#!/bin/bash

export OTEL_TRACES_EXPORTER=my-jaeger-exporter
export OTEL_METRICS_EXPORTER=none
export OTEL_LOGS_EXPORTER=none
export OTEL_SERVICE_NAME=my-service

./gradlew run
