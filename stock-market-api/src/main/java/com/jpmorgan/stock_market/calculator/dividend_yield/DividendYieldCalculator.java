package com.jpmorgan.stock_market.calculator.dividend_yield;

import com.jpmorgan.model.stock.Stock;
import com.jpmorgan.stock_market.calculator.Calculator;

import java.math.BigDecimal;

/**
 * Dividend Yield Calculation interface
 */
public interface DividendYieldCalculator extends Calculator {
    BigDecimal calculate(Stock stock, BigDecimal price);
}
