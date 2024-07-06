package com.ustermetrics.scip4j;

import lombok.NonNull;

import java.lang.foreign.MemorySegment;

import static com.ustermetrics.optimizer4j.scip.bindings.scip_h.*;

/**
 * A variable of a SCIP problem
 */
public class Variable {

    /**
     * The type of a variable
     *
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public enum VariableType {
        BINARY(SCIP_VARTYPE_BINARY()),
        INTEGER(SCIP_VARTYPE_INTEGER()),
        IMPLINT(SCIP_VARTYPE_IMPLINT()),
        CONTINUOUS(SCIP_VARTYPE_CONTINUOUS());

        private final int type;

        static VariableType valueOf(int type) {
            for (var t : values()) {
                if (t.type() == type) {
                    return t;
                }
            }

            throw new IllegalArgumentException("Unknown variable type");
        }

        VariableType(int type) {
            this.type = type;
        }

        int type() {
            return type;
        }
    }

    private final MemorySegment segment;

    Variable(@NonNull MemorySegment segment) {
        this.segment = segment;
    }

    MemorySegment segment() {
        return segment;
    }

    MemorySegment address() {
        return segment.get(C_POINTER, 0);
    }
}
