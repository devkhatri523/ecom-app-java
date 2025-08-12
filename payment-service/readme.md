to go in kafka terminal : docker exec -it ms_kafka bash
to listen on topic : kafka-console-consumer  --bootstrap-server localhost:9092  --topic payment-topic  --from-beginning
to check the topic list : kafka-topics --bootstrap-server localhost:9092 --list


to delete the topic : kafka-topics --bootstrap-server localhost:9092 --delete --topic payment-topic