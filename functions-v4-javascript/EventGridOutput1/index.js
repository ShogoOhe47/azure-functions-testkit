module.exports = async function (context, req) {
    context.log('JavaScript HTTP trigger function processed a request.');
    const crypto = require('crypto');
    const timeStamp = new Date().toISOString();

    //const name = (req.query.name || (req.body && req.body.name));
    const name = 'azure';
    const responseMessage = name
        ? "Hello, " + name + ". This HTTP triggered function executed successfully."
        : "This HTTP triggered function executed successfully. Pass a name in the query string or in the request body for a personalized response.";

    context.bindings.outputEvent = {
        id: 'message-id',
        subject: 'subject-name',
        dataVersion: '1.0',
        eventType: 'event-type',
        data: "event-data:"+crypto.randomUUID(),  //"event-data",
        eventTime: timeStamp
    };

    context.res = {
        // status: 200, /* Defaults to 200 */
        body: responseMessage
    };
}