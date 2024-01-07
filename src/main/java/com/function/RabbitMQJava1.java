package com.function;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.rabbitmq.annotation.RabbitMQOutput;
import com.microsoft.azure.functions.rabbitmq.annotation.RabbitMQTrigger;

import java.util.Optional;

/**
 * Azure Functions with RabbitMQ Trigger.
 *
 * This function will be invoked when a new message is received in RabbitMQ
 * Please add com.microsoft.azure.functions:azure-functions-java-library-rabbitmq to your project dependencies
 * You may get the example and latest version in https://search.maven.org/artifact/com.microsoft.azure.functions/azure-functions-java-library-rabbitmq
 * 
 */
public class RabbitMQJava1 {
    @FunctionName("RabbitMQTriggerJava1")
    public void RabbitMQTriggerJava1(
            @RabbitMQTrigger(
                    connectionStringSetting = "RabbitMQConnectionString",
                    queueName = "functions-testkit-queue"
            ) String input,
            final ExecutionContext context) {
        context.getLogger().info("Java RabbitMQ trigger processed a request." + input);
    }

    @FunctionName("RabbitMQOutputJava1")
    public HttpResponseMessage RabbitMQOutputJava1(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            @RabbitMQOutput(
                    connectionStringSetting = "RabbitMQConnectionString",
                    queueName = "functions-testkit-queue")
            OutputBinding<String> output,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        String response = Commons.generateSampleResponse();

        // write to MQ message
        output.setValue(response);

        // HTTP response
        return request.createResponseBuilder(HttpStatus.OK).body(response).build();
    }
}