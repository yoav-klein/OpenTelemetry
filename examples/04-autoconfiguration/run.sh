#!/bin/bash

export OTEL_TRACES_EXPORTER=jaeger-exporter
export OTEL_METRICS_EXPORTER=none


./gradlew run
