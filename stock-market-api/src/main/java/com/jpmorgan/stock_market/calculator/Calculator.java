package com.jpmorgan.stock_market.calculator;

import java.io.Serializable;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Base interface for all Calculators
 */
public interface Calculator extends Serializable {
    /**
     * PRECISION - The number of digits to be used for an operation; results are rounded to this precision.
     */
    final int PRECISION = 6;

    /**
     * DEFAULT_MATH_CONTEXT - Immutable object which encapsulate the context settings which
     * describe certain rules for numerical operators.
     */
    final MathContext DEFAULT_MATH_CONTEXT = new MathContext(PRECISION, RoundingMode.HALF_EVEN);
}
