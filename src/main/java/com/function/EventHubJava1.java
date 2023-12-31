package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.util.*;

/**
 * Azure Functions with Event Hub trigger.
 */
public class EventHubJava1 {
    /**
     * This function will be invoked when an event is received from Event Hub.
     */
    @FunctionName("EventHubTriggerJava1")
    public void EventHubTriggerJava1(
        @EventHubTrigger(name = "message",
                dataType = "string", // binary  https://learn.microsoft.com/java/api/com.microsoft.azure.functions.annotation.eventhuboutput?view=azure-java-stable
                eventHubName = "functions-testkit-eventhub",
                connection = "EventHubsConnectionString",
                consumerGroup = "$Default",
                cardinality = Cardinality.MANY)
        List<String> message,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Event Hub trigger function executed.");
        context.getLogger().info("Length:" + message.size());
        message.forEach(singleMessage -> context.getLogger().info(singleMessage));
    }

    @FunctionName("EventHubOutputJava1")
    public HttpResponseMessage EventHubOutputJava1(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            @EventHubOutput(name = "event",
                    eventHubName = "functions-testkit-eventhub",
                    connection = "EventHubsConnectionString")
            OutputBinding<String> outputItem,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        String response = Commons.generateSampleResponse();

        // write to blob
        outputItem.setValue(response);

        // HTTP response
        return request.createResponseBuilder(HttpStatus.OK).body(response).build();
    }

}
