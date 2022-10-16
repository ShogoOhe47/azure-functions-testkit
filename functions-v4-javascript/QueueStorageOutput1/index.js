module.exports = async function (context, req) {
    context.log('JavaScript HTTP trigger function processed a request.');
    const crypto = require('crypto');
    const timeStamp = new Date().toISOString();

    //const name = (req.query.name || (req.body && req.body.name));
    const name = 'azure';
    const responseMessage = name
        ? "Hello, " + name + ". This HTTP triggered function executed successfully."
        : "This HTTP triggered function executed successfully. Pass a name in the query string or in the request body for a personalized response.";


    var message = 'Message created at: ' + timeStamp +' '+ crypto.randomUUID();
    // how many message create?
    const numOfMessage = Math.floor(Math.random() * 10);
    
    context.bindings.myQueueItem = []
    for (let i = 0; i <= numOfMessage; i++) {
        context.bindings.myQueueItem.push(i +"of"+ numOfMessage +' '+ message);
    }
    //context.bindings.myQueueItem = ["message 1","message 2"];
    
    context.res = {
        // status: 200, /* Defaults to 200 */
        body: responseMessage
    };
}