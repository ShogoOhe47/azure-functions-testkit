package com.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Azure Functions with IoT Hub trigger.
 */
public class IoTHubJava1 {
    /**
     * IoT Hub Trigger has no sample. It is same as Event Hubs Trigger
     * https://learn.microsoft.com/azure/azure-functions/functions-bindings-event-iot-trigger?tabs=python-v2%2Cisolated-process%2Cnodejs-v4%2Cfunctionsv2%2Cextensionv5&pivots=programming-language-java
     */
//    @FunctionName("IoTHubExample")
//    public void EventHubTriggerJava1(
//            @EventHubTrigger(name = "message",
//                    dataType = "string", // binary  https://learn.microsoft.com/java/api/com.microsoft.azure.functions.annotation.eventhuboutput?view=azure-java-stable
//                    eventHubName = "functions-testkit-eventhub",
//                    connection = "EventHubsConnectionString",
//                    consumerGroup = "$Default",
//                    cardinality = Cardinality.MANY)
//            List<String> message,
//            final ExecutionContext context) {
//
//        int i = 0;
//    }
}
