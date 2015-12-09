package com.jpmorgan.stock_market.calculator.volume_weighted_stock_price;

import com.jpmorgan.model.trade.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class VolumeWeightedStockPriceCalculatorImpl implements VolumeWeightedStockPriceCalculator {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public BigDecimal calculate(final List<Trade> trades) {
        if (Objects.isNull(trades) || trades.isEmpty()) {
            if (logger.isWarnEnabled()) {
                logger.warn("Trades are empty");
            }

            return BigDecimal.ZERO;
        }

        BigDecimal sumTradedPrice = BigDecimal.ZERO;
        long sumQuantity = 0;

        for (Trade trade : trades) {
            final BigDecimal tradedPrice = trade.getTradedPrice();
            final long sharesQuantity = trade.getSharesQuantity();

            sumTradedPrice = sumTradedPrice.add(tradedPrice.multiply(BigDecimal.valueOf(sharesQuantity)));
            sumQuantity += sharesQuantity;
        }

        return sumTradedPrice.divide(BigDecimal.valueOf(sumQuantity), DEFAULT_MATH_CONTEXT);
    }
}
