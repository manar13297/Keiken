# Deploy chatgpt api on minikube using Helm Chart
>**Note**: Helm's capabilities extend far beyond what will be showcased in this demo. While our demonstration focused on substituting values in a values.yaml file to deploy our monolithic app, it's important to recognize that Helm serves a much broader purpose in the software development lifecycle. 
>
>One of Helm's key features is its ability to manage configuration across different environments. Instead of manually copying and pasting variables between development and production environments, Helm streamlines this process.
>
>Furthermore, Helm simplifies version management. With Helm charts, developers can easily upgrade applications to newer versions, taking advantage of the latest features and bug fixes. Conversely, if issues arise, Helm enables quick rollback to a previous version, minimizing downtime and ensuring stability.
------------------------------------------------------
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