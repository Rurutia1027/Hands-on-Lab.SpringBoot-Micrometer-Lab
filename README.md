# Hands-on Lab: Spring Boot + Micrometer + Prometheus + Jib + Kubernetes | [![CI Pipeline for Hands-on Lab](https://github.com/Rurutia1027/Hands-on-Lab.SpringBoot-Micrometer-Lab/actions/workflows/ci-pipeline.yaml/badge.svg)](https://github.com/Rurutia1027/Hands-on-Lab.SpringBoot-Micrometer-Lab/actions/workflows/ci-pipeline.yaml)

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
⚠️ Note: Docker Compose and Kind K8s scripts cannot run simultaneously because of overlapping ports. Run one at a time.

### Start with Docker Image 
Use the script ./bin/run-with-docker-compose.sh to start the environment locally:
```bash
./bin/run-with-docker-compose.sh
```

Ports:
- 8080 → Spring Boot app (/hello endpoint for verification)
- 31000 → Prometheus
- 32000 → Grafana (login: admin/admin)

Verification:
- Check Spring Boot
```
curl http://localhost:8080/hello
```

- Access Prometheus via
```
http://localhost:31000
```

- Access Grafana
```
http://localhost:32000
```

The Grafana dashboards are preloaded via configuration and display metrics collected from:
- Custom AOP metrics
- Interceptor/Servlet layer metrics
- Micrometer + Actuator default metrics


### Start with K8S Cluster via Kind
Use the script `./bin/run-with-k8s-cluster.sh` to deploy everything on a local Kind cluster:
```bash
./bin/run-with-k8s-cluster.sh
```

Ports:
- 31808 → Spring Boot app (/hello endpoint for verification)
- 31000 → Prometheus
- 32000 → Grafana (login: admin/admin)

Verification:
- Check Spring Boot pod:
```
curl http://localhost:31808/hello
```

- Access Prometheus:

```
http://localhost:31000
```

- Access Grafana:
```
http://localhost:32000
```

The Grafana dashboards are preloaded via ConfigMaps and visualize the same metrics as in Docker Compose, allowing you to explore custom and native metrics out-of-the-box.

## Dashboard Snapshots

- Grafana Dashabord
  
![Uploading Screenshot 2025-09-01 at 22.52.54.png…]()

<img width="1794" height="1106" alt="Screenshot 2025-09-01 at 22 44 32" src="https://github.com/user-attachments/assets/0b5411af-5629-40b7-ba1f-94273c936eaf" />

<img width="1796" height="1044" alt="Screenshot 2025-09-01 at 22 44 42" src="https://github.com/user-attachments/assets/6d7b9695-c7ac-42e8-9214-c0a2e08958c3" />

- Prometheus Rules 

<img width="1795" height="890" alt="Screenshot 2025-09-01 at 22 45 18" src="https://github.com/user-attachments/assets/60649321-760e-45bf-a8b8-2b8a0d709f7a" />

