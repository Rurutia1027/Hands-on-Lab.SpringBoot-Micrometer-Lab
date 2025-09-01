#!/bin/sh

CLUSTER_NAME="hands-on-k8s-cluster"

NAMESPACE_NAME="hands-on-lab"

kind create cluster --name ${CLUSTER_NAME} --config ../configs/kind-config.yaml

kind get clusters 

kubectl cluster-info --context kind-${CLUSTER_NAME}

kubectl get nodes

kubectl get pods -A

kubectl apply -f ../k8s/namespace.yaml

kubectl get ns 

kubectl get pods -n ${NAMESPACE_NAME}

kubectl apply -f ../k8s/springboot-deployment.yaml

kubectl get pods -n ${NAMESPACE_NAME}

kubectl apply -f ../k8s/prometheus-configmap.yaml 

kubectl get configmaps -n ${NAMESPACE_NAME}

kubectl apply -f ../k8s/prometheus-deployment.yaml 

kubectl get pods -n ${NAMESPACE_NAME}