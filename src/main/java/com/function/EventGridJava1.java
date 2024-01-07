package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.EventGridOutput;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

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
                    topicEndpointUri = "EventGridConnectionUri",
                    topicKeySetting = "EventGridConnectionKey")
            OutputBinding<String> outputEvent,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        String response = Commons.generateSampleResponse();

        // build Event Grid schema JSON
        StringBuilder sb = new StringBuilder();
        sb.append("[{");
        // sb.append(String.format("\"topic\": \"%s\",", "/subscriptions/.../"));
        sb.append(String.format("\"subject\": \"%s\",", "/blobServices/default/containers/oc2d2817345i200097container/blobs/oc2d2817345i20002296blob"));
        sb.append(String.format("\"id\": \"%s\",", UUID.randomUUID().toString()));
        sb.append(String.format("\"eventType\": \"%s\",",  "Microsoft.Storage.BlobCreated"));
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXXZ");
        String datetimeStr = now.format(dtf).replace("+0000", "");
        sb.append(String.format("\"eventTime\": \"%s\", ", datetimeStr));
        sb.append(String.format("\"data\":{"));
        sb.append(String.format(" \"sample\": \"%s\"", response));
        sb.append(String.format("},"));
        sb.append(String.format("\"dataVersion\": \"1\","));
        sb.append(String.format("\"metadataVersion\": \"1\""));
        sb.append("}]");

        context.getLogger().info(sb.toString());

        // write to message
        outputEvent.setValue(sb.toString());

        // HTTP response
        return request.createResponseBuilder(HttpStatus.OK).body(sb.toString()).build();
    }

}
