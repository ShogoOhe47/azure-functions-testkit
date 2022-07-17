// https://docs.microsoft.com/ja-jp/azure/event-grid/handler-functions
// Event Grid Event was post from Event Grid. Functions not access to Event Grid.
module.exports = async function (context, eventGridEvent) {
    context.log(typeof eventGridEvent);
    context.log(eventGridEvent);
};