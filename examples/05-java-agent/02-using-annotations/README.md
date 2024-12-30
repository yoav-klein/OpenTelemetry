# Java Agent
---

In this project we demonstrate the use of the Java agent for Zero-code configuration of the SDK.

## Quick Reminder
The OpenTelemetry Java agent contains _instrumentations_ for many popular Java libraries and frameworks,
so when you run your application that uses those libraries or frameworks with the Java agent, the Java agent
will generate telemetry for those libraries/frameworks.

This application accesses an object in S3. the Java agent includes instrumentation for AWS SDK, 
so when running the application with the Java agent, it will produce telemetry.

## Run
First, make sure you have a S3 bucket with an object inside.
Then, fill in the blanks in the `run.sh` script and run it.

You'll see that it writes a Span to the console.
