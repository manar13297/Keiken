#!/bin/bash

TOPIC="multi-partition-topic"

KEYS=("hbhbh-ddnj" "dededed-dd4444d-dddd4d-cdd545515116165165115115448" "mmmlh-9")

produce_messages() {
    for i in {1..5}; do
        RANDOM_KEY=${KEYS[$((RANDOM % ${#KEYS[@]}))]}
        TIMESTAMP=$(date +%s%3N)
        echo "$RANDOM_KEY:  From producer $1 at $TIMESTAMP with key $RANDOM_KEY"
    done | docker exec -i smartCv-kafka kafka-console-producer --broker-list localhost:9092 --topic $TOPIC --property "parse.key=true" --property "key.separator=:"
}

produce_messages 1 &
produce_messages 2 &
produce_messages 3 &

wait
echo "All producers finished sending messages."
