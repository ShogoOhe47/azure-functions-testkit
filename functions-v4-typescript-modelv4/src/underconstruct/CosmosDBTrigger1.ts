import { app, InvocationContext } from "@azure/functions";

export async function CosmosDBTrigger1(documents: unknown[], context: InvocationContext): Promise<void> {
    context.log(`Cosmos DB function processed ${documents.length} documents`);
}

app.cosmosDB('CosmosDBTrigger1', {
    connectionStringSetting: '',
    databaseName: '',
    collectionName: '',
    createLeaseCollectionIfNotExists: true,
    handler: CosmosDBTrigger1
});
