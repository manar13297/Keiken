#!/bin/bash

TOPIC="single-partition-topic"
LOG_DIR="./producer_logs"

mkdir -p "${LOG_DIR}"

produce_messages() {
    local log_file="${LOG_DIR}/producer_$1.log"
    while true; do
        TIMESTAMP=$(date +%s%3N)
        MESSAGE="From producer $1 at $TIMESTAMP "
        echo "$MESSAGE"
        echo "$MESSAGE" >> "$log_file"

        echo "$MESSAGE" | docker exec -i smartCv-kafka kafka-console-producer --broker-list localhost:9092 --topic $TOPIC --property "acks=0"

        if [ $? -ne 0 ]; then
            echo "Failed to send message: $MESSAGE" >> "$log_file"
        fi

        sleep 2
    done
}

produce_messages 1 &
produce_messages 2 &

trap "kill 0" SIGINT

wait
echo "All producers finished sending messages."
