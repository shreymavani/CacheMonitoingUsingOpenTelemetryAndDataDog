# Open Telemetry Integration with DataDog

## Steps : 

1. Make jar file for your code.
2. Download dd-java-agent.jar using below step : 

    ```
    wget -O dd-java-agent.jar https://dtdg.co/latest-java-tracer
    ```
3. Add Java Tracer to JVM :

   ```
   java -javaagent:/path/to/dd-java-agent.jar -Ddd.trace.otel.enabled=true -jar /path/to/yourcode.jar
   ```


## References : 

1. https://docs.datadoghq.com/tracing/trace_collection/otel_instrumentation/java/
2. https://opentelemetry.io/docs/instrumentation/java/manual/
3. https://docs.datadoghq.com/tracing/trace_collection/dd_libraries/java/?tab=springboot