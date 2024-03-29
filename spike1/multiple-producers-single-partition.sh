#!/bin/bash

TOPIC="single-partition-topic"

produce_messages() {
    for i in {1..5}; do
        TIMESTAMP=$(date +%s%3N)
        echo "Message from producer $1: $i at $TIMESTAMP"
    done | docker exec -i smartCv-kafka kafka-console-producer --broker-list localhost:9092 --topic $TOPIC
}

produce_messages 1 &
produce_messages 2 &
produce_messages 3 &

wait
echo "All producers finished sending messages."
