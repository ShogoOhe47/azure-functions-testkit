import azure.functions as func
import logging

import src
import src.Blob
import src.CosmosDB
import src.HttpTrigger
import src.TimerTrigger

app = func.FunctionApp()

app.register_functions(src.HttpTrigger.bp)
app.register_functions(src.Blob.bp)
app.register_functions(src.CosmosDB.bp)

app.register_functions(src.TimerTrigger.bp)
