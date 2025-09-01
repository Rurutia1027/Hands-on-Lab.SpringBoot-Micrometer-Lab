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

### Docker Image 
The Spring Boot Micrometer Hands-on Lab is available as a [Docker image](https://hub.docker.com/r/nanachi1027/springboot-micrometer-hol) for easy local or remote use. 

#### Image Details 

| Repository | Tag | Size | Last Updated |
|------------|-----|------|--------------|
| `nanachi1027/springboot-micrometer-hol` | `latest` | 209.6 MB | 8 minutes ago |

#### Pull the Image 
To pull the image from Docker Hub:

```bash
docker pull nanachi1027/springboot-micrometer-hol:latest
```

**Note**: Running the image standalone is not recommended, since the application configuration depends on external Prometheus and Grafana services. It is suggested to use the provided [docker-compose.yaml](./springboot-micrometer-hol/docker-compose/docker-compose.yaml) in this project for proper setup and integration.

```bash 
# setup docker compose 
cd docker-compose/ && docker compose -p hands-on-lab -f docker-compose.yaml up -d

# shutdown docker compose 
docker compose -p hands-on-lab -f docker-compose.yaml down
```

