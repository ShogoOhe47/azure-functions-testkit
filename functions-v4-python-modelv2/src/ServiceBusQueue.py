import logging 

import azure.functions as func 

import uuid

bp = func.Blueprint() 

@bp.route(route="ServiceBusQueueOutput1", auth_level=func.AuthLevel.FUNCTION)
@bp.service_bus_queue_output(arg_name="message",
                              connection="ServiceBusQueueConnectionString",
                              queue_name="functions-testkit-queue")
def ServiceBusQueueOutput1(req: func.HttpRequest, message: func.Out[str]) -> func.HttpResponse:
    logging.info('Python HTTP trigger function processed a request.')

    name = req.params.get('name')
    if not name:
        try:
            req_body = req.get_json()
        except ValueError:
            pass
        else:
            name = req_body.get('name')
    
    # servicebus output
    line = f"{uuid.uuid4()}"
    logging.info(f"[debug] ServiceBus(Queue) output: {line}")
    message.set(line)

    if name:
        return func.HttpResponse(f"Hello, {name}. This HTTP triggered function executed successfully.")
    else:
        return func.HttpResponse(
             "This HTTP triggered function executed successfully. Pass a name in the query string or in the request body for a personalized response.",
             status_code=200
        )


@bp.function_name(name="ServiceBusQueueTrigger1")
@bp.service_bus_queue_trigger(arg_name="msg", 
                               queue_name="functions-testkit-queue", 
                               connection="ServiceBusQueueConnectionString")
def ServiceBusQueueTrigger1(msg: func.ServiceBusMessage) -> None:
    logging.info('Python ServiceBus queue trigger processed message: %s',
                 msg.get_body().decode('utf-8'))


