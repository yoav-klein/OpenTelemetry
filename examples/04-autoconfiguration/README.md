# Autoconfiguration of SDK
---

Here we demonstrate using zero-code configuration of the SDK rather than programmatically configuring it.

Run the `run.sh` script to run the application. In this script we define environment variables
which are read by the autoconfigure module to automatically configure the SDK.

More specifically, we configure the type of trace exporter to `console` so that traces will be printed to console.