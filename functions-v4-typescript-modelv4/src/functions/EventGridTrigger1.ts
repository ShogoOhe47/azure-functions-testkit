import { app, EventGridEvent, InvocationContext } from "@azure/functions";

export async function EventGridTrigger1(event: EventGridEvent, context: InvocationContext): Promise<void> {
    context.log('Event grid function processed event:', event);
}

app.eventGrid('EventGridTrigger1', {
    handler: EventGridTrigger1
});
