package com.ustermetrics.scip4j.bindings;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.lang.foreign.Arena;

import static com.ustermetrics.optimizer4j.scip.bindings.scip_h.*;
import static java.lang.foreign.MemorySegment.NULL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BindingsTest {

    @Test
    void versionReturnsPositiveDouble() {
        val version = SCIPversion();

        System.out.println("Version: " + version);
        assertTrue(version > 0.);
    }

    @Test
    void solveProblemReturnsOptimalSolution() {
        try (val arena = Arena.openConfined()) {
            val scipSeg = arena.allocate(C_POINTER);
            assertEquals(SCIP_OKAY(), SCIPcreate(scipSeg));

            val scipAdr = scipSeg.get(C_POINTER, 0);
            assertEquals(SCIP_OKAY(), SCIPincludeDefaultPlugins(scipAdr));

            val probNameSeg = arena.allocateUtf8String("Mixed-Integer Linear Problem");
            assertEquals(SCIP_OKAY(), SCIPcreateProbBasic(scipAdr, probNameSeg));

            assertEquals(SCIP_OKAY(), SCIPsetObjsense(scipAdr, SCIP_OBJSENSE_MAXIMIZE()));

            val xSeg = arena.allocate(C_POINTER);
            val xNameSeg = arena.allocateUtf8String("x");
            assertEquals(SCIP_OKAY(), SCIPcreateVarBasic(scipAdr, xSeg, xNameSeg, 0., 3.5, 1., SCIP_VARTYPE_INTEGER()));
            val xAdr = xSeg.get(C_POINTER, 0);
            assertEquals(SCIP_OKAY(), SCIPaddVar(scipAdr, xAdr));

            val ySeg = arena.allocate(C_POINTER);
            val yNameSeg = arena.allocateUtf8String("y");
            assertEquals(SCIP_OKAY(), SCIPcreateVarBasic(scipAdr, ySeg, yNameSeg, 0., SCIPinfinity(scipAdr), 10.,
                    SCIP_VARTYPE_INTEGER()));
            val yAdr = ySeg.get(C_POINTER, 0);
            assertEquals(SCIP_OKAY(), SCIPaddVar(scipAdr, yAdr));

            val consSeg = arena.allocate(C_POINTER);
            val consNameSeg = arena.allocateUtf8String("cons");
            assertEquals(SCIP_OKAY(), SCIPcreateConsBasicLinear(scipAdr, consSeg, consNameSeg, 0, NULL, NULL,
                    -SCIPinfinity(scipAdr), 17.5));
            val consAdr = consSeg.get(C_POINTER, 0);
            assertEquals(SCIP_OKAY(), SCIPaddCoefLinear(scipAdr, consAdr, xAdr, 1.));
            assertEquals(SCIP_OKAY(), SCIPaddCoefLinear(scipAdr, consAdr, yAdr, 7.));
            assertEquals(SCIP_OKAY(), SCIPaddCons(scipAdr, consAdr));

            assertEquals(SCIP_OKAY(), SCIPsolve(scipAdr));
            assertEquals(SCIP_STATUS_OPTIMAL(), SCIPgetStatus(scipAdr));

            System.out.println("\nProblem:");
            val extNameSeg = arena.allocateUtf8String("lp");
            assertEquals(SCIP_OKAY(), SCIPprintOrigProblem(scipAdr, NULL, extNameSeg, FALSE()));

            val solAdr = SCIPgetBestSol(scipAdr);
            val obj = SCIPgetSolOrigObj(scipAdr, solAdr);
            val x = SCIPgetSolVal(scipAdr, solAdr, xAdr);
            val y = SCIPgetSolVal(scipAdr, solAdr, yAdr);
            System.out.println("\nSolution:");
            System.out.println("obj: " + obj);
            System.out.println("x: " + x);
            System.out.println("y: " + y);
            val tol = 1e-8;
            assertEquals(23., obj, tol);
            assertEquals(3., x, tol);
            assertEquals(2., y, tol);

            assertEquals(SCIP_OKAY(), SCIPreleaseCons(scipAdr, consSeg));

            assertEquals(SCIP_OKAY(), SCIPreleaseVar(scipAdr, xSeg));
            assertEquals(SCIP_OKAY(), SCIPreleaseVar(scipAdr, ySeg));

            assertEquals(SCIP_OKAY(), SCIPfree(scipSeg));
        }
    }
}
