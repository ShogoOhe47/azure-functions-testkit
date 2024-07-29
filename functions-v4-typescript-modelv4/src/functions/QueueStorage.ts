import { app, InvocationContext } from "@azure/functions";
import { HttpRequest, HttpResponseInit, output } from "@azure/functions";


const queueOutput = output.storageQueue({
    queueName: 'outqueue',
    connection: 'MyStorageConnectionAppSetting',
});
export async function QueueStorageOutput1(request: HttpRequest, context: InvocationContext): Promise<HttpResponseInit> {
    context.log(`Http function processed request for url "${request.url}"`);

    context.extraOutputs.set(queueOutput, "Hello");
    const name = request.query.get('name') || await request.text() || 'world';

    return { body: `Hello, ${name}!` };
};

app.http('QueueStorageOutput1', {
    methods: ['GET', 'POST'],
    authLevel: 'anonymous',
    handler: QueueStorageOutput1
});



export async function QueueStorageTrigger1(queueItem: unknown, context: InvocationContext): Promise<void> {
    context.log('Storage queue function processed work item:', queueItem);
}

app.storageQueue('QueueStorageTrigger1', {
    queueName: 'js-queue-items',
    connection: '',
    handler: QueueStorageTrigger1
});


