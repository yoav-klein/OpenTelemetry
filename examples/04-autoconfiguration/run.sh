#!/bin/bash

export OTEL_TRACES_EXPORTER=jaeger-exporter
export OTEL_METRICS_EXPORTER=none
export OTEL_TRACES_EXPORTER=console


./gradlew run
