package com.fabrikam;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.StorageAccount;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.BlobOutput;

import java.util.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Azure Functions with HTTP Trigger.
 */
public class BlobOutput1 {
    /**
     * This function listens at endpoint "/api/HttpTrigger1". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpTrigger1
     * 2. curl {your host}/api/HttpTrigger1?name=HTTP%20Query
     */
    @FunctionName("BlobOutput1")
    @StorageAccount("BlobConnectionString")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<String>> request,
            @BlobOutput(
                    name = "target",
                    path = "functions-testkit-blob/{DateTime}.txt")
            OutputBinding<String> outputItem,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        String query = request.getQueryParameters().get("name");
        String name = request.getBody().orElse(query);

        // Save blob to outputItem
        /*
        byte[] content
        outputItem.setValue(new String(content, StandardCharsets.UTF_8));
        */
        ZoneId zoneId = ZoneId.of("GMT");
        ZonedDateTime dateTime = ZonedDateTime.now(zoneId);
        outputItem.setValue("Hello! "+ dateTime.toString());
        context.getLogger().info("Blob Message: "+ "Hello! "+ dateTime.toString());

        if (name == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a name on the query string or in the request body").build();
        } else {
            return request.createResponseBuilder(HttpStatus.OK).body("Hello, " + name).build();
        }
    }
}
