import { app, InvocationContext } from "@azure/functions";

export async function ServiceBusTopicTrigger1(message: unknown, context: InvocationContext): Promise<void> {
    context.log('Service bus topic function processed message:', message);
}

app.serviceBusTopic('ServiceBusTopicTrigger1', {
    connection: '',
    topicName: 'mytopic',
    subscriptionName: 'mysubscription',
    handler: ServiceBusTopicTrigger1
});
