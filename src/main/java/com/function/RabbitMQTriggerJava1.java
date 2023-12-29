package com.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.rabbitmq.annotation.RabbitMQTrigger;

/**
 * Azure Functions with RabbitMQ Trigger.
 *
 * This function will be invoked when a new message is received in RabbitMQ
 * Please add com.microsoft.azure.functions:azure-functions-java-library-rabbitmq to your project dependencies
 * You may get the example and latest version in https://search.maven.org/artifact/com.microsoft.azure.functions/azure-functions-java-library-rabbitmq
 * 
 */
public class RabbitMQTriggerJava1 {
    @FunctionName("RabbitMQTriggerJava1")
    public void run(
            @RabbitMQTrigger(
                    connectionStringSetting = "AzureWebJobsStorage",
                    queueName = "myqueue"
            ) String input,
            final ExecutionContext context) {
        context.getLogger().info("Java RabbitMQ trigger processed a request." + input);
    }
}