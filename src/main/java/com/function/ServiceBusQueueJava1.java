package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

/**
 * Azure Functions with Service Bus Trigger.
 */
public class ServiceBusQueueJava1 {
    /**
     * This function will be invoked when a new message is received at the Service Bus Queue.
     */
    @FunctionName("ServiceBusQueueTriggerJava1")
    public void ServiceBusQueueTriggerJava1(
            @ServiceBusQueueTrigger(
                    name = "message",
                    queueName = "functions-testkit-queue",
                    connection = "ServiceBusQueueConnectionString")
            String message,
            final ExecutionContext context
    ) {
        context.getLogger().info("Java Service Bus Queue trigger function executed.");
        context.getLogger().info(message);
    }

    @FunctionName("ServiceBusQueueOutputJava1")
    public HttpResponseMessage ServiceBusQueueOutputJava1(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            @ServiceBusQueueOutput(
                    name = "message",
                    queueName = "functions-testkit-queue",
                    connection = "ServiceBusQueueConnectionString")
            OutputBinding<String> message,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        String response = Commons.generateSampleResponse();

        // write to service bus(queue)
        message.setValue(response);

        // HTTP response
        return request.createResponseBuilder(HttpStatus.OK).body(response).build();
    }
}