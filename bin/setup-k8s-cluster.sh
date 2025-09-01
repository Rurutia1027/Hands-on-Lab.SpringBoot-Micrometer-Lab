#!/bin/sh

CLUSTER_NAME="spring-micrometer-k8s-cluster"

kind create cluster --name kind-local-k8s --config ../configs/kind-config.yaml