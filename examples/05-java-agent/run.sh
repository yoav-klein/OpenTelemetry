#!/bin/bash

export AWS_ACCESS_KEY_ID=xxx
export AWS_SECRET_ACCESS_KEY=xxx
export S3_KEY=some-object
export S3_BUCKET=my=bucket

export OTEL_TRACES_EXPORTER=console
export OTEL_METRICS_EXPORTER=none

./gradlew run
