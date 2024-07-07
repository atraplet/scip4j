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
 * struct SCIP_Interval {
 *     double inf;
 *     double sup;
 * }
 * }
 */
public class SCIP_Interval {

    SCIP_Interval() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        scip_h.C_DOUBLE.withName("inf"),
        scip_h.C_DOUBLE.withName("sup")
    ).withName("SCIP_Interval");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfDouble inf$LAYOUT = (OfDouble)$LAYOUT.select(groupElement("inf"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * double inf
     * }
     */
    public static final OfDouble inf$layout() {
        return inf$LAYOUT;
    }

    private static final long inf$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * double inf
     * }
     */
    public static final long inf$offset() {
        return inf$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * double inf
     * }
     */
    public static double inf(MemorySegment struct) {
        return struct.get(inf$LAYOUT, inf$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * double inf
     * }
     */
    public static void inf(MemorySegment struct, double fieldValue) {
        struct.set(inf$LAYOUT, inf$OFFSET, fieldValue);
    }

    private static final OfDouble sup$LAYOUT = (OfDouble)$LAYOUT.select(groupElement("sup"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * double sup
     * }
     */
    public static final OfDouble sup$layout() {
        return sup$LAYOUT;
    }

    private static final long sup$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * double sup
     * }
     */
    public static final long sup$offset() {
        return sup$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * double sup
     * }
     */
    public static double sup(MemorySegment struct) {
        return struct.get(sup$LAYOUT, sup$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * double sup
     * }
     */
    public static void sup(MemorySegment struct, double fieldValue) {
        struct.set(sup$LAYOUT, sup$OFFSET, fieldValue);
    }

    /**
     * Obtains a slice of {@code arrayParam} which selects the array element at {@code index}.
     * The returned segment has address {@code arrayParam.address() + index * layout().byteSize()}
     */
    public static MemorySegment asSlice(MemorySegment array, long index) {
        return array.asSlice(layout().byteSize() * index);
    }

    /**
     * The size (in bytes) of this struct
     */
    public static long sizeof() { return layout().byteSize(); }

    /**
     * Allocate a segment of size {@code layout().byteSize()} using {@code allocator}
     */
    public static MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocate(layout());
    }

    /**
     * Allocate an array of size {@code elementCount} using {@code allocator}.
     * The returned segment has size {@code elementCount * layout().byteSize()}.
     */
    public static MemorySegment allocateArray(long elementCount, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout()));
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, Arena arena, Consumer<MemorySegment> cleanup) {
        return reinterpret(addr, 1, arena, cleanup);
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code elementCount * layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, long elementCount, Arena arena, Consumer<MemorySegment> cleanup) {
        return addr.reinterpret(layout().byteSize() * elementCount, arena, cleanup);
    }
}

