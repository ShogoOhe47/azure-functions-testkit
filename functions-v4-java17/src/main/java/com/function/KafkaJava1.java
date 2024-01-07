package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.util.Optional;

/**
 * Azure Functions with Kafka Trigger.
 */
public class KafkaJava1 {
    /**
     * This function consume KafkaEvents on the localhost. Change the topic, brokerList, and consumerGroup to fit your enviornment.
     * The function is trigged one for each KafkaEvent
     * @param kafkaEventData
     * @param context
     */
    @FunctionName("KafkaTriggerJava1")
    public void KafkaTriggerJava1(
            @KafkaTrigger(name = "kafkaTrigger", 
                          topic = "topic",
                          brokerList = "KafkaBootstrapServer",
                          username = "KafkaUsername",
                          password = "KafkaPassword",
                          authenticationMode = BrokerAuthenticationMode.SCRAMSHA512,
                          protocol = BrokerProtocol.SASLSSL,
                          consumerGroup="$Default",
                          dataType = "string")
            String kafkaEventData,
            final ExecutionContext context) {
        context.getLogger().info(kafkaEventData);
    }

    @FunctionName("KafkaOutputJava1")
    public HttpResponseMessage KafkaOutputJava1(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            @KafkaOutput(
                    name = "kafkaOutput",
                    topic = "topic",
                    brokerList = "KafkaBootstrapServer",
                    username = "KafkaUsername",
                    password = "KafkaPassword",
                    authenticationMode = BrokerAuthenticationMode.SCRAMSHA512,
                    // sslCaLocation = "confluent_cloud_cacert.pem", // Enable this line for windows.
                    protocol = BrokerProtocol.SASLSSL
            )  OutputBinding<String> output,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        String response = Commons.generateSampleResponse();

        // write to kafka message
        output.setValue(response);

        // HTTP response
        return request.createResponseBuilder(HttpStatus.OK).body(response).build();
    }
}