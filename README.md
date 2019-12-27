# SpringCloudMSA

실행순서 : config -> registry -> 기타 ...

config
config_init
gatway
oauth
oauthclient
registry
resource-server
service
service2

# Prometheus 실행 명령어
prometheus-2.13.0.darwin-amd64/prometheus --config.file=../service/src/main/resources/prometheus.yml 

# Zipkin 실행 명령어
java -jar zipkin.jar