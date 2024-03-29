#!/bin/bash

TOPIC="multi-partition-topic"
KEYS=("kejnjvg-y1" "ldpzokey2" "kelmpdys-ssx-3")
MESSAGES=("Hello1" "World1" "Testing1" "Kafka1")
LOG_DIR="./producer_logs_least_once"
MESSAGE_COUNT=${#MESSAGES[@]}

mkdir -p "${LOG_DIR}"

produce_messages() {
    local log_file="${LOG_DIR}/producer_$1_ack_1.log"
    local start total_time elapsed

    echo "Producer $1 is starting."
    start=$(date +%s%N)

    for MSG in "${MESSAGES[@]}"; do
        RANDOM_KEY=${KEYS[$((RANDOM % ${#KEYS[@]}))]}
        MESSAGE="$RANDOM_KEY: $MSG from producer $1"

        echo "$MESSAGE" | docker exec -i smartCv-kafka kafka-console-producer --broker-list localhost:9092 --topic $TOPIC \
            --property "parse.key=true" \
            --property "key.separator=:" \
            --property "acks=1"

        echo "$MESSAGE" >> "$log_file"
    done

    total_time=$((($(date +%s%N) - $start)/1000000))

    echo "Producer $1 finished. Total time: ${total_time}ms" >> "$log_file"
}

produce_messages 1 &
produce_messages 2 &

wait
echo "All producers have finished sending messages."
