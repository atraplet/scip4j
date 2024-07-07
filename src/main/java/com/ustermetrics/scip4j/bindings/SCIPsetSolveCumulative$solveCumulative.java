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
 * SCIP_RETCODE (*solveCumulative)(int, double *, double *, double *, int *, int *, int, int, int, double, double, long long, unsigned int *, unsigned int *, unsigned int *, unsigned int *)
 * }
 */
public class SCIPsetSolveCumulative$solveCumulative {

    SCIPsetSolveCumulative$solveCumulative() {
        // Should not be called directly
    }

    /**
     * The function pointer signature, expressed as a functional interface
     */
    public interface Function {
        int apply(int _x0, MemorySegment _x1, MemorySegment _x2, MemorySegment _x3, MemorySegment _x4, MemorySegment _x5, int _x6, int _x7, int _x8, double _x9, double _x10, long _x11, MemorySegment _x12, MemorySegment _x13, MemorySegment _x14, MemorySegment _x15);
    }

    private static final FunctionDescriptor $DESC = FunctionDescriptor.of(
        scip_h.C_INT,
        scip_h.C_INT,
        scip_h.C_POINTER,
        scip_h.C_POINTER,
        scip_h.C_POINTER,
        scip_h.C_POINTER,
        scip_h.C_POINTER,
        scip_h.C_INT,
        scip_h.C_INT,
        scip_h.C_INT,
        scip_h.C_DOUBLE,
        scip_h.C_DOUBLE,
        scip_h.C_LONG_LONG,
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

    private static final MethodHandle UP$MH = scip_h.upcallHandle(SCIPsetSolveCumulative$solveCumulative.Function.class, "apply", $DESC);

    /**
     * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
     * The lifetime of the returned segment is managed by {@code arena}
     */
    public static MemorySegment allocate(SCIPsetSolveCumulative$solveCumulative.Function fi, Arena arena) {
        return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
    }

    private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

    /**
     * Invoke the upcall stub {@code funcPtr}, with given parameters
     */
    public static int invoke(MemorySegment funcPtr,int _x0, MemorySegment _x1, MemorySegment _x2, MemorySegment _x3, MemorySegment _x4, MemorySegment _x5, int _x6, int _x7, int _x8, double _x9, double _x10, long _x11, MemorySegment _x12, MemorySegment _x13, MemorySegment _x14, MemorySegment _x15) {
        try {
            return (int) DOWN$MH.invokeExact(funcPtr, _x0, _x1, _x2, _x3, _x4, _x5, _x6, _x7, _x8, _x9, _x10, _x11, _x12, _x13, _x14, _x15);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
}

