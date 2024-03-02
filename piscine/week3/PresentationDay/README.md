# Deploy chatgpt api on minikube using Helm Chart

## Prerequirements
- The app is already dockerized
  - jar generated
  - Dockerfile created
  - docker build -t app .
- minikube installed
- Kubernetes is enables in Docker Desktop

## Create helm chart
```bash
helm create chatgptchart
```
```bash
tree chatgptchart
```

# changing values.yaml
- repository
- service type
- service port

# start minikube
```bash
minikube -p minikube docker-env
```
Get-ChildItem Env:

# reevaulate docker env

```bash
minikube start --driver=docker
```

# build image 
```bash
docker build -t app .
```

$Env:DOCKER_HOST = "tcp://127.0.0.1:63802"

```bash
minikube image ls
```

# install and deploy helm chart on cluster
```bash
helm install mychart chatgptchart
```

# verify the configuration
```bash
kubectl get pods
```

```bash
kubectl get svc
```

# Access api
```bash
minikube service mychart-chatgptchart --url
```