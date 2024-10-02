docker build -f Dockerfile.native -t contact-quarkus .
kind load docker-image contact-quarkus --name demo-quarkus
kubectl create -f kubernetes/k8s.yml