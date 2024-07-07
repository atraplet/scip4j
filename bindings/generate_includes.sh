#! /usr/bin/bash

# main
USAGE="\
Usage: generate_includes path_to_scip_precompiled_package path_to_jextract_binary"

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
TMP_INCLUDES="${SCIP4J}"/bindings/tmp_includes.txt
INCLUDES="${SCIP4J}"/bindings/includes.txt

# dump included symbols
rm -f "${SCIP_H}"
cat <<EOF >"${SCIP_H}"
#include "scip/scip.h"
#include "scip/scipdefplugins.h"
EOF
rm -f "${TMP_INCLUDES}"
rm -f "${INCLUDES}"
${JEXTRACT} \
  --include-dir "${SCIP}"/include \
  --dump-includes "${TMP_INCLUDES}" \
  "${SCIP_H}"

# select scip symbols
grep SCIP "${TMP_INCLUDES}" >"${INCLUDES}"
rm -f "${TMP_INCLUDES}"
rm -f "${SCIP_H}"
