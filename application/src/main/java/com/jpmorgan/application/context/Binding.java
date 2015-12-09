package com.jpmorgan.application.context;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.jpmorgan.stock_market.StockMarket;
import com.jpmorgan.stock_market.StockMarketImpl;
import com.jpmorgan.stock_market.calculator.dividend_yield.*;
import com.jpmorgan.stock_market.calculator.geometric_mean.GeometricMeanCalculator;
import com.jpmorgan.stock_market.calculator.geometric_mean.GeometricMeanCalculatorImpl;
import com.jpmorgan.stock_market.calculator.pe_ratio.PERatioCalculator;
import com.jpmorgan.stock_market.calculator.pe_ratio.PERatioCalculatorImpl;
import com.jpmorgan.stock_market.calculator.volume_weighted_stock_price.VolumeWeightedStockPriceCalculator;
import com.jpmorgan.stock_market.calculator.volume_weighted_stock_price.VolumeWeightedStockPriceCalculatorImpl;
import com.jpmorgan.stock_market.exchange.stock.StockExchange;
import com.jpmorgan.stock_market.exchange.stock.StockExchangeImpl;
import com.jpmorgan.stock_market.repository.trade.TradeRepository;
import com.jpmorgan.stock_market.repository.trade.TradeRepositoryImpl;

/**
 * Binding is responsible for binding interfaces to implementation (DI)
 */
public final class Binding extends AbstractModule {

    @Override
    protected void configure() {
        bind(StockMarket.class).to(StockMarketImpl.class);
        bind(StockExchange.class).to(StockExchangeImpl.class);
        bind(TradeRepository.class).to(TradeRepositoryImpl.class);

        bind(DividendYieldCalculatorFactory.class).to(DividendYieldCalculatorFactoryImpl.class);
        bind(DividendYieldCalculator.class)
                .annotatedWith(Names.named(DividendYieldCommonCalculatorImpl.NAMED))
                .to(DividendYieldCommonCalculatorImpl.class);
        bind(DividendYieldCalculator.class)
                .annotatedWith(Names.named(DividendYieldPreferredCalculatorImpl.NAMED))
                .to(DividendYieldPreferredCalculatorImpl.class);

        bind(PERatioCalculator.class).to(PERatioCalculatorImpl.class);

        bind(VolumeWeightedStockPriceCalculator.class).to(VolumeWeightedStockPriceCalculatorImpl.class);

        bind(GeometricMeanCalculator.class).to(GeometricMeanCalculatorImpl.class);
    }
}
