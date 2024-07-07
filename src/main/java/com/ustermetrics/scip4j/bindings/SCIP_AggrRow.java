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
 * struct SCIP_AggrRow {
 *     double *vals;
 *     int *inds;
 *     int *rowsinds;
 *     int *slacksign;
 *     double *rowweights;
 *     double rhshi;
 *     double rhslo;
 *     int nnz;
 *     int nrows;
 *     int rowssize;
 *     int rank;
 *     unsigned int local;
 * }
 * }
 */
public class SCIP_AggrRow {

    SCIP_AggrRow() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        scip_h.C_POINTER.withName("vals"),
        scip_h.C_POINTER.withName("inds"),
        scip_h.C_POINTER.withName("rowsinds"),
        scip_h.C_POINTER.withName("slacksign"),
        scip_h.C_POINTER.withName("rowweights"),
        scip_h.C_DOUBLE.withName("rhshi"),
        scip_h.C_DOUBLE.withName("rhslo"),
        scip_h.C_INT.withName("nnz"),
        scip_h.C_INT.withName("nrows"),
        scip_h.C_INT.withName("rowssize"),
        scip_h.C_INT.withName("rank"),
        scip_h.C_INT.withName("local"),
        MemoryLayout.paddingLayout(4)
    ).withName("SCIP_AggrRow");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final AddressLayout vals$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("vals"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * double *vals
     * }
     */
    public static final AddressLayout vals$layout() {
        return vals$LAYOUT;
    }

    private static final long vals$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * double *vals
     * }
     */
    public static final long vals$offset() {
        return vals$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * double *vals
     * }
     */
    public static MemorySegment vals(MemorySegment struct) {
        return struct.get(vals$LAYOUT, vals$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * double *vals
     * }
     */
    public static void vals(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(vals$LAYOUT, vals$OFFSET, fieldValue);
    }

    private static final AddressLayout inds$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("inds"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int *inds
     * }
     */
    public static final AddressLayout inds$layout() {
        return inds$LAYOUT;
    }

    private static final long inds$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int *inds
     * }
     */
    public static final long inds$offset() {
        return inds$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int *inds
     * }
     */
    public static MemorySegment inds(MemorySegment struct) {
        return struct.get(inds$LAYOUT, inds$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int *inds
     * }
     */
    public static void inds(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(inds$LAYOUT, inds$OFFSET, fieldValue);
    }

    private static final AddressLayout rowsinds$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("rowsinds"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int *rowsinds
     * }
     */
    public static final AddressLayout rowsinds$layout() {
        return rowsinds$LAYOUT;
    }

    private static final long rowsinds$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int *rowsinds
     * }
     */
    public static final long rowsinds$offset() {
        return rowsinds$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int *rowsinds
     * }
     */
    public static MemorySegment rowsinds(MemorySegment struct) {
        return struct.get(rowsinds$LAYOUT, rowsinds$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int *rowsinds
     * }
     */
    public static void rowsinds(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(rowsinds$LAYOUT, rowsinds$OFFSET, fieldValue);
    }

    private static final AddressLayout slacksign$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("slacksign"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int *slacksign
     * }
     */
    public static final AddressLayout slacksign$layout() {
        return slacksign$LAYOUT;
    }

    private static final long slacksign$OFFSET = 24;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int *slacksign
     * }
     */
    public static final long slacksign$offset() {
        return slacksign$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int *slacksign
     * }
     */
    public static MemorySegment slacksign(MemorySegment struct) {
        return struct.get(slacksign$LAYOUT, slacksign$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int *slacksign
     * }
     */
    public static void slacksign(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(slacksign$LAYOUT, slacksign$OFFSET, fieldValue);
    }

    private static final AddressLayout rowweights$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("rowweights"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * double *rowweights
     * }
     */
    public static final AddressLayout rowweights$layout() {
        return rowweights$LAYOUT;
    }

    private static final long rowweights$OFFSET = 32;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * double *rowweights
     * }
     */
    public static final long rowweights$offset() {
        return rowweights$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * double *rowweights
     * }
     */
    public static MemorySegment rowweights(MemorySegment struct) {
        return struct.get(rowweights$LAYOUT, rowweights$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * double *rowweights
     * }
     */
    public static void rowweights(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(rowweights$LAYOUT, rowweights$OFFSET, fieldValue);
    }

    private static final OfDouble rhshi$LAYOUT = (OfDouble)$LAYOUT.select(groupElement("rhshi"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * double rhshi
     * }
     */
    public static final OfDouble rhshi$layout() {
        return rhshi$LAYOUT;
    }

    private static final long rhshi$OFFSET = 40;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * double rhshi
     * }
     */
    public static final long rhshi$offset() {
        return rhshi$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * double rhshi
     * }
     */
    public static double rhshi(MemorySegment struct) {
        return struct.get(rhshi$LAYOUT, rhshi$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * double rhshi
     * }
     */
    public static void rhshi(MemorySegment struct, double fieldValue) {
        struct.set(rhshi$LAYOUT, rhshi$OFFSET, fieldValue);
    }

    private static final OfDouble rhslo$LAYOUT = (OfDouble)$LAYOUT.select(groupElement("rhslo"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * double rhslo
     * }
     */
    public static final OfDouble rhslo$layout() {
        return rhslo$LAYOUT;
    }

    private static final long rhslo$OFFSET = 48;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * double rhslo
     * }
     */
    public static final long rhslo$offset() {
        return rhslo$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * double rhslo
     * }
     */
    public static double rhslo(MemorySegment struct) {
        return struct.get(rhslo$LAYOUT, rhslo$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * double rhslo
     * }
     */
    public static void rhslo(MemorySegment struct, double fieldValue) {
        struct.set(rhslo$LAYOUT, rhslo$OFFSET, fieldValue);
    }

    private static final OfInt nnz$LAYOUT = (OfInt)$LAYOUT.select(groupElement("nnz"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int nnz
     * }
     */
    public static final OfInt nnz$layout() {
        return nnz$LAYOUT;
    }

    private static final long nnz$OFFSET = 56;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int nnz
     * }
     */
    public static final long nnz$offset() {
        return nnz$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int nnz
     * }
     */
    public static int nnz(MemorySegment struct) {
        return struct.get(nnz$LAYOUT, nnz$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int nnz
     * }
     */
    public static void nnz(MemorySegment struct, int fieldValue) {
        struct.set(nnz$LAYOUT, nnz$OFFSET, fieldValue);
    }

    private static final OfInt nrows$LAYOUT = (OfInt)$LAYOUT.select(groupElement("nrows"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int nrows
     * }
     */
    public static final OfInt nrows$layout() {
        return nrows$LAYOUT;
    }

    private static final long nrows$OFFSET = 60;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int nrows
     * }
     */
    public static final long nrows$offset() {
        return nrows$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int nrows
     * }
     */
    public static int nrows(MemorySegment struct) {
        return struct.get(nrows$LAYOUT, nrows$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int nrows
     * }
     */
    public static void nrows(MemorySegment struct, int fieldValue) {
        struct.set(nrows$LAYOUT, nrows$OFFSET, fieldValue);
    }

    private static final OfInt rowssize$LAYOUT = (OfInt)$LAYOUT.select(groupElement("rowssize"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int rowssize
     * }
     */
    public static final OfInt rowssize$layout() {
        return rowssize$LAYOUT;
    }

    private static final long rowssize$OFFSET = 64;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int rowssize
     * }
     */
    public static final long rowssize$offset() {
        return rowssize$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int rowssize
     * }
     */
    public static int rowssize(MemorySegment struct) {
        return struct.get(rowssize$LAYOUT, rowssize$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int rowssize
     * }
     */
    public static void rowssize(MemorySegment struct, int fieldValue) {
        struct.set(rowssize$LAYOUT, rowssize$OFFSET, fieldValue);
    }

    private static final OfInt rank$LAYOUT = (OfInt)$LAYOUT.select(groupElement("rank"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int rank
     * }
     */
    public static final OfInt rank$layout() {
        return rank$LAYOUT;
    }

    private static final long rank$OFFSET = 68;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int rank
     * }
     */
    public static final long rank$offset() {
        return rank$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int rank
     * }
     */
    public static int rank(MemorySegment struct) {
        return struct.get(rank$LAYOUT, rank$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int rank
     * }
     */
    public static void rank(MemorySegment struct, int fieldValue) {
        struct.set(rank$LAYOUT, rank$OFFSET, fieldValue);
    }

    private static final OfInt local$LAYOUT = (OfInt)$LAYOUT.select(groupElement("local"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * unsigned int local
     * }
     */
    public static final OfInt local$layout() {
        return local$LAYOUT;
    }

    private static final long local$OFFSET = 72;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * unsigned int local
     * }
     */
    public static final long local$offset() {
        return local$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * unsigned int local
     * }
     */
    public static int local(MemorySegment struct) {
        return struct.get(local$LAYOUT, local$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * unsigned int local
     * }
     */
    public static void local(MemorySegment struct, int fieldValue) {
        struct.set(local$LAYOUT, local$OFFSET, fieldValue);
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
