package com.fabrikam;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.BlobTrigger;
import com.microsoft.azure.functions.annotation.BindingName;

import java.util.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class BlobTrigger1 {
    /**
     * This function listens at endpoint "/api/HttpTrigger1". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpTrigger1
     * 2. curl {your host}/api/HttpTrigger1?name=HTTP%20Query
     */
    @FunctionName("BlobTrigger1")
    public void run(
            @BlobTrigger(name = "file",
                    dataType = "binary",
                    path = "functions-testkit-blob/{name}",
                    connection = "BlobConnectionString") byte[] content,
            @BindingName("name") String filename,
            final ExecutionContext context
    ) {
        context.getLogger().info("Name: " + filename + " Size: " + content.length + " bytes");
    }
}
