#! /usr/bin/bash

# main
USAGE="\
Usage: generate_bindings path_to_scip_precompiled_package path_to_jextract_binary"

# read command line arguments
if [ $# -eq 2 ]; then
  SCIP4J=$(dirname "${0}")/../
  SCIP="${1}"
  JEXTRACT="${2}"
else
  echo "$USAGE"
  exit 1
fi

# define variables
SCIP_H="${SCIP4J}"/bindings/scip.h

# remove old bindings
rm -rf "${SCIP4J}"/src/main/java/com/ustermetrics/scip4j/bindings/

# generate bindings
rm -f "${SCIP_H}"
cat <<EOF >"${SCIP_H}"
#include "scip/scip.h"
#include "scip/scipdefplugins.h"
EOF
$JEXTRACT \
  --include-dir "${SCIP}"/include \
  --target-package com.ustermetrics.scip4j.bindings \
  --output "${SCIP4J}"/src/main/java \
  @"${SCIP4J}"/bindings/includes.txt "${SCIP_H}"

# cleanup
rm -f "${SCIP_H}"
