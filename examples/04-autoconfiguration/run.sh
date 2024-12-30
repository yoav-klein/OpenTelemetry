#!/bin/bash

export OTEL_TRACES_EXPORTER=console
export OTEL_METRICS_EXPORTER=none
export OTEL_TRACES_EXPORTER=console

./gradlew run
