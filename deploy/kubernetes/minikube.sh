#!/bin/bash
CHECK_MINIKUBE=$(minikube status --format={{.Host}})
if [[ "$CHECK_MINIKUBE" = "Stopped" ]]; then
  minikube start --vm-driver=docker
fi;
eval $(minikube docker-env)
docker build --build-arg JAR_FILE=build/libs/wordle-0.0.1-SNAPSHOT.jar -t wordle .
minikube tunnel &>/dev/null &
kubectl apply -f deploy.yaml
echo "App live at: $(k get service -n wordle-app -o jsonpath='{.items[*].status.loadBalancer.ingress[].ip}')"
