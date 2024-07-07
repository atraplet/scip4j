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
 * SCIP_RETCODE (*print)(SCIP *, SCIP_EXPR *, SCIP_EXPRITER_STAGE, int, unsigned int, FILE *)
 * }
 */
public class SCIPexprhdlrSetPrint$print {

    SCIPexprhdlrSetPrint$print() {
        // Should not be called directly
    }

    /**
     * The function pointer signature, expressed as a functional interface
     */
    public interface Function {
        int apply(MemorySegment _x0, MemorySegment _x1, int _x2, int _x3, int _x4, MemorySegment _x5);
    }

    private static final FunctionDescriptor $DESC = FunctionDescriptor.of(
        scip_h.C_INT,
        scip_h.C_POINTER,
        scip_h.C_POINTER,
        scip_h.C_INT,
        scip_h.C_INT,
        scip_h.C_INT,
        scip_h.C_POINTER
    );

    /**
     * The descriptor of this function pointer
     */
    public static FunctionDescriptor descriptor() {
        return $DESC;
    }

    private static final MethodHandle UP$MH = scip_h.upcallHandle(SCIPexprhdlrSetPrint$print.Function.class, "apply", $DESC);

    /**
     * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
     * The lifetime of the returned segment is managed by {@code arena}
     */
    public static MemorySegment allocate(SCIPexprhdlrSetPrint$print.Function fi, Arena arena) {
        return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
    }

    private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

    /**
     * Invoke the upcall stub {@code funcPtr}, with given parameters
     */
    public static int invoke(MemorySegment funcPtr,MemorySegment _x0, MemorySegment _x1, int _x2, int _x3, int _x4, MemorySegment _x5) {
        try {
            return (int) DOWN$MH.invokeExact(funcPtr, _x0, _x1, _x2, _x3, _x4, _x5);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
}

