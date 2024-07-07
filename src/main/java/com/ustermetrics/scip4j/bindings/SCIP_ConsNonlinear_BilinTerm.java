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
 * struct SCIP_ConsNonlinear_BilinTerm {
 *     SCIP_VAR *x;
 *     SCIP_VAR *y;
 *     union {
 *         SCIP_CONSNONLINEAR_AUXEXPR **exprs;
 *         SCIP_VAR *var;
 *     } aux;
 *     int nauxexprs;
 *     int auxexprssize;
 *     int nlockspos;
 *     int nlocksneg;
 *     unsigned int existing;
 * }
 * }
 */
public class SCIP_ConsNonlinear_BilinTerm {

    SCIP_ConsNonlinear_BilinTerm() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        scip_h.C_POINTER.withName("x"),
        scip_h.C_POINTER.withName("y"),
        SCIP_ConsNonlinear_BilinTerm.aux.layout().withName("aux"),
        scip_h.C_INT.withName("nauxexprs"),
        scip_h.C_INT.withName("auxexprssize"),
        scip_h.C_INT.withName("nlockspos"),
        scip_h.C_INT.withName("nlocksneg"),
        scip_h.C_INT.withName("existing"),
        MemoryLayout.paddingLayout(4)
    ).withName("SCIP_ConsNonlinear_BilinTerm");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final AddressLayout x$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("x"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * SCIP_VAR *x
     * }
     */
    public static final AddressLayout x$layout() {
        return x$LAYOUT;
    }

    private static final long x$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * SCIP_VAR *x
     * }
     */
    public static final long x$offset() {
        return x$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * SCIP_VAR *x
     * }
     */
    public static MemorySegment x(MemorySegment struct) {
        return struct.get(x$LAYOUT, x$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * SCIP_VAR *x
     * }
     */
    public static void x(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(x$LAYOUT, x$OFFSET, fieldValue);
    }

    private static final AddressLayout y$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("y"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * SCIP_VAR *y
     * }
     */
    public static final AddressLayout y$layout() {
        return y$LAYOUT;
    }

    private static final long y$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * SCIP_VAR *y
     * }
     */
    public static final long y$offset() {
        return y$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * SCIP_VAR *y
     * }
     */
    public static MemorySegment y(MemorySegment struct) {
        return struct.get(y$LAYOUT, y$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * SCIP_VAR *y
     * }
     */
    public static void y(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(y$LAYOUT, y$OFFSET, fieldValue);
    }

    /**
     * {@snippet lang=c :
     * union {
     *     SCIP_CONSNONLINEAR_AUXEXPR **exprs;
     *     SCIP_VAR *var;
     * }
     * }
     */
    public static class aux {

        aux() {
            // Should not be called directly
        }

        private static final GroupLayout $LAYOUT = MemoryLayout.unionLayout(
            scip_h.C_POINTER.withName("exprs"),
            scip_h.C_POINTER.withName("var")
        ).withName("$anon$82:4");

        /**
         * The layout of this union
         */
        public static final GroupLayout layout() {
            return $LAYOUT;
        }

        private static final AddressLayout exprs$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("exprs"));

        /**
         * Layout for field:
         * {@snippet lang=c :
         * SCIP_CONSNONLINEAR_AUXEXPR **exprs
         * }
         */
        public static final AddressLayout exprs$layout() {
            return exprs$LAYOUT;
        }

        private static final long exprs$OFFSET = 0;

        /**
         * Offset for field:
         * {@snippet lang=c :
         * SCIP_CONSNONLINEAR_AUXEXPR **exprs
         * }
         */
        public static final long exprs$offset() {
            return exprs$OFFSET;
        }

        /**
         * Getter for field:
         * {@snippet lang=c :
         * SCIP_CONSNONLINEAR_AUXEXPR **exprs
         * }
         */
        public static MemorySegment exprs(MemorySegment union) {
            return union.get(exprs$LAYOUT, exprs$OFFSET);
        }

        /**
         * Setter for field:
         * {@snippet lang=c :
         * SCIP_CONSNONLINEAR_AUXEXPR **exprs
         * }
         */
        public static void exprs(MemorySegment union, MemorySegment fieldValue) {
            union.set(exprs$LAYOUT, exprs$OFFSET, fieldValue);
        }

        private static final AddressLayout var_$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("var"));

        /**
         * Layout for field:
         * {@snippet lang=c :
         * SCIP_VAR *var
         * }
         */
        public static final AddressLayout var_$layout() {
            return var_$LAYOUT;
        }

        private static final long var_$OFFSET = 0;

        /**
         * Offset for field:
         * {@snippet lang=c :
         * SCIP_VAR *var
         * }
         */
        public static final long var_$offset() {
            return var_$OFFSET;
        }

        /**
         * Getter for field:
         * {@snippet lang=c :
         * SCIP_VAR *var
         * }
         */
        public static MemorySegment var_(MemorySegment union) {
            return union.get(var_$LAYOUT, var_$OFFSET);
        }

        /**
         * Setter for field:
         * {@snippet lang=c :
         * SCIP_VAR *var
         * }
         */
        public static void var_(MemorySegment union, MemorySegment fieldValue) {
            union.set(var_$LAYOUT, var_$OFFSET, fieldValue);
        }

        /**
         * Obtains a slice of {@code arrayParam} which selects the array element at {@code index}.
         * The returned segment has address {@code arrayParam.address() + index * layout().byteSize()}
         */
        public static MemorySegment asSlice(MemorySegment array, long index) {
            return array.asSlice(layout().byteSize() * index);
        }

        /**
         * The size (in bytes) of this union
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

    private static final GroupLayout aux$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("aux"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * union {
     *     SCIP_CONSNONLINEAR_AUXEXPR **exprs;
     *     SCIP_VAR *var;
     * } aux
     * }
     */
    public static final GroupLayout aux$layout() {
        return aux$LAYOUT;
    }

    private static final long aux$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * union {
     *     SCIP_CONSNONLINEAR_AUXEXPR **exprs;
     *     SCIP_VAR *var;
     * } aux
     * }
     */
    public static final long aux$offset() {
        return aux$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * union {
     *     SCIP_CONSNONLINEAR_AUXEXPR **exprs;
     *     SCIP_VAR *var;
     * } aux
     * }
     */
    public static MemorySegment aux(MemorySegment struct) {
        return struct.asSlice(aux$OFFSET, aux$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * union {
     *     SCIP_CONSNONLINEAR_AUXEXPR **exprs;
     *     SCIP_VAR *var;
     * } aux
     * }
     */
    public static void aux(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, aux$OFFSET, aux$LAYOUT.byteSize());
    }

    private static final OfInt nauxexprs$LAYOUT = (OfInt)$LAYOUT.select(groupElement("nauxexprs"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int nauxexprs
     * }
     */
    public static final OfInt nauxexprs$layout() {
        return nauxexprs$LAYOUT;
    }

    private static final long nauxexprs$OFFSET = 24;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int nauxexprs
     * }
     */
    public static final long nauxexprs$offset() {
        return nauxexprs$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int nauxexprs
     * }
     */
    public static int nauxexprs(MemorySegment struct) {
        return struct.get(nauxexprs$LAYOUT, nauxexprs$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int nauxexprs
     * }
     */
    public static void nauxexprs(MemorySegment struct, int fieldValue) {
        struct.set(nauxexprs$LAYOUT, nauxexprs$OFFSET, fieldValue);
    }

    private static final OfInt auxexprssize$LAYOUT = (OfInt)$LAYOUT.select(groupElement("auxexprssize"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int auxexprssize
     * }
     */
    public static final OfInt auxexprssize$layout() {
        return auxexprssize$LAYOUT;
    }

    private static final long auxexprssize$OFFSET = 28;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int auxexprssize
     * }
     */
    public static final long auxexprssize$offset() {
        return auxexprssize$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int auxexprssize
     * }
     */
    public static int auxexprssize(MemorySegment struct) {
        return struct.get(auxexprssize$LAYOUT, auxexprssize$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int auxexprssize
     * }
     */
    public static void auxexprssize(MemorySegment struct, int fieldValue) {
        struct.set(auxexprssize$LAYOUT, auxexprssize$OFFSET, fieldValue);
    }

    private static final OfInt nlockspos$LAYOUT = (OfInt)$LAYOUT.select(groupElement("nlockspos"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int nlockspos
     * }
     */
    public static final OfInt nlockspos$layout() {
        return nlockspos$LAYOUT;
    }

    private static final long nlockspos$OFFSET = 32;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int nlockspos
     * }
     */
    public static final long nlockspos$offset() {
        return nlockspos$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int nlockspos
     * }
     */
    public static int nlockspos(MemorySegment struct) {
        return struct.get(nlockspos$LAYOUT, nlockspos$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int nlockspos
     * }
     */
    public static void nlockspos(MemorySegment struct, int fieldValue) {
        struct.set(nlockspos$LAYOUT, nlockspos$OFFSET, fieldValue);
    }

    private static final OfInt nlocksneg$LAYOUT = (OfInt)$LAYOUT.select(groupElement("nlocksneg"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int nlocksneg
     * }
     */
    public static final OfInt nlocksneg$layout() {
        return nlocksneg$LAYOUT;
    }

    private static final long nlocksneg$OFFSET = 36;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int nlocksneg
     * }
     */
    public static final long nlocksneg$offset() {
        return nlocksneg$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int nlocksneg
     * }
     */
    public static int nlocksneg(MemorySegment struct) {
        return struct.get(nlocksneg$LAYOUT, nlocksneg$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int nlocksneg
     * }
     */
    public static void nlocksneg(MemorySegment struct, int fieldValue) {
        struct.set(nlocksneg$LAYOUT, nlocksneg$OFFSET, fieldValue);
    }

    private static final OfInt existing$LAYOUT = (OfInt)$LAYOUT.select(groupElement("existing"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * unsigned int existing
     * }
     */
    public static final OfInt existing$layout() {
        return existing$LAYOUT;
    }

    private static final long existing$OFFSET = 40;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * unsigned int existing
     * }
     */
    public static final long existing$offset() {
        return existing$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * unsigned int existing
     * }
     */
    public static int existing(MemorySegment struct) {
        return struct.get(existing$LAYOUT, existing$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * unsigned int existing
     * }
     */
    public static void existing(MemorySegment struct, int fieldValue) {
        struct.set(existing$LAYOUT, existing$OFFSET, fieldValue);
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

