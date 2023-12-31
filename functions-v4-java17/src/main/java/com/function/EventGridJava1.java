package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.EventGridOutput;

import java.util.Optional;

/**
 * Azure Functions with Event Grid trigger.
 */
public class EventGridJava1 {
    /**
     * This function will be invoked when an event is received from Event Grid.
     */
    @FunctionName("EventGridTriggerJava1")
    public void EventGridTriggerJava1(@EventGridTrigger(name = "eventGridEvent") String message, final ExecutionContext context) {
        context.getLogger().info("Java Event Grid trigger function executed.");
        context.getLogger().info(message);
    }

    @FunctionName("EventGridOutputJava1")
    public HttpResponseMessage EventGridOutputJava1(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            @EventGridOutput(name = "outputEvent",
                    topicEndpointUri = "MyEventGridTopicUriSetting",
                    topicKeySetting = "MyEventGridTopicKeySetting")
            OutputBinding<String> outputEvent,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        String response = Commons.generateSampleResponse();

        // write to message
        outputEvent.setValue(response);

        // HTTP response
        return request.createResponseBuilder(HttpStatus.OK).body(response).build();
    }

}
