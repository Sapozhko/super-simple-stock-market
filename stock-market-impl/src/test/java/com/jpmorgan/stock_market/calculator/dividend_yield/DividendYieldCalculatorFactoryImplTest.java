package com.jpmorgan.stock_market.calculator.dividend_yield;

import com.jpmorgan.model.stock.StockType;
import org.junit.Test;

import static org.junit.Assert.*;

public class DividendYieldCalculatorFactoryImplTest {

    //TODO: Replace with DI
    private DividendYieldCalculatorFactory dividendYieldCalculatorFactory =
            new DividendYieldCalculatorFactoryImpl(
                    new DividendYieldCommonCalculatorImpl(),
                    new DividendYieldPreferredCalculatorImpl());

    @Test
    public void should_return_common_instance() {
        final DividendYieldCalculator common = dividendYieldCalculatorFactory.newInstance(StockType.COMMON);

        assertNotNull(common);
        assertEquals(DividendYieldCommonCalculatorImpl.class, common.getClass());
        assertNotEquals(DividendYieldPreferredCalculatorImpl.class, common.getClass());
    }

    @Test
    public void should_return_preferred_instance() {
        final DividendYieldCalculator common = dividendYieldCalculatorFactory.newInstance(StockType.PREFERRED);

        assertNotNull(common);
        assertEquals(DividendYieldPreferredCalculatorImpl.class, common.getClass());
        assertNotEquals(DividendYieldCommonCalculatorImpl.class, common.getClass());
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_null_pointer_exception() {
        dividendYieldCalculatorFactory.newInstance(null);
    }
}
