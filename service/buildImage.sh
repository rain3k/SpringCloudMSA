docker build --file=Dockerfile --tag=service:latest --rm=true .


kubectl run service --image=service:latest --port=8082 --image-pull-policy Never

