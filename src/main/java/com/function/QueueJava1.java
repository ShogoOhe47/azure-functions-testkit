package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.util.Optional;

/**
 * Azure Functions with Azure Storage Queue trigger.
 */
public class QueueJava1 {
    /**
     * This function will be invoked when a new message is received at the specified path. The message contents are provided as input to this function.
     */
    @FunctionName("QueueTriggerJava1")
    public void run(
            @QueueTrigger(name = "message", queueName = "functions-testkit-queue", connection = "QueueStorageConnectionString") String message,
            final ExecutionContext context
    ) {
        context.getLogger().info("Java Queue trigger function processed a message: " + message);
    }


    @FunctionName("QueueOutputJava1")
    @StorageAccount("BlobConnectionString")
    public HttpResponseMessage QueueOutputJava1(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            @QueueOutput(
                    name = "message",
                    queueName = "functions-testkit-queue",
                    connection = "QueueStorageConnectionString")
            OutputBinding<String> message,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        String response = Commons.generateSampleResponse();

        // write to queue message
        message.setValue(response);

        // HTTP response
        return request.createResponseBuilder(HttpStatus.OK).body(response).build();
    }

}
