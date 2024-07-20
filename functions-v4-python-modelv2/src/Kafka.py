import logging 

import azure.functions as func
from azure.functions import KafkaEvent
import uuid

bp = func.Blueprint() 

@bp.route(route="KafkaOutput1_Single", auth_level=func.AuthLevel.FUNCTION)
@bp.generic_output_binding(arg_name="outputMessage",
                           type="kafka",
                           brokerList="%KafkaBootstrapServer%",
                           topic="%ConfluentCloudPrefix%default",
                           username="%ConfluentCloudUserName%",
                           password="%ConfluentCloudPassword%",
                           protocol="SASLSSL",
                           authenticationMode="ScramSha512"
                           )
# authenticationMode="ScramSha512"
# https://github.com/Azure/azure-functions-kafka-extension/blob/d0f437816bd42cc7037b394d9cb9aa549403eddd/src/Microsoft.Azure.WebJobs.Extensions.Kafka/Config/BrokerAuthenticationMode.cs#L12
def KafkaOutput1_Single(req: func.HttpRequest, outputMessage: func.Out[str]) -> func.HttpResponse:
    logging.info('Python HTTP trigger function processed a request.')

    name = req.params.get('name')
    if not name:
        try:
            req_body = req.get_json()
        except ValueError:
            pass
        else:
            name = req_body.get('name')

    # kafka output
    line = f"{uuid.uuid4()}"
    logging.info(f"[debug] Kafka output: {line}")
    outputMessage.set(line)

    if name:
        return func.HttpResponse(f"Hello, {name}. This HTTP triggered function executed successfully.")
    else:
        return func.HttpResponse(
             "This HTTP triggered function executed successfully. Pass a name in the query string or in the request body for a personalized response.",
             status_code=200
    )

# @bp.route(route="KafkaOutput1_Multiple", auth_level=func.AuthLevel.FUNCTION)
# @bp.generic_output_binding(arg_name="outputMessage",
#                            type="kafka",
#                            brokerList="%KafkaBootstrapServer%",
#                            topic="%ConfluentCloudPrefix%default",
#                            username="%ConfluentCloudUserName%",
#                            password="%ConfluentCloudPassword%",
#                            protocol="SASLSSL",
#                            authenticationMode="ScramSha512"
#                            )
# def KafkaOutput1_Multiple(req: func.HttpRequest, outputMessage: func.Out[str]) -> func.HttpResponse:
#     logging.info('Python HTTP trigger function processed a request.')

#     name = req.params.get('name')
#     if not name:
#         try:
#             req_body = req.get_json()
#         except ValueError:
#             pass
#         else:
#             name = req_body.get('name')

#     # kafka output
#     my_messages=[]
#     for i in range(3):
#         line = f"{i}:{uuid.uuid4()}"
#         logging.info(f"[debug] Kafka output: {line}")
#         my_messages.append(line)
#     # outputMessage.set(my_messages)
#     outputMessage.set(['one', 'two'])

#     if name:
#         return func.HttpResponse(f"Hello, {name}. This HTTP triggered function executed successfully.")
#     else:
#         return func.HttpResponse(
#              "This HTTP triggered function executed successfully. Pass a name in the query string or in the request body for a personalized response.",
#              status_code=200
#     )

@bp.function_name(name="KafkaTrigger1")
@bp.generic_trigger(arg_name="kevent",
                    type="kafkaTrigger",
                    topic="%ConfluentCloudPrefix%default",
                    brokerList= "%KafkaBootstrapServer%",
                    username="%ConfluentCloudUserName%",
                    password="%ConfluentCloudPassword%",
                    consumerGroup="%ConfluentCloudPrefix%functions",
                    protocol="SASLSSL",
                    authenticationMode="ScramSha512",
                    cardinality="MANY", # ONE, MANY: if choose MANY, require dateType arg
                    dataType="string"
                    )
def KafkaTrigger1(kevent : KafkaEvent):
    logging.info(kevent.get_body().decode('utf-8'))
    logging.info(kevent.metadata)
