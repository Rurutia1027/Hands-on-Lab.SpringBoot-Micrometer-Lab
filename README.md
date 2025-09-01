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
Hands-on-Lab.SpringBoot-Micrometer-Lab/
├── README.md
├── bin/
│   └── setup-k8s-cluster.sh          # Script to create K8s cluster
├── configs/
│   └── kind-config.yaml              # KIND cluster configuration
├── k8s/
│   ├── springboot-deployment.yaml
│   ├── prometheus-deployment.yaml
│   ├── prometheus-configmap.yaml
│   └── grafana-deployment.yaml
├── springboot-micrometer-hol/
│   ├── pom.xml
│   ├── src/
│   │   └── main/
│   │       ├── java/com/hol/springbootmicrometer/
│   │       │   ├── SpringBootMicrometerHolApplication.java
│   │       │   └── controller/HelloController.java
│   │       └── resources/
│   │           ├── application.yaml
│   │           └── application-docker.yaml
│   └── docker-compose/
│       ├── docker-compose.yaml           # Dev environment: Prometheus + Grafana
│       ├── docker-compose-docker.yaml    # Includes Spring Boot Docker image
│       └── prometheus/
│           └── prometheus.yml            # Prometheus configuration
```

## Metrics Flow  

### Environment Verification Table 
- **Spring Boot**: `curl http://localhost:8080/actuator`

- **Micrometer**: `curl http://localhost:8080/actuator/prometheus`

- **Prometheus**: `http://localhost:31000` -> Status -> Targets

- **Grafana**: `http://localhost:32000` -> Data source -> Panels 

### Metrics Flow Diagram 
```
Spring Boot App (8080)
        │
        │ exposes /actuator and /actuator/prometheus
        ▼
    Micrometer
        │
        │ collects metrics
        ▼
    Prometheus (31000)
        │
        │ scrapes metrics
        ▼
    Grafana (32000)
        │
        │ visualizes metrics
        ▼
   Dashboard / Panels
```

## Quick Start 
`todo `


