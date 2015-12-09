package com.jpmorgan.stock_market.calculator.volume_weighted_stock_price;

import com.jpmorgan.model.trade.Trade;
import com.jpmorgan.stock_market.calculator.Calculator;

import java.math.BigDecimal;
import java.util.List;

/**
 * Volume Weighted Stock Price Calculation interface
 */
public interface VolumeWeightedStockPriceCalculator extends Calculator {
    BigDecimal calculate(List<Trade> trades);
}
