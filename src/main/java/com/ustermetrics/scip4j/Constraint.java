package com.ustermetrics.optimizer4j.scip;

import lombok.NonNull;

import java.lang.foreign.MemorySegment;

/**
 * A constraint of a SCIP problem
 */
public class Constraint {

    private final MemorySegment segment;

    Constraint(@NonNull MemorySegment segment) {
        this.segment = segment;
    }

    MemorySegment segment() {
        return segment;
    }
}
