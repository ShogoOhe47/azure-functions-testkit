import logging 

import azure.functions as func

import uuid

bp = func.Blueprint() 

@bp.route(route="QueueStorageOutput1", auth_level=func.AuthLevel.FUNCTION)
@bp.queue_output(arg_name="msg", 
                  queue_name="functions-testkit-queue", 
                  connection="QueueStorageConnectionString")
def QueueStorageOutput1(req: func.HttpRequest, msg: func.Out[str]) -> func.HttpResponse:
    logging.info('Python HTTP trigger function processed a request.')

    name = req.params.get('name')
    if not name:
        try:
            req_body = req.get_json()
        except ValueError:
            pass
        else:
            name = req_body.get('name')

    # queue storage output
    line = f"{uuid.uuid4()}"
    logging.info(f"[debug] QueueStorage output: {line}")
    msg.set( line )

    ### it is not work. queue output only support single message.
    # for i in range(3):
    #     line = f"{i}:{uuid.uuid4()}"
    #     logging.info(f"[debug] QueueStorage output: {line}")
    #     msg.set(line)


    if name:
        return func.HttpResponse(f"Hello, {name}. This HTTP triggered function executed successfully.")
    else:
        return func.HttpResponse(
             "This HTTP triggered function executed successfully. Pass a name in the query string or in the request body for a personalized response.",
             status_code=200
        )


@bp.function_name(name="QueueStorageTrigger1")
@bp.queue_trigger(arg_name="msg", queue_name="functions-testkit-queue",
                   connection="QueueStorageConnectionString")  # Queue trigger
def QueueStorageTrigger1(msg: func.QueueMessage) -> None:
    logging.info('Python queue trigger function processed a queue item: %s',
                 msg.get_body().decode('utf-8'))
