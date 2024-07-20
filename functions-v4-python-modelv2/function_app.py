import azure.functions as func
import logging

import src
import src.Blob
import src.CosmosDB
import src.EventGrid
import src.EventHub
import src.HttpTrigger
import src.QueueStorage
import src.TimerTrigger

app = func.FunctionApp()

app.register_functions(src.HttpTrigger.bp)
app.register_functions(src.Blob.bp)
app.register_functions(src.CosmosDB.bp)
# app.register_functions(src.EventGrid.bp)
app.register_functions(src.EventHub.bp)
app.register_functions(src.QueueStorage.bp)

app.register_functions(src.TimerTrigger.bp)
