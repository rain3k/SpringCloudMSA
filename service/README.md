
http://localhost:8082/oauth/authorize?client_id=client&redirect_uri=http://localhost:8001/whoami&response_type=code&scope=read_profile


인증키를 가져오는 방법
Oauth2에서 키 발급시 Basic Auth를 통해 인증하며 Basic Auth는 client_id와 Client_secret키 값을 이용해서 인증한다. 
Client_secret키값을 Basic Auth로 인코딩하면 Spring Security에서 디코딩한뒤 키 값을 다시 Bcrypt하여 Client_secret값과 비교하여 인증한다.

하단 예의 경우 Y2xpZW50OjEyMzQ=는 1234의 Basic Auth인코딩값이며 DB의 Client_id는 1234의 BCrypt값인 $2a$10$4HUOXA455WD1DZJmim/Mbuxg9QmopIsao9YJcMhjcYB4GnX.Dk15K로 저장되어 있어야 한다. 
  
curl -X POST \
  http://localhost:8082/oauth/token \
  -H 'Authorization: Basic Y2xpZW50OjEyMzQ=' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d 'code=4jM7lM&grant_type=authorization_code&redirect_uri=http%3A%2F%2Flocalhost%3A8001%2Fwhoami&scope=read_profile'
  
 
 
 curl -X GET \
  http://localhost:8080/api/session \
  -H 'Authorization: Bearer 28871cd9-ca67-492c-94a7-3c00409f015a'
 