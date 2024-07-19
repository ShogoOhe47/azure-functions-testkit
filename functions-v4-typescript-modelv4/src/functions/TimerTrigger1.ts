import { app, InvocationContext, Timer } from "@azure/functions";

export async function TimerTrigger1(myTimer: Timer, context: InvocationContext): Promise<void> {
    context.log('Timer function processed request.');
}

app.timer('TimerTrigger1', {
    schedule: '0 */5 * * * *',
    handler: TimerTrigger1
});
