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
 * typedef struct SCIP_NlpParam {
 *     double lobjlimit;
 *     double feastol;
 *     double opttol;
 *     double solvertol;
 *     double timelimit;
 *     int iterlimit;
 *     unsigned short verblevel;
 *     SCIP_NLPPARAM_FASTFAIL fastfail;
 *     unsigned int expectinfeas;
 *     unsigned int warmstart;
 *     const char *caller;
 * } SCIP_NLPPARAM
 * }
 */
public class SCIP_NLPPARAM$170 extends SCIP_NlpParam {

    SCIP_NLPPARAM$170() {
        // Should not be called directly
    }
}
