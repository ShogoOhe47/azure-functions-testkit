import logging 

import azure.functions as func 

import json
import datetime
import uuid

bp = func.Blueprint() 

@bp.route(route="EventGridOutput1", auth_level=func.AuthLevel.FUNCTION)
@bp.event_grid_output(
    arg_name="outputEvent",
    topic_endpoint_uri="EventGridConnectionUri",
    topic_key_setting="EventGridConnectionKey")
def EventGridOutput1(req: func.HttpRequest, outputEvent: func.Out[func.EventGridOutputEvent]) -> func.HttpResponse:
    logging.info('Python HTTP trigger function processed a request.')

    name = req.params.get('name')
    if not name:
        try:
            req_body = req.get_json()
        except ValueError:
            pass
        else:
            name = req_body.get('name')

    # eventgrid output
    outputEvent.set(
        func.EventGridOutputEvent(
            id="test-id",
            data={"tag1": "value1", "tag2": "value2"},
            subject=str(uuid.uuid4()),
            event_type="test-event-1",
            event_time=datetime.datetime.utcnow(),
            data_version="1.0"
        ))

    if name:
        return func.HttpResponse(f"Hello, {name}. This HTTP triggered function executed successfully.")
    else:
        return func.HttpResponse(
             "This HTTP triggered function executed successfully. Pass a name in the query string or in the request body for a personalized response.",
             status_code=200
        )


@bp.function_name(name="EventGridTrigger1")
@bp.event_grid_trigger(arg_name="event")
def eventGridTest(event: func.EventGridEvent):
    result = json.dumps({
        'id': event.id,
        'data': event.get_json(),
        'topic': event.topic,
        'subject': event.subject,
        'event_type': event.event_type,
    })

    logging.info('Python EventGrid trigger processed an event: %s', result)

