import { app, InvocationContext } from "@azure/functions";

export async function QueueStorageTrigger1(queueItem: unknown, context: InvocationContext): Promise<void> {
    context.log('Storage queue function processed work item:', queueItem);
}

app.storageQueue('QueueStorageTrigger1', {
    queueName: 'js-queue-items',
    connection: '',
    handler: QueueStorageTrigger1
});
