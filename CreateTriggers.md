# Create Triggers
```
func new --name HttpTrigger1 --template "HTTP trigger" --authlevel "anonymous"

func new --name BlobOutput1  --template "HTTP trigger"
func new --name BlobTrigger1  --template "Azure Blob Storage trigger"
func new --name CosmosDBOutput1  --template "HTTP trigger"
func new --name CosmosDBTrigger1  --template "Azure Cosmos DB trigger"
func new --name EventGridOutput1  --template "HTTP trigger"
func new --name EventGridTrigger1  --template "Azure Event Grid trigger"
func new --name EventHubOutput1  --template "HTTP trigger"
func new --name EventHubTrigger1  --template "Azure Event Hub trigger"
func new --name KafkaOutput1  --template "HTTP trigger"
func new --name KafkaTrigger1  --template "Kafka trigger"
func new --name QueueStorageOutput1  --template "HTTP trigger"
func new --name QueueStorageTrigger1  --template "Azure Queue Storage trigger"
func new --name RabbitMQOutput1  --template "HTTP trigger"
func new --name RabbitMQTrigger1  --template "RabbitMQ trigger"
func new --name ServiceBusQueueOutput1  --template "HTTP trigger"
func new --name ServiceBusQueueTrigger1  --template "Azure Service Bus Queue trigger"
func new --name ServiceBusTopicOutput1  --template "HTTP trigger"
func new --name ServiceBusTopicTrigger1  --template "Azure Service Bus Topic trigger"
func new --name TimerTrigger1  --template "Timer trigger"
```


# List Trigger Templates
func templates list

```
PS > func --version
4.0.5198
PS > func templates list
C# Templates:
  Azure Blob Storage trigger
  Azure Cosmos DB trigger
  Durable Functions activity
  Durable Functions entity (class)
  Durable Functions entity (function)
  Durable Functions Entity HTTP starter
  Durable Functions HTTP starter
  Durable Functions orchestrator
  Azure Event Grid trigger
  Azure Event Hub trigger
  HTTP trigger
  IoT Hub (Event Hub)
  Kafka output
  Kafka trigger
  Azure Queue Storage trigger
  RabbitMQ trigger
  SendGrid
  Azure Service Bus Queue trigger
  Azure Service Bus Topic trigger
  SignalR negotiate HTTP trigger
  Timer trigger

Custom Templates:
  Azure Blob Storage trigger
  Azure Cosmos DB trigger
  Azure Event Grid trigger
  Azure Event Hub trigger
  HTTP trigger
  IoT Hub (Event Hub)
  Kafka trigger
  Azure Queue Storage trigger
  RabbitMQ trigger
  SendGrid
  Azure Service Bus Queue trigger
  Azure Service Bus Topic trigger
  SignalR negotiate HTTP trigger
  Timer trigger

JavaScript Templates:
  Azure Blob Storage trigger
  Azure Cosmos DB trigger
  Durable Functions activity
  Durable Functions entity
  Durable Functions Entity HTTP starter
  Durable Functions HTTP starter
  Durable Functions orchestrator
  Azure Event Grid trigger
  Azure Event Hub trigger
  HTTP trigger
  IoT Hub (Event Hub)
  Kafka output
  Kafka trigger
  Azure Queue Storage trigger
  RabbitMQ trigger
  SendGrid
  Azure Service Bus Queue trigger
  Azure Service Bus Topic trigger
  SignalR negotiate HTTP trigger
  Timer trigger
  Azure Blob Storage trigger
  Azure Cosmos DB trigger
  Durable Functions entity
  Durable Functions orchestrator
  Azure Event Grid trigger
  Azure Event Hub trigger
  HTTP trigger
  Azure Queue Storage trigger
  Azure Service Bus Queue trigger
  Azure Service Bus Topic trigger
  Timer trigger

PowerShell Templates:
  Azure Blob Storage trigger
  Azure Cosmos DB trigger
  Durable Functions activity
  Durable Functions HTTP starter
  Durable Functions orchestrator
  Azure Event Grid trigger
  Azure Event Hub trigger
  HTTP trigger
  IoT Hub (Event Hub)
  Kafka output
  Kafka trigger
  Azure Queue Storage trigger
  RabbitMQ trigger
  SendGrid
  Azure Service Bus Queue trigger
  Azure Service Bus Topic trigger
  SignalR negotiate HTTP trigger
  Timer trigger

Python Templates:
  Azure Blob Storage trigger
  Azure Cosmos DB trigger
  Durable Functions activity
  Durable Functions entity
  Durable Functions HTTP starter
  Durable Functions orchestrator
  Azure Event Grid trigger
  Azure Event Hub trigger
  HTTP trigger
  Kafka output
  Kafka trigger
  Azure Queue Storage trigger
  RabbitMQ trigger
  Azure Service Bus Queue trigger
  Azure Service Bus Topic trigger
  Timer trigger

TypeScript Templates:
  Azure Blob Storage trigger
  Azure Cosmos DB trigger
  Durable Functions activity
  Durable Functions entity
  Durable Functions Entity HTTP starter
  Durable Functions HTTP starter
  Durable Functions orchestrator
  Azure Event Grid trigger
  Azure Event Hub trigger
  HTTP trigger
  IoT Hub (Event Hub)
  Kafka output
  Kafka trigger
  Azure Queue Storage trigger
  RabbitMQ trigger
  SendGrid
  Azure Service Bus Queue trigger
  Azure Service Bus Topic trigger
  SignalR negotiate HTTP trigger
  Timer trigger
  Azure Blob Storage trigger
  Azure Cosmos DB trigger
  Durable Functions entity
  Durable Functions orchestrator
  Azure Event Grid trigger
  Azure Event Hub trigger
  HTTP trigger
  Azure Queue Storage trigger
  Azure Service Bus Queue trigger
  Azure Service Bus Topic trigger
  Timer trigger

PS >
```


# REF
https://learn.microsoft.com/azure/azure-functions/functions-run-local?tabs=windows%2Cisolated-process%2Cnode-v4%2Cpython-v2%2Chttp-trigger%2Ccontainer-apps&pivots=programming-language-python#create-func