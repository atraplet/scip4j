package com.ustermetrics.optimizer4j.scip;

import lombok.*;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.ustermetrics.optimizer4j.scip.bindings.scip_h.*;

/**
 * A parameter object for the parameters of the SCIP solver
 *
 * @see <a href="https://github.com/scipopt/scip">SCIP</a>
 */
@Getter
@Builder
public class Solver {

    /**
     * The SCIP solving status
     *
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public enum Status {
        OPTIMAL(SCIP_STATUS_OPTIMAL()),
        UNKNOWN(SCIP_STATUS_UNKNOWN()),
        USERINTERRUPT(SCIP_STATUS_USERINTERRUPT()),
        NODELIMIT(SCIP_STATUS_NODELIMIT()),
        TOTALNODELIMIT(SCIP_STATUS_TOTALNODELIMIT()),
        STALLNODELIMIT(SCIP_STATUS_STALLNODELIMIT()),
        TIMELIMIT(SCIP_STATUS_TIMELIMIT()),
        MEMLIMIT(SCIP_STATUS_MEMLIMIT()),
        GAPLIMIT(SCIP_STATUS_GAPLIMIT()),
        SOLLIMIT(SCIP_STATUS_SOLLIMIT()),
        BESTSOLLIMIT(SCIP_STATUS_BESTSOLLIMIT()),
        RESTARTLIMIT(SCIP_STATUS_RESTARTLIMIT()),
        INFEASIBLE(SCIP_STATUS_INFEASIBLE()),
        UNBOUNDED(SCIP_STATUS_UNBOUNDED()),
        INFORUNBD(SCIP_STATUS_INFORUNBD()),
        TERMINATE(SCIP_STATUS_TERMINATE());

        private final int status;

        static Status valueOf(int status) {
            for (var s : values()) {
                if (s.status() == status) {
                    return s;
                }
            }

            throw new IllegalArgumentException("Unknown status");
        }

        Status(int status) {
            this.status = status;
        }

        int status() {
            return status;
        }
    }

    @Singular
    private final Map<String, Boolean> boolParams;
    @Singular
    private final Map<String, Integer> intParams;
    @Singular
    private final Map<String, Long> longintParams;
    @Singular
    private final Map<String, Double> realParams;
    @Singular
    private final Map<String, Character> charParams;
    @Singular
    private final Map<String, String> stringParams;

    /**
     * @param boolParams    the boolean parameters
     * @param intParams     the integer parameters
     * @param longintParams the long parameters
     * @param realParams    the double parameters
     * @param charParams    the character parameters
     * @param stringParams  the string parameters
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public Solver(@NonNull Map<String, Boolean> boolParams, @NonNull Map<String, Integer> intParams,
                  @NonNull Map<String, Long> longintParams, @NonNull Map<String, Double> realParams,
                  @NonNull Map<String, Character> charParams, @NonNull Map<String, String> stringParams) {
        val errMsg = "%s must not contain null keys or values";
        checkArgument(!boolParams.containsKey(null) && !boolParams.containsValue(null), errMsg, "boolParams");
        checkArgument(!intParams.containsKey(null) && !intParams.containsValue(null), errMsg, "intParams");
        checkArgument(!longintParams.containsKey(null) && !longintParams.containsValue(null), errMsg, "longintParams");
        checkArgument(!realParams.containsKey(null) && !realParams.containsValue(null), errMsg, "realParams");
        checkArgument(!charParams.containsKey(null) && !charParams.containsValue(null), errMsg, "charParams");
        checkArgument(!stringParams.containsKey(null) && !stringParams.containsValue(null), errMsg, "stringParams");

        this.boolParams = boolParams;
        this.intParams = intParams;
        this.longintParams = longintParams;
        this.realParams = realParams;
        this.charParams = charParams;
        this.stringParams = stringParams;
    }

    /**
     * @return the version of the SCIP solver
     * @see <a href="https://github.com/scipopt/scip">SCIP</a>
     */
    public static double version() {
        return SCIPversion();
    }
}
