package com.jpmorgan.stock_market.calculator.geometric_mean;

import com.jpmorgan.model.trade.Trade;
import com.jpmorgan.stock_market.calculator.Calculator;

import java.math.BigDecimal;
import java.util.List;

/**
 * Geometric Mean Calculation interface
 */
public interface GeometricMeanCalculator extends Calculator {
    BigDecimal calculate(List<Trade> trades);
}
