# SCIP Solver for Java

[![Build](https://github.com/atraplet/scip4j/actions/workflows/build.yml/badge.svg)](https://github.com/atraplet/scip4j/actions/workflows/build.yml)
[![Codecov](https://codecov.io/github/atraplet/scip4j/graph/badge.svg?token=S8TXRQ4UAZ)](https://codecov.io/github/atraplet/scip4j)
[![Maven Central](https://img.shields.io/maven-central/v/com.ustermetrics/scip4j)](https://central.sonatype.com/artifact/com.ustermetrics/scip4j)
[![Apache License, Version 2.0](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://github.com/atraplet/scip4j/blob/master/LICENSE)

*This library requires JDK 22 as it depends on Java's
new [Foreign Function and Memory (FFM) API](https://docs.oracle.com/en/java/javase/22/core/foreign-function-and-memory-api.html).*

scip4j (SCIP Solver for Java) is a Java library that provides an interface from the Java programming language to the
native open source mathematical programming solver [SCIP](https://www.scipopt.org). It invokes the solver
through Java's
new [Foreign Function and Memory (FFM) API](https://docs.oracle.com/en/java/javase/22/core/foreign-function-and-memory-api.html).

## Usage

### Dependency

Add the latest version from [Maven Central](https://central.sonatype.com/artifact/com.ustermetrics/scip4j) to
your `pom.xml`

```
<dependency>
    <groupId>com.ustermetrics</groupId>
    <artifactId>scip4j</artifactId>
    <version>x.y.z</version>
</dependency>
```

### Native Library

Either add the latest version of [scip4j-native](https://github.com/atraplet/scip4j-native)
from [Maven Central](https://central.sonatype.com/artifact/com.ustermetrics/scip4j-native) to
your `pom.xml`

```
<dependency>
    <groupId>com.ustermetrics</groupId>
    <artifactId>scip4j-native</artifactId>
    <version>x.y.z</version>
    <scope>runtime</scope>
</dependency>
```

or install the native solver on the machine and add the location to the `java.library.path`. scip4j dynamically loads
the native solver.

### Run Code

Since scip4j invokes some restricted methods of the FFM API, use `--enable-native-access=com.ustermetrics.scip4j`
or `--enable-native-access=ALL-UNNAMED` (if you are not using the Java Platform Module System) to avoid warnings from
the Java runtime.

## Build

### Java bindings

The directory `./bindings` contains the files and scripts needed to generate the Java bindings. The actual bindings are
under `./src/main/java` in the package `com.ustermetrics.scip4j.bindings`.

The scripts depend on the [jextract](https://jdk.java.net/jextract/) tool, which mechanically generates Java bindings
from native library headers.

The bindings are generated in two steps: First, `./bindings/generate_includes.sh` generates the dumps of the included
symbols in the `includes.txt` file. Replace absolute platform dependent path with relative platform independent path in
the comments. Second, `./bindings/generate_bindings.sh` generates the actual Java bindings. Finally,
add `NativeLoader.loadLibrary` and remove the platform dependent layout constants.

## Release

Update the version in the `pom.xml`, create a tag, and push it by running

```
export VERSION=X.Y.Z
git checkout --detach HEAD
sed -i -E "s/<version>[0-9]+\-SNAPSHOT<\/version>/<version>$VERSION<\/version>/g" pom.xml
git commit -m "v$VERSION" pom.xml
git tag v$VERSION
git push origin v$VERSION
```

This will trigger the upload of the package to Maven Central via GitHub Actions.

Then, go to the GitHub repository [releases page](https://github.com/atraplet/scip4j/releases) and update the release.

## Credits

This project is based on the native open source mathematical programming
solver [SCIP](https://www.scipopt.org),
which is developed and maintained by the [current team members](https://scipopt.org/index.php#developers).
