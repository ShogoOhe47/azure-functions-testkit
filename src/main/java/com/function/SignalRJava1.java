package com.function;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.signalr.SignalRConnectionInfo;
import com.microsoft.azure.functions.signalr.annotation.SignalRConnectionInfoInput;
import com.microsoft.azure.functions.signalr.annotation.SignalROutput;
import com.microsoft.azure.functions.signalr.SignalRMessage;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class SignalRJava1 {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("SignalRInputJava1")
    public HttpResponseMessage SignalRInputJava1(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            @SignalRConnectionInfoInput(
                name = "signal",
                hubName = "chat",
                connectionStringSetting = "SignalRConnectionString",
                userId = "userId1")
            SignalRConnectionInfo connectionInfo,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        return request.createResponseBuilder(HttpStatus.OK).body(String.format("SignalR ConnectionInfo: url='%s', accessToken='%s'", connectionInfo.url, connectionInfo.accessToken)).build();
    }

    @FunctionName("SignalROutputJava1")
    @SignalROutput(name = "signal", hubName = "chat", connectionStringSetting="SignalRConnectionString")
    public SignalRMessage SignalROutputJava1(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        SignalRMessage message = new SignalRMessage();
        //message.userId = "userId1";

        String response = Commons.generateSampleResponse();

        // write to queue message
        message.target = response;

        // SignalR response
        return message;
    }

}
