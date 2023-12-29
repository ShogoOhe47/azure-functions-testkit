package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with Kafka Trigger.
 */
public class KafkaTriggerJava1 {
    /**
     * This function consume KafkaEvents on the localhost. Change the topic, brokerList, and consumerGroup to fit your enviornment.
     * The function is trigged one for each KafkaEvent
     * @param kafkaEventData
     * @param context
     */
    @FunctionName("KafkaTriggerJava1")
    public void run(
            @KafkaTrigger(name = "kafkaTrigger", 
                          topic = "topic", 
                          brokerList = "AzureWebJobsStorage",
                          username = "$ConnectionString",
                          password = "%KafkaPassword%",
                          authenticationMode = BrokerAuthenticationMode.PLAIN,
                          protocol = BrokerProtocol.SASLSSL,
                          consumerGroup="$Default") String kafkaEventData,
            final ExecutionContext context) {
        context.getLogger().info(kafkaEventData);
    }
}