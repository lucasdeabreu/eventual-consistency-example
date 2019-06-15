##### The idea of this project is use eventual consistency to decoupling services between each other.

In this example `payment-service` depend on `Order` information to 
register a payment, instead of call `order-service` to request such 
information instead `order-service` will send events to notify when 
an `Order` is creating and then `payment-service` use this events 
to keep a local copy of `Order`, doing this `payment-service` does 
not need request any info to `order-service`.

Those events are sent via queue using RabbitMQ.  

##### Trying out

Compile 
```
mvn clean package
```

Run
```
docker-compose up
```

Create an Order
```
curl -X POST \
  http://localhost:8081/api/orders \
  -H 'Accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
	"address": "Rua seila",
	"confirmationDate": "2016-01-25T21:34:55",
	"status": "NEW",
	"items": [
		{
			"description": "Produto 1",
			"unitPrice": 2.0,
			"quantity": 1
		}
	]
}'
```

Pay the Order 
```
curl -X POST \
  http://localhost:8082/api/payments \
  -H 'Accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
	"creditCard": "1121212111112",
	"value": 2,
	"orderId": 5
}'
```