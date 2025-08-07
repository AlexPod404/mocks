#!/bin/bash

# Script to start all mock services in parallel
# Each service will run in the background on its designated port

echo "Starting all mock services..."
echo "=========================================="

# Array of services and their ports
declare -A services=(
    ["armsb_clients"]="8001"
    ["armsb_client_card"]="8010"
    ["armsb_cti"]="8020"
    ["armsb_tasks"]="8030"
    ["armsb_calendar"]="8040"
    ["assistant_sber_one"]="8050"
)

# Function to start a service
start_service() {
    local service=$1
    local port=$2
    echo "Starting $service on port $port..."
    cd $service
    nohup mvn spring-boot:run > ../logs/${service}.log 2>&1 &
    echo $! > ../logs/${service}.pid
    cd ..
    echo "$service started (PID: $(cat logs/${service}.pid))"
}

# Create logs directory
mkdir -p logs

# Start all services
for service in "${!services[@]}"; do
    port=${services[$service]}
    start_service $service $port
    sleep 2  # Small delay between starts
done

echo "=========================================="
echo "All services starting..."
echo "Check logs/ directory for individual service logs"
echo ""
echo "Service status:"
echo "- armsb_clients:      http://localhost:8001"
echo "- armsb_client_card:  http://localhost:8010"
echo "- armsb_cti:          http://localhost:8020"
echo "- armsb_tasks:        http://localhost:8030"
echo "- armsb_calendar:     http://localhost:8040"
echo "- assistant_sber_one: http://localhost:8050"
echo ""
echo "To stop all services, run: ./stop-all-services.sh"