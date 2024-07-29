import { app, InvocationContext } from "@azure/functions";

export async function ServiceBusQueueTrigger1(message: unknown, context: InvocationContext): Promise<void> {
    context.log('Service bus queue function processed message:', message);
}

app.serviceBusQueue('ServiceBusQueueTrigger1', {
    connection: '',
    queueName: 'myinputqueue',
    handler: ServiceBusQueueTrigger1
});
