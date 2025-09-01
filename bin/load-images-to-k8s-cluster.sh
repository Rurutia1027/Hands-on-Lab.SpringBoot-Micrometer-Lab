#!/bin/sh 

CLUSTER_NAME="hands-on-k8s-cluster"

# Load Spring Boot image
kind load docker-image nanachi1027/springboot-micrometer-hol:latest --name ${CLUSTER_NAME}

# Load Prometheus and Grafana (optional if pulling from Docker Hub)
# kind load docker-image prom/prometheus:latest --name ${CLUSTER_NAME}
# kind load docker-image grafana/grafana:latest --name ${CLUSTER_NAME}