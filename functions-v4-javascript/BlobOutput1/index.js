module.exports = async function (context, req) {
    context.log('JavaScript HTTP trigger function processed a request.');

    //const name = (req.query.name || (req.body && req.body.name));
    const name = 'azure';
    const responseMessage = name
        ? "Hello, " + name + ". This HTTP triggered function executed successfully."
        : "This HTTP triggered function executed successfully. Pass a name in the query string or in the request body for a personalized response.";
    
    context.bindings.myOutputBlob = 'Hello!';

    // BlobOutput では任意の出力先を指定することが出来ない。input に依存する
    // https://qiita.com/TsuyoshiUshio@github/items/7e10cc19170fad9dc6d9

    context.res = {
        // status: 200, /* Defaults to 200 */
        body: responseMessage
    };
}