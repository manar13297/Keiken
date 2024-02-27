# How to Install Kubernetes Cluster from Scratch?

> This lab is conducted within an Ubuntu virtual machine environment.
## Step 1: create your virtual machine and set it up within VirtualBox or any other virtualization software.
## Step 2: access your VM via SSH or utilize the interface provided by your virtualization software to launch the VM.


## Step 3: install docker
```bash 
apt update
```
```bash 
apt install docker.io -y 
```

![text](scshots/install_docker.png?raw=true)

>Docker here acts as the 'farmer on the ground,' executing instructions from the Kubernetes control plane. Kubernetes, like the overseer, ensures that Docker manages containers according to the specified guidelines. 
> 
> Docker is the container engine that Kubernetes utilizes to create and manage containers.

## Step 4: download kubernetes binaries
We can download kubernetes using various ways. In this lab, we will use binaries provided by [google storage api](https://console.cloud.google.com/storage/browser/_details/kubernetes-release/release/v1.9.11/kubernetes-server-linux-amd64.tar.gz;tab=live_object).
```bash 
wget https://storage.googleapis.com/kubernetes-release/release/v1.9.11/kubernetes-server-linux-amd64.tar.gz
```
```bash 
tar -xzf kubernetes-server-linux-amd64.tar.gz
```
![text](scshots/install_kub.png?raw=true)
```bash 
cd kubernetes/server/bin
```
```bash 
ls
```
![text](scshots/installed_bin_kub.png?raw=true)
*Of these installed binaries, we only require kube-apiserver, kubectl, kubelet, kube-proxy, kube-scheduler and kube-controller-manager.We'll move them to /usr/bin so we can access them from our PATH.*
```bash 
mv kube-apiserver kubectl kubelet kube-proxy kube-scheduler kube-controller-manager /usr/bin
```
## Step 4: install kubelet
- Create a directory for kubernetes manifests for the kubelet.
```bash 
mkdir -p /etc/kubernetes/manifests
```
*In this directory, we will put pod manifests and kubelet will start pods that way without needing any of other parts of the cube: `standalone mode`*
- Start kubelet
```bash 
kubelet --pod-manifest-path /etc/kubernetes/manifests &> /etc/kubernetes/kubelet.log & 
```
>This command starts the kubelet service and redirects both standard output (stdout) and standard error (stderr) to a log file located at /etc/kubernetes/kubelet.log.
>
> `--pod-manifest-path`: this flag specfies  the directory path where the kubelet should look for pod manifest files.
>
> `&>`: This is a shell redirection operator that redirects both stdout and stderr to a file.
- Check kubelet process
```bash 
ps -au | grep kubelet
```
![text](scshots/install_kubelet.png?raw=true)
- check logs 

```bash 
head /etc/kubernetes/kubelet.log
```
![text](scshots/kubelet_logs_1.png?raw=true)

## Step 5: Create a pod
- Create a file in etc/kubernetes/manifests directory (The file wii be a kubernetes pod).
```bash 
nano /etc/kubernetes/manifests/kubelet-test.yaml
```
Paste this inside the file:
>     apiVersion: v1 
> 
>     kind: Pod 
> 
>     metadata:
> 
>       name: kubelet-test1 
> 
>     spec:
> 
>       containers:
> 
>         name: alpine 
> 
>         image: alpine 
> 
>         command: ["/bin/sh", "-c"]
>
>         args: ["while true; do echo k_intenship_2024; sleep 15; done"]


*when this Pod is created in Kubernetes, it will launch a single container based on the Alpine Linux image. This container will continuously echo "k_intenship_2024" every 15 seconds.*

```bash 
docker ps
```
![text](scshots/docker_ps.png?raw=true)

*If everything went okey, we must see our pod started* (we can see a /pause container. It is not just a container running, it's a pod.)
>**Note**
>
> If you can't see your pod, check your kubelet.log file.
> If there is an error message like: mountpoint to cpu not found, check this [link](problem_mountpointCpu.md) to solve your problem
> make sure that swap is disabled. (this command will disable it until the next boot `swapoff -a`)

- check if the pod is doing its job
```bash 
docker logs <your_container_id>
```
![text](scshots/print_kintern.png?raw=true)

## Step 6: kubernetes api server

### install and start etcd
**In order to set up our kubernetes api server we should have etcd as a flag that the api servers pointed to.**

etcd wil be the state store of our cluster. It's kind of a library where the history and the information for our cluster can be stored.
- Get etcd
```bash
wget https://github.com/etcd-io/etcd/releases/download/v3.2.26/etcd-v3.2.26-linux-amd64.tar.gz
```
```bash
tar -xzf etcd-v3.2.26-linux-amd64.tar.gz
mv etcd-v3.2.26-linux-amd64/etcd /usr/bin/etcd
mv etcd-v3.2.26-linux-amd64/etcdctl /usr/bin/etcdctl
```
- Start etcd
```bash
etcd --listen-client-urls http://0.0.0.0:2379 --advertise-client-urls http://localhost:2379 &> /etc/kubernetes/etcd.log &
```
- Check cluster health
```bash
etcdctl cluster-health
```

![text](scshots/etcd_healthy.png?raw=true)

*etcd will accept client connections from any IP address on port 2379. It will advertise itself as available for client connections only on localhost.*

### Start kubernetes api server
```bash
kube-apiserver --etcd-servers=http://localhost:2379 --service-cluster-ip-range-10.0.0.0/16 --bind-address-0.0.0.0 --insecure-bind-address-0.0.0.0 &> /etc/kubernetes/apiserver.log &
```
```bash
ps -au | grep apiserver
```
![text](scshots/ps_apiserver.png?raw=true)

- Interact with the Kubernetes API server.