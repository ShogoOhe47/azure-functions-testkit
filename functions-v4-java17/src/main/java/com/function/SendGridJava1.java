package com.function;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.SendGridOutput;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class SendGridJava1 {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("SendGridTriggerJava1")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            @SendGridOutput(
                    name = "message",
                    dataType = "String",
                    apiKey = "AzureWebJobsSendGridApiKey",
                    to = "shogo.ohe.47@gmail.com", //"user@contoso.com",
                    from = "shogo-ohe@outlook.com", //"sender@contoso.com",
                    subject = "Azure Functions email with SendGrid",
                    text = "Sent from Azure Functions")
            OutputBinding<String> message,
            final ExecutionContext context) {

        String response = Commons.generateSampleResponse();

        final String toAddress = "shogo.ohe.47@gmail.com";
        final String fromAddress = "shogo-ohe@outlook.com";
        final String subject = "Sending with Twilio SendGrid is Fun";
        final String contents = "Sent from Azure Functions "+response;

        StringBuilder builder = new StringBuilder()
            .append("{")
            .append("\"personalizations\":[")
            .append("{\"to\":[{\"email\":\"%s\"}],")
            .append("\"subject\":\"%s\",")
            .append("\"from\":{\"email\":\"%s\"},")
            .append("\"content\":[{\"type\":\"text/plain\",\"value\":\"%s\"}]}")
            .append("]}");



        final String body = String.format(builder.toString(), toAddress, subject, fromAddress, contents);
        context.getLogger().info(body);

        // bind to template message
        message.setValue(body);

        // HTTP response
        return request.createResponseBuilder(HttpStatus.OK).body(response).build();
    }
}
