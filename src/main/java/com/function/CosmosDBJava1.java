package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.util.Optional;
import java.util.Random;

/**
 * Azure Functions with Cosmos DB trigger.
 * 
 * This template uses an outdated version of the Azure Cosmos DB extension. Learn about the new extension at https://aka.ms/cosmos-db-azure-functions-extension-v4
 */
public class CosmosDBJava1 {
    /**
     * This function will be invoked when there are inserts or updates in the specified database and collection.
     *
     * at first time, listener is not able to start. because target database is not exist. it will be create by output trigger.
     * > The listener for function 'Functions.CosmosDBTriggerJava1' was unable to start. Microsoft.Azure.WebJobs.Extensions.CosmosDB: Either the source container 'Items' (in database 'Tasks')  or the lease container 'leases' (in database 'Tasks') does not exist. Both containers must exist before the listener starts. To automatically create the lease container, set 'CreateLeaseContainerIfNotExists' to 'true'. Microsoft.Azure.Cosmos.Client: Response status code does not indicate success: NotFound (404); Substatus: 0; ActivityId: 29830c65-c386-4b3b-908d-4d66e3d4b91d; Reason: (Message: {"Errors":["Resource Not Found. Learn more: https:\/\/aka.ms\/cosmosdb-tsg-not-found"]}
     */
    @FunctionName("CosmosDBTriggerJava1")
    public void CosmosDBTriggerJava1(
        @CosmosDBTrigger(
            name = "items",
            databaseName = "Tasks",
            containerName = "Items",
            leaseContainerName="leases",
            connection = "CosmosDBConnectionString",
            createLeaseContainerIfNotExists = true,
            leaseContainerPrefix = "Trigger1"
        )
        Object[] items,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Cosmos DB trigger function executed.");
        context.getLogger().info("Documents count: " + items.length);
    }

    @FunctionName("CosmosDBOutputJava1")
    public HttpResponseMessage CosmosDBOutputJava1(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            @CosmosDBOutput(
                    name = "items",
                    databaseName = "Tasks",
                    containerName = "Items",
                    createIfNotExists = true,
                    connection = "CosmosDBConnectionString",
                    partitionKey = "/description" // "Partition key paths must contain only valid characters and not contain a trailing slash or wildcard character."
            )
            OutputBinding<String> outputItem,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        String response = Commons.generateSampleResponse();

        // Generate random ID
        final int id = Math.abs(new Random().nextInt());

        // Generate document
        final String jsonDocument = "{\"id\":\"" + id + "\", " +
                "\"description\": \"" + response + "\"}";

        context.getLogger().info("Document to be saved: " + jsonDocument);

        // Set outputItem's value to the JSON document to be saved
        outputItem.setValue(jsonDocument);

        // HTTP response
        return request.createResponseBuilder(HttpStatus.OK).body(response).build();
    }
}
