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
 * SCIP_RETCODE (*presolexec)(SCIP *, SCIP_PRESOL *, int, SCIP_PRESOLTIMING, int, int, int, int, int, int, int, int, int, int, int *, int *, int *, int *, int *, int *, int *, int *, int *, int *, SCIP_RESULT *)
 * }
 */
public class SCIPincludePresolBasic$presolexec {

    SCIPincludePresolBasic$presolexec() {
        // Should not be called directly
    }

    /**
     * The function pointer signature, expressed as a functional interface
     */
    public interface Function {
        int apply(MemorySegment _x0, MemorySegment _x1, int _x2, int _x3, int _x4, int _x5, int _x6, int _x7, int _x8, int _x9, int _x10, int _x11, int _x12, int _x13, MemorySegment _x14, MemorySegment _x15, MemorySegment _x16, MemorySegment _x17, MemorySegment _x18, MemorySegment _x19, MemorySegment _x20, MemorySegment _x21, MemorySegment _x22, MemorySegment _x23, MemorySegment _x24);
    }

    private static final FunctionDescriptor $DESC = FunctionDescriptor.of(
        scip_h.C_INT,
        scip_h.C_POINTER,
        scip_h.C_POINTER,
        scip_h.C_INT,
        scip_h.C_INT,
        scip_h.C_INT,
        scip_h.C_INT,
        scip_h.C_INT,
        scip_h.C_INT,
        scip_h.C_INT,
        scip_h.C_INT,
        scip_h.C_INT,
        scip_h.C_INT,
        scip_h.C_INT,
        scip_h.C_INT,
        scip_h.C_POINTER,
        scip_h.C_POINTER,
        scip_h.C_POINTER,
        scip_h.C_POINTER,
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

    private static final MethodHandle UP$MH = scip_h.upcallHandle(SCIPincludePresolBasic$presolexec.Function.class, "apply", $DESC);

    /**
     * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
     * The lifetime of the returned segment is managed by {@code arena}
     */
    public static MemorySegment allocate(SCIPincludePresolBasic$presolexec.Function fi, Arena arena) {
        return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
    }

    private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

    /**
     * Invoke the upcall stub {@code funcPtr}, with given parameters
     */
    public static int invoke(MemorySegment funcPtr,MemorySegment _x0, MemorySegment _x1, int _x2, int _x3, int _x4, int _x5, int _x6, int _x7, int _x8, int _x9, int _x10, int _x11, int _x12, int _x13, MemorySegment _x14, MemorySegment _x15, MemorySegment _x16, MemorySegment _x17, MemorySegment _x18, MemorySegment _x19, MemorySegment _x20, MemorySegment _x21, MemorySegment _x22, MemorySegment _x23, MemorySegment _x24) {
        try {
            return (int) DOWN$MH.invokeExact(funcPtr, _x0, _x1, _x2, _x3, _x4, _x5, _x6, _x7, _x8, _x9, _x10, _x11, _x12, _x13, _x14, _x15, _x16, _x17, _x18, _x19, _x20, _x21, _x22, _x23, _x24);
        } catch (Throwable ex$) {
            throw new AssertionError("should not reach here", ex$);
        }
    }
}

