# Hands-on Lab: Spring Boot + Micrometer + Prometheus + Jib + Kubernetes 

## Objective  
By the end of this lab, participants will be able to: 
- Create a simple Spring Boot application with Micrometer metrics. 
- Expose metrics to Prometheus. 
- Dashboards for metrics displaying via Grafana. 
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
├── springboot-micrometer-hol/        # The Spring Boot app
│   ├── pom.xml
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/hol/springbootmicrometer/
│   │       │       ├── SpringBootMicrometerLabApplication.java
│   │       │       └── controller/
│   │       │           └── HelloController.java
│   │       └── resources/
│   │           └── application.properties
│   ├── docker-compose/                         # Local dev environment
│   │   ├── docker-compose.yaml                 # Dev environment: Spring Boot + Prometheus + Grafana
│   │   ├── docker-compose-docker.yaml          # Includes Spring Boot built via Jib Docker image
│   │   └── prometheus/
│   │       └── prometheus.yml
├── k8s/                               # Kubernetes manifests
│   ├── springboot-deployment.yaml
│   ├── prometheus-deployment.yaml
│   └── grafana-deployment.yaml
└── README.md                           # Root README describing the full lab
```

## Quick Start 
`todo `


