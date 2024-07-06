package com.ustermetrics.scip4j;

import lombok.val;
import org.junit.jupiter.api.Test;

import static com.ustermetrics.optimizer4j.scip.Problem.ObjectiveSense.MAXIMIZE;
import static com.ustermetrics.optimizer4j.scip.Solver.Status.MEMLIMIT;
import static com.ustermetrics.optimizer4j.scip.Solver.Status.OPTIMAL;
import static com.ustermetrics.optimizer4j.scip.Variable.VariableType.CONTINUOUS;
import static com.ustermetrics.optimizer4j.scip.Variable.VariableType.INTEGER;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProblemTest {

    @Test
    void solveMIPReturnsOptimalSolution() {
        try (val problem = new Problem("MIP")) {
            val x = problem.addVar("x", 0., 3.5, INTEGER);
            val y = problem.addVar("y", 0., problem.infinity(), INTEGER);

            problem.setObjective(MAXIMIZE, new Variable[]{x, y}, new double[]{1., 10.});

            problem.addCons("linear", -problem.infinity(), 17.5, new Variable[]{x, y}, new double[]{1., 7.});

            val status = problem.solve();
            assertEquals(OPTIMAL, status);

            System.out.println("\nProblem:");
            problem.printOrigProblem(Problem.PrintFormat.LP);

            val obj = problem.getSolOrigObj();
            val xVal = problem.getSolVal(x);
            val yVal = problem.getSolVal(y);

            System.out.println("\nSolution:");
            System.out.println("obj: " + obj);
            System.out.println("x: " + xVal);
            System.out.println("y: " + yVal);

            val tol = 1e-8;
            assertEquals(23., obj, tol);
            assertEquals(3., xVal, tol);
            assertEquals(2., yVal, tol);
        }
    }

    @Test
    void solveMIQCPReturnsOptimalSolution() {
        try (val problem = new Problem("MIQCP")) {
            val x = problem.addVar("x", 0., 3.5, INTEGER);
            val y = problem.addVar("y", 0., problem.infinity(), INTEGER);

            problem.setObjective(MAXIMIZE, new Variable[]{x, y}, new double[]{1., 10.});

            problem.addCons("linear", -problem.infinity(), 17.5, new Variable[]{x, y}, new double[]{1., 7.});
            problem.addCons("quadratic", -problem.infinity(), 5., new Variable[]{x}, new Variable[]{x},
                    new double[]{1.});

            val status = problem.solve();
            assertEquals(OPTIMAL, status);

            System.out.println("\nProblem:");
            problem.printOrigProblem(Problem.PrintFormat.LP);

            val obj = problem.getSolOrigObj();
            val xVal = problem.getSolVal(x);
            val yVal = problem.getSolVal(y);

            System.out.println("\nSolution:");
            System.out.println("obj: " + obj);
            System.out.println("x: " + xVal);
            System.out.println("y: " + yVal);

            val tol = 1e-8;
            assertEquals(22., obj, tol);
            assertEquals(2., xVal, tol);
            assertEquals(2., yVal, tol);
        }
    }

    @Test
    void solveMINLPReturnsOptimalSolution() {
        try (val problem = new Problem("MINLP")) {
            val x = problem.addVar("x", 0., 3.5, INTEGER);
            val y = problem.addVar("y", 0., problem.infinity(), INTEGER);
            val t = problem.addVar("t", -problem.infinity(), problem.infinity(), CONTINUOUS);

            problem.setObjective(MAXIMIZE, new Variable[]{x, y}, new double[]{1., 10.});

            problem.addCons("linear 1", -problem.infinity(), 17.5, new Variable[]{x, y}, new double[]{1., 7.});
            problem.addCons("linear 2", -problem.infinity(), sqrt(5.), new Variable[]{t}, new double[]{1.});
            problem.addCons("soc", new Variable[]{x}, new double[]{1.}, new double[]{0.}, 0., t, 1., 0.);

            val status = problem.solve();
            assertEquals(OPTIMAL, status);

            System.out.println("\nProblem:");
            problem.printOrigProblem();

            val obj = problem.getSolOrigObj();
            val xVal = problem.getSolVal(x);
            val yVal = problem.getSolVal(y);

            System.out.println("\nSolution:");
            System.out.println("obj: " + obj);
            System.out.println("x: " + xVal);
            System.out.println("y: " + yVal);

            val tol = 1e-8;
            assertEquals(22., obj, tol);
            assertEquals(2., xVal, tol);
            assertEquals(2., yVal, tol);
        }
    }

    @Test
    void solveProblemWithMemlimitReturnsMemlimitStatus() {
        try (val problem = new Problem()) {
            val x = problem.addVar(0., 3.5, INTEGER);
            val y = problem.addVar(0., problem.infinity(), INTEGER);
            problem.setObjective(MAXIMIZE, new Variable[]{x, y}, new double[]{1., 10.});
            problem.addCons("", -problem.infinity(), 17.5, new Variable[]{x, y}, new double[]{1., 7.});
            val solver = Solver.builder().realParam("limits/memory", 1e-6).build();
            val status = problem.solve(solver);

            System.out.println("Status: " + status);
            assertEquals(MEMLIMIT, status);
        }
    }

    @Test
    void getObjectiveValueWithoutSolvingProblemThrowsException() {
        val exception = assertThrows(IllegalStateException.class, () -> {
            try (val problem = new Problem()) {
                problem.getSolOrigObj();
            }
        });

        assertEquals("Solution address must not be NULL", exception.getMessage());
    }
}
