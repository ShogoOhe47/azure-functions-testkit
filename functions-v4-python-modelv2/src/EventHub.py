import logging 

import azure.functions as func

from typing import List
import uuid

bp = func.Blueprint() 

@bp.route(route="EventHubOutput1_Single", auth_level=func.AuthLevel.FUNCTION)
@bp.event_hub_output(arg_name="event",
                      event_hub_name="functions-testkit-eventhub",
                      connection="EventHubsConnectionString")
def EventHubOutput1_Single(req: func.HttpRequest, event: func.Out[str]) -> func.HttpResponse:
    logging.info('Python HTTP trigger function processed a request.')

    name = req.params.get('name')
    if not name:
        try:
            req_body = req.get_json()
        except ValueError:
            pass
        else:
            name = req_body.get('name')

    # eventhub output
    line = f"{uuid.uuid4()}"
    logging.info(f"[debug] EventHub output: {line}")
    event.set(line)

    if name:
        return func.HttpResponse(f"Hello, {name}. This HTTP triggered function executed successfully.")
    else:
        return func.HttpResponse(
             "This HTTP triggered function executed successfully. Pass a name in the query string or in the request body for a personalized response.",
             status_code=200
        )


@bp.route(route="EventHubOutput1_Multiple", auth_level=func.AuthLevel.FUNCTION)
@bp.event_hub_output(arg_name="event",
                      event_hub_name="functions-testkit-eventhub",
                      connection="EventHubsConnectionString")
def EventHubOutput1_Multiple(req: func.HttpRequest, event: func.Out[List[str]]) -> func.HttpResponse:
    logging.info('Python HTTP trigger function processed a request.')

    name = req.params.get('name')
    if not name:
        try:
            req_body = req.get_json()
        except ValueError:
            pass
        else:
            name = req_body.get('name')

    # eventhub output
    my_messages=[]
    for i in range(3):
        line = f"{i}:{uuid.uuid4()}"
        logging.info(f"[debug] EventHub output: {line}")
        my_messages.append(line)
    event.set(my_messages)

    if name:
        return func.HttpResponse(f"Hello, {name}. This HTTP triggered function executed successfully.")
    else:
        return func.HttpResponse(
             "This HTTP triggered function executed successfully. Pass a name in the query string or in the request body for a personalized response.",
             status_code=200
        )


@bp.function_name(name="EventHubTrigger1")
@bp.event_hub_message_trigger(arg_name="myhub", 
                               event_hub_name="functions-testkit-eventhub",
                               connection="EventHubsConnectionString") 
def EventHubTrigger1(myhub: func.EventHubEvent):
    logging.info('Python EventHub trigger processed an event: %s',
                myhub.get_body().decode('utf-8'))