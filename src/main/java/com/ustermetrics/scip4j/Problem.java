package com.ustermetrics.optimizer4j.scip;

import lombok.NonNull;
import lombok.val;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.*;
import static com.google.common.base.Verify.verify;
import static com.ustermetrics.optimizer4j.scip.bindings.scip_h.*;
import static java.lang.foreign.MemorySegment.NULL;
import static java.lang.foreign.ValueLayout.ADDRESS;

/**
 * A mixed-integer linear program or a mixed-integer nonlinear program (MINLP),
 * which can be solved with the SCIP solver.
 * <p>
 * The constraints of the MINLP can be linear, quadratic, or second-order cone
 * constraints.
 *
 * @implNote In order to control the lifecycle of native memory, Problem
 * implements the {@linkplain AutoCloseable} interface and should be used with the
 * <i>try-with-resources</i> statement.
 * @see <a href="https://github.com/scipopt/scip">SCIP</a>
 */
public class Problem implements AutoCloseable {

    private enum ReturnCode {
        OKAY(SCIP_OKAY()),
        ERROR(SCIP_ERROR()),
        NOMEMORY(SCIP_NOMEMORY()),
        READERROR(SCIP_READERROR()),
        WRITEERROR(SCIP_WRITEERROR()),
        NOFILE(SCIP_NOFILE()),
        FILECREATEERROR(SCIP_FILECREATEERROR()),
        LPERROR(SCIP_LPERROR()),
        NOPROBLEM(SCIP_NOPROBLEM()),
        INVALIDCALL(SCIP_INVALIDCALL()),
        INVALIDDATA(SCIP_INVALIDDATA()),
        INVALIDRESULT(SCIP_INVALIDRESULT()),
        PLUGINNOTFOUND(SCIP_PLUGINNOTFOUND()),
        PARAMETERUNKNOWN(SCIP_PARAMETERUNKNOWN()),
        PARAMETERWRONGTYPE(SCIP_PARAMETERWRONGTYPE()),
        PARAMETERWRONGVAL(SCIP_PARAMETERWRONGVAL()),
        KEYALREADYEXISTING(SCIP_KEYALREADYEXISTING()),
        MAXDEPTHLEVEL(SCIP_MAXDEPTHLEVEL()),
        BRANCHERROR(SCIP_BRANCHERROR()),
        NOTIMPLEMENTED(SCIP_NOTIMPLEMENTED());

        private final int code;

        private static ReturnCode valueOf(int code) {
            for (var c : values()) {
                if (c.code() == code) {
                    return c;
                }
            }

            throw new IllegalArgumentException("Unknown return code");
        }

        ReturnCode(int code) {
            this.code = code;
        }

        private int code() {
            return code;
        }
    }

    /**
     * The objective sense of a problem
     *
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public enum ObjectiveSense {
        MINIMIZE(SCIP_OBJSENSE_MINIMIZE()),
        MAXIMIZE(SCIP_OBJSENSE_MAXIMIZE());

        private final int sense;

        static ObjectiveSense valueOf(int sense) {
            for (var s : values()) {
                if (s.sense() == sense) {
                    return s;
                }
            }

            throw new IllegalArgumentException("Unknown objective sense");
        }

        ObjectiveSense(int sense) {
            this.sense = sense;
        }

        int sense() {
            return sense;
        }
    }

    /**
     * The print format of a problem
     *
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public enum PrintFormat {
        CIP,
        LP,
        MPS
    }

    private final Arena arena = Arena.openConfined();
    private final List<Variable> variables = new ArrayList<>();
    private final List<Constraint> constraints = new ArrayList<>();
    private final MemorySegment scipSeg;
    private final MemorySegment scipAdr;

    private static void checkCall(int code) {
        val returnCode = ReturnCode.valueOf(code);
        verify(returnCode == ReturnCode.OKAY, "Error '" + returnCode.name() + "' in function call");
    }

    private MemorySegment allocateArray(Variable @NonNull [] vars) {
        val nvars = vars.length;
        assert nvars > 0;

        val varsSeg = arena.allocateArray(ADDRESS, nvars);
        for (int i = 0; i < nvars; i++) {
            assert vars[i] != null;

            val varAdr = vars[i].address();
            varsSeg.set(ADDRESS, ADDRESS.byteSize() * i, varAdr);
        }

        return varsSeg;
    }

    private void setBoolParams(@NonNull Solver solver) {
        for (var entry : solver.getBoolParams().entrySet()) {
            val nameSeg = arena.allocateUtf8String(entry.getKey());
            checkCall(SCIPsetBoolParam(scipAdr, nameSeg, entry.getValue() ? TRUE() : FALSE()));
        }
    }

    private void setIntParams(@NonNull Solver solver) {
        for (var entry : solver.getIntParams().entrySet()) {
            val nameSeg = arena.allocateUtf8String(entry.getKey());
            checkCall(SCIPsetIntParam(scipAdr, nameSeg, entry.getValue()));
        }
    }

    private void setLongintParams(@NonNull Solver solver) {
        for (var entry : solver.getLongintParams().entrySet()) {
            val nameSeg = arena.allocateUtf8String(entry.getKey());
            checkCall(SCIPsetLongintParam(scipAdr, nameSeg, entry.getValue()));
        }
    }

    private void setRealParams(@NonNull Solver solver) {
        for (var entry : solver.getRealParams().entrySet()) {
            val nameSeg = arena.allocateUtf8String(entry.getKey());
            checkCall(SCIPsetRealParam(scipAdr, nameSeg, entry.getValue()));
        }
    }

    private void setCharParams(@NonNull Solver solver) {
        for (var entry : solver.getCharParams().entrySet()) {
            val nameSeg = arena.allocateUtf8String(entry.getKey());
            checkCall(SCIPsetCharParam(scipAdr, nameSeg, (byte) entry.getValue().charValue()));
        }
    }

    private void setStringParams(@NonNull Solver solver) {
        for (var entry : solver.getStringParams().entrySet()) {
            val nameSeg = arena.allocateUtf8String(entry.getKey());
            val valueSeg = arena.allocateUtf8String(entry.getValue());
            checkCall(SCIPsetStringParam(scipAdr, nameSeg, valueSeg));
        }
    }

    private void setParams(@NonNull Solver solver) {
        setBoolParams(solver);
        setIntParams(solver);
        setLongintParams(solver);
        setRealParams(solver);
        setCharParams(solver);
        setStringParams(solver);
    }

    private Constraint addCons(@NonNull MemorySegment consSeg) {
        val consAdr = consSeg.get(C_POINTER, 0);
        checkCall(SCIPaddCons(scipAdr, consAdr));
        val constraint = new Constraint(consSeg);
        constraints.add(constraint);

        return constraint;
    }

    /**
     * @param name the name of this problem
     */
    public Problem(@NonNull String name) {
        scipSeg = arena.allocate(C_POINTER);
        checkCall(SCIPcreate(scipSeg));

        scipAdr = scipSeg.get(C_POINTER, 0);
        checkCall(SCIPincludeDefaultPlugins(scipAdr));

        val nameSeg = arena.allocateUtf8String(name);
        checkCall(SCIPcreateProbBasic(scipAdr, nameSeg));
    }

    public Problem() {
        this("");
    }

    /**
     * Creates and adds a variable to this problem
     *
     * @param name    the name of the variable
     * @param lb      the lower bound of the variable
     * @param ub      the upper bound of the variable
     * @param varType the type of the variable
     * @return the variable
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public Variable addVar(String name, double lb, double ub, Variable.VariableType varType) {
        val varSeg = arena.allocate(C_POINTER);
        val nameSeg = name != null ? arena.allocateUtf8String(name) : NULL;
        checkCall(SCIPcreateVarBasic(scipAdr, varSeg, nameSeg, lb, ub, 0., varType.type()));

        val varAdr = varSeg.get(C_POINTER, 0);
        checkCall(SCIPaddVar(scipAdr, varAdr));
        val variable = new Variable(varSeg);
        variables.add(variable);

        return variable;
    }

    /**
     * Creates and adds a variable to this problem
     *
     * @param lb      the lower bound of the variable
     * @param ub      the upper bound of the variable
     * @param varType the type of the variable
     * @return the variable
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public Variable addVar(double lb, double ub, Variable.VariableType varType) {
        return addVar(null, lb, ub, varType);
    }

    /**
     * Sets the objective function of this problem
     *
     * @param objSense the objective sense
     * @param vars     the variables in the objective function
     * @param objs     the coefficients of the variables in the objective function
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public void setObjective(ObjectiveSense objSense, Variable @NonNull [] vars, double @NonNull [] objs) {
        val nvars = vars.length;
        checkArgument(nvars > 0, "length of vars must be positive");
        checkArgument(nvars == objs.length, "vars and objs must have the same length");

        for (int i = 0; i < nvars; i++) {
            checkNotNull(vars[i]);

            val varAdr = vars[i].address();
            val obj = objs[i];
            checkCall(SCIPchgVarObj(scipAdr, varAdr, obj));
        }

        checkCall(SCIPsetObjsense(scipAdr, objSense.sense()));
    }

    /**
     * Creates and adds a linear constraint to this problem
     *
     * @param name the name of the constraint
     * @param lhs  the left hand side of the constraint
     * @param rhs  the right hand side of the constraint
     * @param vars the variables in the constraint
     * @param vals the coefficients of the variables in the constraint
     * @return the constraint
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public Constraint addCons(@NonNull String name, double lhs, double rhs, Variable @NonNull [] vars,
                              double @NonNull [] vals) {
        val nvars = vars.length;
        checkArgument(nvars > 0, "length of vars must be positive");
        checkArgument(!Arrays.asList(vars).contains(null), "vars must not contain null");
        checkArgument(nvars == vals.length, "vars and vals must have the same length");

        val consSeg = arena.allocate(C_POINTER);
        val nameSeg = arena.allocateUtf8String(name);
        val varsSeg = allocateArray(vars);
        val valsSeg = arena.allocateArray(C_DOUBLE, vals);
        checkCall(SCIPcreateConsBasicLinear(scipAdr, consSeg, nameSeg, nvars, varsSeg, valsSeg, lhs, rhs));

        return addCons(consSeg);
    }

    /**
     * Creates and adds a quadratic constraint to this problem
     *
     * @param name      the name of the constraint
     * @param lhs       the left hand side of the constraint
     * @param rhs       the right hand side of the constraint
     * @param linVars   the linear variables in the constraint
     * @param linCoefs  the coefficients of the linear variables in the constraint
     * @param quadVars1 the first variables in the quadratic terms of the constraint
     * @param quadVars2 the second variables in the quadratic terms of the constraint
     * @param quadCoefs the coefficients of the quadratic terms of the constraint
     * @return the constraint
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public Constraint addCons(@NonNull String name, double lhs, double rhs, Variable[] linVars, double[] linCoefs,
                              Variable @NonNull [] quadVars1, Variable @NonNull [] quadVars2,
                              double @NonNull [] quadCoefs) {
        val nquadterms = quadVars1.length;
        checkArgument(nquadterms > 0, "length of quadVars1 must be positive");
        checkArgument(!Arrays.asList(quadVars1).contains(null), "quadVars1 must not contain null");
        checkArgument(nquadterms == quadVars2.length, "quadVars1 and quadVars2 must have the same length");
        checkArgument(!Arrays.asList(quadVars2).contains(null), "quadVars2 must not contain null");
        checkArgument(nquadterms == quadCoefs.length, "quadVars1 and quadCoefs must have the same length");
        checkArgument(linVars == null && linCoefs == null || linVars != null && linCoefs != null, "linVars and " +
                "linCoefs must be both null or must be both not null");
        val nlinvars = linVars != null ? linVars.length : 0;
        checkArgument(linVars == null || nlinvars > 0, "linVars must be null or the length of linVars must be " +
                "positive");
        checkArgument(linVars == null || !Arrays.asList(linVars).contains(null), "linVars must be null or linVars " +
                "must not contain null");
        checkArgument(linVars == null || nlinvars == linCoefs.length, "linVars must be null or linVars and linCoefs " +
                "have the same length");

        val consSeg = arena.allocate(C_POINTER);
        val nameSeg = arena.allocateUtf8String(name);
        val linVarsSeg = linVars != null ? allocateArray(linVars) : NULL;
        val linCoefsSeg = linCoefs != null ? arena.allocateArray(C_DOUBLE, linCoefs) : NULL;
        val quadVars1Seg = allocateArray(quadVars1);
        val quadVars2Seg = allocateArray(quadVars2);
        val quadCoefsSeg = arena.allocateArray(C_DOUBLE, quadCoefs);
        checkCall(SCIPcreateConsBasicQuadraticNonlinear(scipAdr, consSeg, nameSeg, nlinvars, linVarsSeg, linCoefsSeg,
                nquadterms, quadVars1Seg, quadVars2Seg, quadCoefsSeg, lhs, rhs));

        return addCons(consSeg);
    }

    /**
     * Creates and adds a quadratic constraint to this problem
     *
     * @param name      the name of the constraint
     * @param lhs       the left hand side of the constraint
     * @param rhs       the right hand side of the constraint
     * @param quadVars1 the first variables in the quadratic terms of the constraint
     * @param quadVars2 the second variables in the quadratic terms of the constraint
     * @param quadCoefs the coefficients of the quadratic terms of the constraint
     * @return the constraint
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public Constraint addCons(@NonNull String name, double lhs, double rhs, Variable @NonNull [] quadVars1,
                              Variable @NonNull [] quadVars2, double @NonNull [] quadCoefs) {
        return addCons(name, lhs, rhs, null, null, quadVars1, quadVars2, quadCoefs);
    }

    /**
     * Creates and adds a second-order cone constraint to this problem
     *
     * @param name      the name of the constraint
     * @param vars      the left hand side variables
     * @param coefs     the left hand side coefficients of the variables
     * @param offsets   the left hand side offsets
     * @param constant  the left hand side constant
     * @param rhsVar    the right hand side variable
     * @param rhsCoef   the right hand side coefficient of the variable
     * @param rhsOffset the right hand side offset
     * @return the constraint
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public Constraint addCons(@NonNull String name, Variable @NonNull [] vars, double @NonNull [] coefs,
                              double @NonNull [] offsets, double constant, @NonNull Variable rhsVar, double rhsCoef,
                              double rhsOffset) {
        val nvars = vars.length;
        checkArgument(nvars > 0, "length of vars must be positive");
        checkArgument(!Arrays.asList(vars).contains(null), "vars must not contain null");
        checkArgument(nvars == coefs.length, "vars and coefs must have the same length");
        checkArgument(nvars == offsets.length, "vars and offsets must have the same length");

        val consSeg = arena.allocate(C_POINTER);
        val nameSeg = arena.allocateUtf8String(name);
        val varsSeg = allocateArray(vars);
        val coefsSeg = arena.allocateArray(C_DOUBLE, coefs);
        val offsetsSeg = arena.allocateArray(C_DOUBLE, offsets);
        checkCall(SCIPcreateConsBasicSOCNonlinear(scipAdr, consSeg, nameSeg, nvars, varsSeg, coefsSeg, offsetsSeg,
                constant, rhsVar.address(), rhsCoef, rhsOffset));

        return addCons(consSeg);
    }

    /**
     * Solves this problem
     *
     * @param solver the parameter object for the parameters of the SCIP solver. For parameters not present in the
     *               parameter object, the default parameters of the native solver are used.
     * @return the exit status from SCIP
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public Solver.Status solve(Solver solver) {
        if (solver != null) {
            setParams(solver);
        }
        checkCall(SCIPsolve(scipAdr));
        return Solver.Status.valueOf(SCIPgetStatus(scipAdr));
    }

    /**
     * Solves this problem using the SCIP solver with default parameters
     *
     * @return the exit status from SCIP
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public Solver.Status solve() {
        return solve(null);
    }

    /**
     * Get the objective value of the solution of this problem
     *
     * @return the objective value
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public double getSolOrigObj() {
        val solAdr = SCIPgetBestSol(scipAdr);
        checkState(!NULL.equals(solAdr), "Solution address must not be NULL");
        return SCIPgetSolOrigObj(scipAdr, solAdr);
    }

    /**
     * Get the value of the given variable in the solution of this problem
     *
     * @param var the variable
     * @return the value of the given variable
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public double getSolVal(@NonNull Variable var) {
        val solAdr = SCIPgetBestSol(scipAdr);
        checkState(!NULL.equals(solAdr), "Solution address must not be NULL");
        val varAdr = var.address();
        return SCIPgetSolVal(scipAdr, solAdr, varAdr);
    }

    /**
     * Get the values of the given variables in the solution of this problem
     *
     * @param vars the variables
     * @return the values of the given variables
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public double[] getSolVals(Variable @NonNull [] vars) {
        checkArgument(vars.length > 0, "length of vars must be positive");
        checkArgument(!Arrays.asList(vars).contains(null), "vars must not contain null");

        // TODO
        val solAddr = SCIPgetBestSol(scipAdr);
        checkState(!NULL.equals(solAddr), "Solution address must not be NULL");
//        val varAddr = var.address();
//        checkCall(SCIPgetSolVals(scipAddr, solAddr, varAddr, valsAddr));
        val sol = new double[]{};
        return sol;
    }

    /**
     * Prints this problem
     *
     * @param format          the print format
     * @param useGenericNames use generic names?
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public void printOrigProblem(PrintFormat format, boolean useGenericNames) {
        val formatSeg = arena.allocateUtf8String(format.name());
        checkCall(SCIPprintOrigProblem(scipAdr, NULL, formatSeg, useGenericNames ? TRUE() : FALSE()));
    }

    /**
     * Prints this problem with the original names
     *
     * @param format the print format
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public void printOrigProblem(PrintFormat format) {
        printOrigProblem(format, false);
    }

    /**
     * Prints this problem with the original names in the CIP format
     *
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public void printOrigProblem() {
        printOrigProblem(PrintFormat.CIP, false);
    }

    /**
     * Sets the message handler to be quiet or not
     *
     * @param quiet quiet or not
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public void setMessageHdlrQuiet(boolean quiet) {
        SCIPsetMessagehdlrQuiet(scipAdr, quiet ? 1 : 0);
    }

    /**
     * Get SCIP's infinity value
     *
     * @return infinity
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public double infinity() {
        return SCIPinfinity(scipAdr);
    }

    @Override
    public void close() {
        for (var constraint : constraints) {
            checkCall(SCIPreleaseCons(scipAdr, constraint.segment()));
        }
        for (var variable : variables) {
            checkCall(SCIPreleaseVar(scipAdr, variable.segment()));
        }
        checkCall(SCIPfree(scipSeg));
        arena.close();
    }
}
