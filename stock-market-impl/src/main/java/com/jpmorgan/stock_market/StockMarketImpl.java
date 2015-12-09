package com.jpmorgan.stock_market;

import com.jpmorgan.model.stock.Stock;
import com.jpmorgan.model.stock.StockSymbol;
import com.jpmorgan.model.trade.Trade;
import com.jpmorgan.model.trade.TradeIndicator;
import com.jpmorgan.stock_market.calculator.dividend_yield.DividendYieldCalculator;
import com.jpmorgan.stock_market.calculator.dividend_yield.DividendYieldCalculatorFactory;
import com.jpmorgan.stock_market.calculator.geometric_mean.GeometricMeanCalculator;
import com.jpmorgan.stock_market.calculator.pe_ratio.PERatioCalculator;
import com.jpmorgan.stock_market.calculator.volume_weighted_stock_price.VolumeWeightedStockPriceCalculator;
import com.jpmorgan.stock_market.exchange.stock.StockExchange;
import com.jpmorgan.stock_market.repository.trade.TradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Objects;

public class StockMarketImpl implements StockMarket {
    public static final int MINUTES = 15;

    final Logger logger = LoggerFactory.getLogger(getClass());

    private final StockExchange stockExchange;
    private final TradeRepository tradeRepository;

    private final DividendYieldCalculatorFactory dividendYieldCalculatorFactory;
    private final PERatioCalculator peRatioCalculator;
    private final VolumeWeightedStockPriceCalculator volumeWeightedStockPriceCalculator;
    private final GeometricMeanCalculator geometricMeanCalculator;

    @Inject
    public StockMarketImpl(StockExchange stockExchange,
                           TradeRepository tradeRepository,
                           DividendYieldCalculatorFactory dividendYieldCalculatorFactory,
                           PERatioCalculator peRatioCalculator,
                           VolumeWeightedStockPriceCalculator volumeWeightedStockPriceCalculator,
                           GeometricMeanCalculator geometricMeanCalculator) {

        this.stockExchange = stockExchange;
        this.tradeRepository = tradeRepository;
        this.dividendYieldCalculatorFactory = dividendYieldCalculatorFactory;
        this.peRatioCalculator = peRatioCalculator;
        this.volumeWeightedStockPriceCalculator = volumeWeightedStockPriceCalculator;
        this.geometricMeanCalculator = geometricMeanCalculator;
    }

    @Override
    public Trade process(final StockSymbol stockSymbol, final BigDecimal price) {
        Objects.requireNonNull(stockSymbol, "Stock Symbol can't be 'Null'");
        Objects.requireNonNull(price, "Price can't be 'Null'");


        final Stock stock = stockExchange.get(stockSymbol);
        Objects.requireNonNull(stock, "Stock is not found");


        if (logger.isInfoEnabled()) {
            logger.info("Processing Stock: " + stock);
        }


        final DividendYieldCalculator dividendYieldCalculator = dividendYieldCalculatorFactory.newInstance(stock.getType());

        final BigDecimal dividendYield = dividendYieldCalculator.calculate(stock, price);
        if (logger.isInfoEnabled()) {
            logger.info("Stock - Dividend Yield: " + dividendYield);
        }


        final BigDecimal peRatio = peRatioCalculator.calculate(dividendYield, price);
        if (logger.isInfoEnabled()) {
            logger.info("Stock - P/E Ratio: " + peRatio);
        }


        final Trade trade = tradeRepository.create(new Trade.Builder(System.currentTimeMillis())
                .indicator(getIndicator(peRatio))
                .sharesQuantity(getSharesQuantity())
                .tradedPrice(price)
                .build());
        if (logger.isInfoEnabled()) {
            logger.info("Recorded a trade: " + trade);
        }


        final BigDecimal volumeWeightedStockPrice = volumeWeightedStockPriceCalculator.calculate(tradeRepository.lastMinutes(MINUTES));
        if (logger.isInfoEnabled()) {
            logger.info("Stock - Volume Weighted Stock Price: " + volumeWeightedStockPrice);
        }


        final BigDecimal geometricMean = geometricMeanCalculator.calculate(tradeRepository.list());
        if (logger.isInfoEnabled()) {
            logger.info("Stock - GBCE All Share Index: " + geometricMean);
        }

        return trade;
    }

    private TradeIndicator getIndicator(final BigDecimal peRatio) {
        return peRatio.compareTo(BigDecimal.ONE) > 0 ? TradeIndicator.BUY : TradeIndicator.SELL;
    }

    private long getSharesQuantity() {
        final long numOfTrades = tradeRepository.count();
        return numOfTrades == 0 ? 1 : numOfTrades;
    }
}
