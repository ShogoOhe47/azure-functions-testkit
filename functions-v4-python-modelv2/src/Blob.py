import logging 

import azure.functions as func 
import azurefunctions.extensions.bindings.blob as blob

bp = func.Blueprint() 

@bp.route(route="BlobOutput1", auth_level=func.AuthLevel.FUNCTION)
@bp.blob_output(arg_name="outputblob",
                path="functions-testkit-blob/{DateTime}.txt",
                connection="BlobConnectionString")
def BlobOutput1(req: func.HttpRequest, outputblob: func.Out[str]) -> func.HttpResponse:
    logging.info('Python HTTP trigger function processed a request.')

    name = req.params.get('name')
    if not name:
        try:
            req_body = req.get_json()
        except ValueError:
            pass
        else:
            name = req_body.get('name')

    outputblob.set("Hello!")

    if name:
        return func.HttpResponse(f"Hello, {name}. This HTTP triggered function executed successfully.")
    else:
        return func.HttpResponse(
             "This HTTP triggered function executed successfully. Pass a name in the query string or in the request body for a personalized response.",
             status_code=200
        )

@bp.blob_trigger(
    arg_name="client", path="functions-testkit-blob", connection="BlobConnectionString"
)
def blob_trigger(client: blob.BlobClient):
    logging.info(
        f"Python blob trigger function processed blob \n"
        f"Properties: {client.get_blob_properties()}\n"
        f"Blob content head: {client.download_blob().read(size=1)}"
    )
