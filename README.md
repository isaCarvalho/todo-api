# TO DO API

This is a POC for learning spring boot with Kotlin + some other stuff.

# Kubernetes Deployment

To deploy this application to kubernetes using [Minikube](https://minikube.sigs.k8s.io/docs/) to the following:

1. Start minikube using the command

```shell
minikube start --memory 2048 --cpus 2 --disk-size 10g --kubernetes-version v1.18.8
```

2. Run 

```shell
eval $(minikube docker-env)
```

or `minikube docker-env | Invoke-Expression` if you are using Windows;

4. Build the docker image using the command

```shell
docker build -t isacarvalho/todo-api:latest .
```

4. Install de application using helm with the command:

```shell
helm upgrade --install todo-api charts --values charts/values.yaml
```

5. Check if the application was correctly deployed using the command ``

```shell
kubectl get pods
```

6. Get the url to access the application

```shell
minikube service todo-api --url
```