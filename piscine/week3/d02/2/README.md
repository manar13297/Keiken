# Contrasting Kubernetes Distributions
Minikube is a lightweight tool designed for local development and testing of Kubernetes applications. It allows developers to quickly spin up a single-node Kubernetes cluster on their local machine, providing an environment similar to a full Kubernetes cluster but with minimal resource requirements.
![text](scshots/enable_hyperv.png)
*Enabling Microsoft Hyper-V*
-------------------

![text](scshots/vmswitch.png)
*Create an external virtual switch named minikube*
-------------------

![text](scshots/install_minikube.png)
*install minikube*
-------------------

![text](scshots/start_minikube_cluster.png)
*start minikube cluster*
-------------------

![text](scshots/verify_minikube_status.png)
*verify minikube status*
-------------------

![text](scshots/extensions_list.png)
*check extensions list*
-------------------

![text](scshots/ingress_addons.png)
*add ingress add-on*
-------------------

![text](scshots/hosts_file_update.png)
*associate minkube ip address with the hostname host.example.com in hosts file*
-------------------

![text](scshots/dashboard_addons.png)
*add dashboard add-on*
-------------------

![text](scshots/minikube_dash.png)
*minikube dashboard*
# Connecting kubectl to Your Cluster
![text](scshots/mkdir_kube.png)
*create kube directory under C:/*
- Install kubectl
![text](scshots/download_kubcctl.png)
*Download kubectl*
-------------------

![text](scshots/add_env_kube.png)
*Add kubectl to path*
-------------------

![text](scshots/check_kubectl_version.png)
*Check kubectl version*
- Connect to Minikube
  - if it's not already installed, [install openSSSL](https://www.youtube.com/watch?v=PgP9oGGxLG0)
  - clone the [git repo](https://github.com/RedHatTraining/DO100-apps.git)

  I have a windows operating system, so I executed `.\setup\windows\setup.ps1`

![text](scshots/oops.png)
***Ooops ERROR***

If you encountered the same error (username starts with a capital letter), you need to do a tiny change in the setup.ps1 file.

change the first line of `setup.ps1` file by this line: $USERNAME = $env:USERNAME.ToLower()

Execute again the script.

![text](scshots/execute_script.png)
*And it should be executed successfully*

# Running and Interacting with Your First Application
- Create Pods From Container Images
  ![text](scshots/create_pod.png)
- Create deployment
  ![text](scshots/create_deployment.png)
- exec commands within an existing pod
  ![text](scshots/exec_ls.png)

# Deploying managed application
- Create a deployment
  ![text](scshots/create_deployment_1.png)
-------------------
>kubectl is a command-line tool that allows us to interact with Kubernetes clusters. It serves as the primary interface for managing Kubernetes clusters, including deploying applications, inspecting cluster resources, debugging issues, and performing various administrative tasks.
>
>With kubectl, we can perform operations such as: deploying applications; managing pods, deployments and more; interacting with pods, etc.
> 
