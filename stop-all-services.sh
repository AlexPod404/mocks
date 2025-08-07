#!/bin/bash

# Script to stop all mock services

echo "Stopping all mock services..."
echo "=========================================="

# Array of services
services=(
    "armsb_clients"
    "armsb_client_card" 
    "armsb_cti"
    "armsb_tasks"
    "armsb_calendar"
    "assistant_sber_one"
)

# Function to stop a service
stop_service() {
    local service=$1
    if [ -f "logs/${service}.pid" ]; then
        local pid=$(cat logs/${service}.pid)
        if ps -p $pid > /dev/null 2>&1; then
            echo "Stopping $service (PID: $pid)..."
            kill $pid
            # Wait a moment and force kill if necessary
            sleep 2
            if ps -p $pid > /dev/null 2>&1; then
                echo "Force stopping $service..."
                kill -9 $pid
            fi
            rm -f logs/${service}.pid
            echo "$service stopped"
        else
            echo "$service was not running (stale PID file)"
            rm -f logs/${service}.pid
        fi
    else
        echo "No PID file found for $service"
    fi
}

# Stop all services
for service in "${services[@]}"; do
    stop_service $service
done

# Also kill any remaining Maven Spring Boot processes
echo ""
echo "Killing any remaining Maven Spring Boot processes..."
pkill -f "spring-boot:run" 2>/dev/null || echo "No remaining Maven processes found"

echo "=========================================="
echo "All services stopped"
echo "Log files are preserved in logs/ directory"