package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with Service Topic Trigger.
 */
public class ServiceBusTopicTriggerJava1 {
    /**
     * This function will be invoked when a new message is received at the Service Bus Topic.
     */
    @FunctionName("ServiceBusTopicTriggerJava1")
    public void run(
        @ServiceBusTopicTrigger(
            name = "message",
            topicName = "mytopic",
            subscriptionName = "mysubscription",
            connection = "AzureWebJobsStorage"
        )
        String message,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Service Bus Topic trigger function executed.");
        context.getLogger().info(message);
    }
}