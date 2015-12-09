package com.jpmorgan.stock_market.calculator.geometric_mean;

import com.jpmorgan.model.trade.Trade;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class GeometricMeanCalculatorImplTest {

    private static final double DELTA = 0.001;

    private GeometricMeanCalculatorImpl geometricMeanCalculator = new GeometricMeanCalculatorImpl();

    @Test
    public void should_return_0_if_list_is_empty() {
        final double expected = 0;

        assertEquals(expected, geometricMeanCalculator.calculate(Collections.emptyList()).doubleValue(), DELTA);
    }

    @Test
    public void should_return_0_if_list_is_null() {
        final double expected = 0;

        assertEquals(expected, geometricMeanCalculator.calculate(null).doubleValue(), DELTA);
    }

    @Test
    public void should_return_100_after_calculation() {
        final double expected = 100;
        Trade trade = mock(Trade.class);
        doReturn(BigDecimal.valueOf(100)).when(trade).getTradedPrice();

        List<Trade> trades = Arrays.asList(trade, trade, trade);
        assertEquals(expected, geometricMeanCalculator.calculate(trades).doubleValue(), DELTA);
    }

    @Test
    public void should_return_500_after_calculation() {
        final double expected = 500;
        Trade trade = mock(Trade.class);
        doReturn(BigDecimal.valueOf(500)).when(trade).getTradedPrice();

        List<Trade> trades = Arrays.asList(trade, trade, trade, trade, trade);
        assertEquals(expected, geometricMeanCalculator.calculate(trades).doubleValue(), DELTA);
    }
}
