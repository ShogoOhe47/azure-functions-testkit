module.exports = async function (context, myBlob) {
    context.log("JavaScript blob trigger function processed blob \n Blob:", context.bindingData.blobTrigger, "\n Blob Size:", myBlob.length, "Bytes");
    
    // for TextFile: binary -> string
    // var text_decoder = new TextDecoder("utf-8");
    // context.log(text_decoder.decode(Uint8Array.from(myBlob).buffer));

    context.log( typeof(myBlob) )
};