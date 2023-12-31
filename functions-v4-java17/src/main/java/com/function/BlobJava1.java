package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Date;
import java.util.UUID;

/**
 * Azure Functions with Azure Blob trigger.
 */
public class BlobJava1 {
    /**
     * This function will be invoked when a new or updated blob is detected at the specified path. The blob contents are provided as input to this function.
     */
    @FunctionName("BlobTriggerJava1")
    @StorageAccount("BlobConnectionString")
    public void BlobTriggerJava1(
        @BlobTrigger(name = "content", path = "functions-testkit-blob/{name}", dataType = "binary") byte[] content,
        @BindingName("name") String name,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Blob trigger function processed a blob. Name: " + name + "\n  Size: " + content.length + " Bytes");
    }

    @FunctionName("BlobOutputJava1")
    @StorageAccount("BlobConnectionString")
    public HttpResponseMessage BlobOutputJava1(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            @BlobOutput(
                    name = "target",
                    path = "functions-testkit-blob/{DateTime}.txt")
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
