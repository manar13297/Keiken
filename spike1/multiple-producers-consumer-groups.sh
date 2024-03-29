#!/bin/bash

TOPIC="multi-partition-topic"

KEYS=("hbhbh-ddnj" "dededed" "mmmlh-9")

produce_messages() {
    while true; do
        RANDOM_KEY=${KEYS[$((RANDOM % ${#KEYS[@]}))]}
        TIMESTAMP=$(date +%s%3N)
        echo "$RANDOM_KEY:  From producer $1 at $TIMESTAMP with key $RANDOM_KEY"
        sleep 2
    done | docker exec -i smartCv-kafka kafka-console-producer --broker-list localhost:9092 --topic $TOPIC --property "parse.key=true" --property "key.separator=:"
}

produce_messages 1 &
produce_messages 2 &
produce_messages 3 &

trap "kill 0" SIGINT

wait
echo "All producers finished sending messages."
