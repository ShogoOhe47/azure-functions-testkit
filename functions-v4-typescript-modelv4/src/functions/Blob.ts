import { app, input, InvocationContext, output } from "@azure/functions";
import { HttpRequest, HttpResponseInit, } from "@azure/functions";

const blobOutput = output.storageBlob({
    path: 'functions-testkit-blob/{DateTime}.txt',
    connection: 'BlobConnectionString',
});
export async function BlobOutput1(request: HttpRequest, context: InvocationContext): Promise<HttpResponseInit> {
    context.log(`Http function processed request for url "${request.url}"`);

    const name = request.query.get('name') || await request.text() || 'world';

    return { body: `Hello, ${name}!` };
};

app.http('BlobOutput1', {
    methods: ['GET', 'POST'],
    authLevel: 'anonymous',
    return: blobOutput,
    handler: BlobOutput1
});


export async function BlobTrigger1(blob: Buffer, context: InvocationContext): Promise<void> {
    context.log(`Storage blob function processed blob "${context.triggerMetadata.name}" with size ${blob.length} bytes`);
}

app.storageBlob('BlobTrigger1', {
    path: 'functions-testkit-blob/{name}',
    connection: 'BlobConnectionString',
    handler: BlobTrigger1
});
