package com.ustermetrics.scip4j;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    @Test
    void buildSolverReturnsBuiltSolver() {
        val solver = Solver.builder()
                .intParam("limits/restarts", 0)
                .build();

        assertEquals(0, solver.getIntParams().get("limits/restarts"));
        assertTrue(solver.getBoolParams().isEmpty());
    }

    @Test
    void buildSolverWithInvalidArgumentsThrowsException() {
        val exception = assertThrowsExactly(IllegalArgumentException.class,
                () -> Solver.builder()
                        .stringParam("limits/restarts", null)
                        .build());
        assertEquals("stringParams must not contain null keys or values", exception.getMessage());
    }

    @Test
    void versionReturnsPositiveDouble() {
        double version = Solver.version();

        System.out.println("Version: " + version);
        assertTrue(version > 0.);
    }
}
