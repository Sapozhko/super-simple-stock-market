package com.jpmorgan.stock_market.calculator.pe_ratio;

import java.math.BigDecimal;
import java.util.Objects;

public class PERatioCalculatorImpl implements PERatioCalculator {

    @Override
    public BigDecimal calculate(final BigDecimal dividend, final BigDecimal price) {
        Objects.requireNonNull(dividend, "Dividend can't be 'Null'");
        Objects.requireNonNull(price, "Price can't be 'Null'");

        return price.divide(dividend, DEFAULT_MATH_CONTEXT);
    }
}
