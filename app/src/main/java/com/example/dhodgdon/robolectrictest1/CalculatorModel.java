package com.example.dhodgdon.robolectrictest1;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.math.BigInteger;

/**
 * Two operand arithmetic model
 */
final class CalculatorModel {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            OPERATOR_INVALID,
            OPERATOR_PLUS,
            OPERATOR_MINUS
    })
    @interface Operator{}
    static final int
            OPERATOR_INVALID = -1,
            OPERATOR_PLUS = 0,
            OPERATOR_MINUS = 1;

    private CalculatorModel() {
        throw new RuntimeException();
    }

    static BigInteger compute(@NonNull BigInteger a, @Operator int operator, @NonNull BigInteger b) {
        switch(operator) {
            case OPERATOR_PLUS: {
                return a.add(b);
            }
            case OPERATOR_MINUS: {
                return a.subtract(b);
            }
            default:
                throw new IllegalStateException("Invalid operator: " + operator);
        }
    }
}
