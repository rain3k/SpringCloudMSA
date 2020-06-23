# SpringCloudMSA

## 폴더 설명
config : Config 서버로 여러개의 Spring Boot기반 서버 설정을 통합 관리 한다.

config_init : config 서버에서 사용할 서버 설정들이 저장되어있음.

gatway : 부하 분산을 위한 gateway로 최초 Request받고 service,service2로 부하분산 및 Failover 처리를 해준다.

oauthclient : service 서버에서 제공하고 있는 oauth 2.0와 연동되는 Client로 개발하려함

registry : Eureka 기반으로 Spring boot으로 Application을 개발하고 서버를 실행하면 Eureka에 Domain(IP+Port)을 생성해준다. 

resource-server : oauth2.0인증 완료후 취특한 토큰으로 해당서버에 요청하면 Contents를 응답해준다.

service : oAuth 2.0 기능 및 관리시스템을 위해 개발 중이며 계정인증은 JWT기반으로 하고 있음.

service_frontend : service 관리 페이지를 위한 frontend UI로 reactjs, bootstrap기반으로 개발 중 

service2 : gateway의 부하 분산 기능 테스트를 위해 만든 서버 


## 실행순서
config -> registry -> service

# service_frontend 실행 명령어
cd service_frontend
npm start

# Prometheus 실행 명령어
prometheus-2.13.0.darwin-amd64/prometheus --config.file=../service/src/main/resources/prometheus.yml 

# Zipkin 실행 명령어
java -jar zipkin.jar