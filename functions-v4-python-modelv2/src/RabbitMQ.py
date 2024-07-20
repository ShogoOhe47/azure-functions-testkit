import logging 

import azure.functions as func

import uuid

bp = func.Blueprint() 

@bp.route(route="RabbitMQOutput1", auth_level=func.AuthLevel.FUNCTION)
@bp.generic_output_binding(arg_name="outputMessage",
                           type="rabbitMQ",
                           queueName="queue",
                           connectionStringSetting="RabbitMQConnectionString"
                           )
def RabbitMQOutput1(req: func.HttpRequest, outputMessage: func.Out[str]) -> func.HttpResponse:
    logging.info('Python HTTP trigger function processed a request.')

    name = req.params.get('name')
    if not name:
        try:
            req_body = req.get_json()
        except ValueError:
            pass
        else:
            name = req_body.get('name')

    # rabbitmq output
    line = f"{uuid.uuid4()}"
    logging.info(f"[debug] RabbitMQ output: {line}")
    outputMessage.set(line)

    if name:
        return func.HttpResponse(f"Hello, {name}. This HTTP triggered function executed successfully.")
    else:
        return func.HttpResponse(
             "This HTTP triggered function executed successfully. Pass a name in the query string or in the request body for a personalized response.",
             status_code=200
        )

@bp.function_name(name="RabbitMQTrigger1")
@bp.generic_trigger(arg_name="myQueueItem",
                    type="rabbitMQTrigger",
                    queueName="queue",
                    connectionStringSetting="RabbitMQConnectionString")
def RabbitMQTrigger1(myQueueItem) -> None:
    logging.info('Python RabbitMQ trigger function processed a queue item: %s', myQueueItem)


