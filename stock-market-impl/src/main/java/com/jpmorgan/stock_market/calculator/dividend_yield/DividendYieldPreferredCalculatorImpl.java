package com.jpmorgan.stock_market.calculator.dividend_yield;

import com.jpmorgan.model.stock.Stock;
import com.jpmorgan.model.stock.StockType;

import java.math.BigDecimal;
import java.util.Objects;

public class DividendYieldPreferredCalculatorImpl extends DividendYieldCalculatorImpl {

    public static final String NAMED = "DividendYieldPreferredCalculator";

    @Override
    protected StockType stockType() {
        return StockType.PREFERRED;
    }

    @Override
    protected BigDecimal evaluate(final Stock stock, final BigDecimal price) {
        BigDecimal fixedDividend = stock.getFixedDividend();
        BigDecimal parValue = stock.getParValue();

        Objects.requireNonNull(fixedDividend, "Fixed Dividend can't be 'Null'");
        Objects.requireNonNull(parValue, "Par Value can't be 'Null'");
        Objects.requireNonNull(price, "Price can't be 'Null'");

        return fixedDividend
                .multiply(parValue)
                .divide(price.multiply(BigDecimal.valueOf(100)), DEFAULT_MATH_CONTEXT);
    }
}
