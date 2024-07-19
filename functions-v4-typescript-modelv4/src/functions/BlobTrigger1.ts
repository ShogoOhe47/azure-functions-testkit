import { app, InvocationContext } from "@azure/functions";

export async function BlobTrigger1(blob: Buffer, context: InvocationContext): Promise<void> {
    context.log(`Storage blob function processed blob "${context.triggerMetadata.name}" with size ${blob.length} bytes`);
}

app.storageBlob('BlobTrigger1', {
    path: 'samples-workitems/{name}',
    connection: '',
    handler: BlobTrigger1
});
