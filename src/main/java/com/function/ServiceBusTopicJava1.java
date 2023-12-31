package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.util.Optional;

/**
 * Azure Functions with Service Topic Trigger.
 */
public class ServiceBusTopicJava1 {
    /**
     * This function will be invoked when a new message is received at the Service Bus Topic.
     */
    @FunctionName("ServiceBusTopicTriggerJava1")
    public void ServiceBusTopicTriggerJava1(
        @ServiceBusTopicTrigger(
            name = "message",
            topicName = "functions-testkit-topic",
            subscriptionName = "functions-testkit-subscription",
            connection = "ServiceBusTopicConnectionString"
        )
        String message,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Service Bus Topic trigger function executed.");
        context.getLogger().info(message);
    }

    @FunctionName("ServiceBusTopicOutputJava1")
    public HttpResponseMessage ServiceBusTopicOutputJava1(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            @ServiceBusTopicOutput(
                    name = "message",
                    topicName = "functions-testkit-topic",
                    subscriptionName = "functions-testkit-subscription",
                    connection = "ServiceBusTopicConnectionString")
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