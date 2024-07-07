// Generated by jextract

package com.ustermetrics.scip4j.bindings;

import java.lang.invoke.*;
import java.lang.foreign.*;
import java.nio.ByteOrder;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.lang.foreign.ValueLayout.*;
import static java.lang.foreign.MemoryLayout.PathElement.*;

/**
 * {@snippet lang=c :
 * SCIP_RETCODE (*ownercreate)(SCIP *, SCIP_EXPR *, SCIP_EXPR_OWNERDATA **, SCIP_RETCODE (**)(SCIP *, SCIP_EXPR *, SCIP_EXPR_OWNERDATA **), SCIP_RETCODE (**)(SCIP *, FILE *, SCIP_EXPR *, SCIP_EXPR_OWNERDATA *), SCIP_RETCODE (**)(SCIP *, SCIP_EXPR *, SCIP_EXPR_OWNERDATA *), void *)
 * }
 */
public class SCIPcreateExpr2$ownercreate {

    SCIPcreateExpr2$ownercreate() {
        // Should not be called directly
    }

    /**
     * The function pointer signature, expressed as a functional interface
     */
    public interface Function {
        int apply(MemorySegment _x0, MemorySegment _x1, MemorySegment _x2, MemorySegment _x3, MemorySegment _x4, MemorySegment _x5, MemorySegment _x6);
    }

    private static final FunctionDescriptor $DESC = FunctionDescriptor.of(
        scip_h.C_INT,
        scip_h.C_POINTER,
        scip_h.C_POINTER,
        scip_h.C_POINTER,
        scip_h.C_POINTER,
        scip_h.C_POINTER,
        scip_h.C_POINTER,
        scip_h.C_POINTER
    );

    /**
     * The descriptor of this function pointer
     */
    public static FunctionDescriptor descriptor() {
        return $DESC;
    }

    private static final MethodHandle UP$MH = scip_h.upcallHandle(SCIPcreateExpr2$ownercreate.Function.class, "apply", $DESC);

    /**
     * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
     * The lifetime of the returned segment is managed by {@code arena}
     */
    public static MemorySegment allocate(SCIPcreateExpr2$ownercreate.Function fi, Arena arena) {
        return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
    }

    private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

    /**
     * Invoke the upcall stub {@code funcPtr}, with given parameters
     */
    public static int invoke(MemorySegment funcPtr,MemorySegment _x0, MemorySegment _x1, MemorySegment _x2, MemorySegment _x3, MemorySegment _x4, MemorySegment _x5, MemorySegment _x6) {
        try {
            return (int) DOWN$MH.invokeExact(funcPtr, _x0, _x1, _x2, _x3, _x4, _x5, _x6);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
}

