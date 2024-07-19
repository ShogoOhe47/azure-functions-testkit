import logging 

import azure.functions as func

import datetime
import uuid

bp = func.Blueprint() 

@bp.route(route="CosmosDBOutput1", auth_level=func.AuthLevel.FUNCTION)
@bp.cosmos_db_output(arg_name="documents",
                     database_name="Tasks",
                     container_name="Items",
                     create_if_not_exists=True,
                     connection="CosmosDBConnectionString")
def CosmosDBOutput1(req: func.HttpRequest, documents: func.Out[func.Document]) -> func.HttpResponse:
    logging.info('Python HTTP trigger function processed a request.')

    name = req.params.get('name')
    if not name:
        try:
            req_body = req.get_json()
        except ValueError:
            pass
        else:
            name = req_body.get('name')
    
    # sample date
    sample_data = dict()
    sample_data["id"] = datetime.datetime.now(datetime.timezone.utc).strftime("%Y-%m-%dT%H:%M:%S.%fZ")
    sample_data["category"] = "general"
    sample_data["description"] = str(uuid.uuid4())

    documents.set(func.Document.from_dict(sample_data))

    if name:
        return func.HttpResponse(f"Hello, {name}. This HTTP triggered function executed successfully.")
    else:
        return func.HttpResponse(
             "This HTTP triggered function executed successfully. Pass a name in the query string or in the request body for a personalized response.",
             status_code=200
        )


@bp.function_name(name="CosmosDBTrigger1")
@bp.cosmos_db_trigger(arg_name="documents", 
                       connection="CosmosDBConnectionString",
                       database_name="Tasks", 
                       container_name="Items", 
                       lease_container_name="leases",
                       create_lease_container_if_not_exists="true")
def test_function(documents: func.DocumentList) -> str:
    if documents:
        logging.info('Document id: %s', documents[0]['id'])