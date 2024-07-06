package com.ustermetrics.scip4j;

import org.junit.jupiter.api.Test;

import static com.ustermetrics.optimizer4j.utils.DoubleUtils.allClose;
import static com.ustermetrics.optimizer4j.utils.DoubleUtils.isClose;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoubleUtilsTest {

    @Test
    void Doubles_within_tolerances_are_close() {
        assertTrue(isClose(0., 1e-9));
    }

    @Test
    void Doubles_not_within_tolerances_are_not_close() {
        assertFalse(isClose(0., 1e-7));
    }

    @Test
    void Double_arrays_with_different_length_are_not_close() {
        assertFalse(allClose(new double[]{0., 1.}, new double[]{0.}));
    }

    @Test
    void Double_arrays_within_tolerances_are_close() {
        assertTrue(allClose(new double[]{0., 1.}, new double[]{1e-9, 1.}));
    }

    @Test
    void Double_arrays_not_within_tolerances_are_not_close() {
        assertFalse(allClose(new double[]{0., 1.}, new double[]{1e-7, 1.}));
    }
}