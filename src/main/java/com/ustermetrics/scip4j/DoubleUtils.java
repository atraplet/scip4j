package com.ustermetrics.optimizer4j.utils;

import lombok.NonNull;

import java.util.stream.IntStream;

import static java.lang.Math.abs;

public class DoubleUtils {

    /**
     * Checks if two doubles are close to each other within tolerances.
     *
     * @implNote The comparison is not symmetric similar to <a href="https://numpy.org">Numpy's</a>
     * <a href="https://numpy.org/doc/stable/reference/generated/numpy.isclose.html">isclose</a>.
     */
    public static boolean isClose(double expected, double actual, double relTol, double absTol) {
        return abs(expected - actual) <= absTol + relTol * abs(expected);
    }

    /**
     * Checks if two doubles are close to each other within default tolerances of {@code 1e-8}.
     *
     * @see DoubleUtils#isClose(double, double, double, double) isClose
     */
    public static boolean isClose(double expected, double actual) {
        return isClose(expected, actual, 1e-8, 1e-8);
    }

    /**
     * Checks if two double arrays are element-wise close to each other within tolerances.
     *
     * @implNote The comparison is not symmetric similar to <a href="https://numpy.org">Numpy's</a>
     * <a href="https://numpy.org/doc/stable/reference/generated/numpy.allclose.html">allclose</a>.
     * @see DoubleUtils#isClose(double, double, double, double) isClose
     */
    public static boolean allClose(double @NonNull [] expected, double @NonNull [] actual, double relTol,
                                   double absTol) {
        return expected.length == actual.length && IntStream.range(0, expected.length)
                .allMatch(i -> isClose(expected[i], actual[i], relTol, absTol));
    }

    /**
     * Checks if two double arrays are element-wise close to each other within default tolerances of {@code 1e-8}.
     *
     * @see DoubleUtils#allClose(double[], double[], double, double) allClose
     */
    public static boolean allClose(double @NonNull [] expected, double @NonNull [] actual) {
        return allClose(expected, actual, 1e-8, 1e-8);
    }
}
