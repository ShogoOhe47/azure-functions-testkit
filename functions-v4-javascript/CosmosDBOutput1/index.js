// https://docs.microsoft.com/ja-jp/azure/cosmos-db/sql/sql-api-nodejs-get-started?tabs=windows
// データベース名とコンテナー名は、それぞれ Tasks と Items に設定されます。
module.exports = async function (context, req) {
    context.log('JavaScript HTTP trigger function processed a request.');
    const crypto = require('crypto');

    //const name = (req.query.name || (req.body && req.body.name));
    const name = 'azure';
    const responseMessage = name
        ? "Hello, " + name + ". This HTTP triggered function executed successfully."
        : "This HTTP triggered function executed successfully. Pass a name in the query string or in the request body for a personalized response.";

    // cosmosdb output: need to cast String
    context.bindings.documents = JSON.stringify({
        id: new String( new Date().getTime() ),
        category: 'general',
        description: crypto.randomUUID()
    });
    
    context.res = {
        // status: 200, /* Defaults to 200 */
        body: responseMessage
    };
}