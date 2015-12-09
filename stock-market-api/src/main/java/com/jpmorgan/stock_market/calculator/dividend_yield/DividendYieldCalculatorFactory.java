package com.jpmorgan.stock_market.calculator.dividend_yield;

import com.jpmorgan.model.stock.StockType;

/**
 * Dividend Yield Calculation Factory interface
 */
public interface DividendYieldCalculatorFactory {
    DividendYieldCalculator newInstance(StockType type);
}
