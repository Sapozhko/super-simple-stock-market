package com.jpmorgan.stock_market.calculator.pe_ratio;

import com.jpmorgan.stock_market.calculator.Calculator;

import java.math.BigDecimal;

/**
 * P/E Ratio Calculation interface
 */
public interface PERatioCalculator extends Calculator {
    BigDecimal calculate(BigDecimal dividend, BigDecimal price);
}
