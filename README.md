# Hands-on Lab: Spring Boot + Micrometer + Prometheus + Jib + Kubernetes 

## Objective  
By the end of this lab, participants will be able to: 
- Create a simple Spring Boot application with Micrometer metrics. 
- Expose metrics to Prometheus. 
- Build a Docker image using Jib. 
- Deploy the application on Kubernetes. 

## Pre-requisites 
- Java 17+ installed 
- Maven 3.9+ installed 
- Docker installed and running 
- Kubernetes cluster (local, e.g. Kind, K3d, or a cloud cluster like EKS)
- `kubectl` configured for your cluster 


## Proposed Repo Structure 

```
springboot-micrometer-hol/
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   └── com/hol/springbootmicrometer
│       │       ├── SpringbootMicrometerLabApplication.java
│       │       └── controller/
│       │           └── HelloController.java
│       └── resources/
│           └── application.properties
├── docker-compose/
│   ├── docker-compose.yaml            # Dev environment: Spring Boot + Prometheus + Grafana
│   └── docker-compose-docker.yaml     # Includes Spring Boot built via Jib Docker image
├── k8s/
│   ├── springboot-deployment.yaml
│   ├── prometheus-deployment.yaml
│   └── grafana-deployment.yaml
└── README.md
```

## Quick Start 
`todo `


