# Project

SSAFY Final Project 개인 중고 물품 경매 사이트 'OBOSA'



## Project Spec

WEB FrontEnd : Vue.js, Vuetify, Vuex

BackEnd : Java Springboot 2.1.9 RELEASE
	자바 버전 :  JAVA 8
	ORM 기술 : Hibernate JPA



## 서버 정보

|             |               역할                | host | Domain |
| :---------: | :-------------------------------: | :--: | :----: |
| **AWS EC2** |      API 어플리케이션 서버 1      |      |        |
|             |      API 어플리케이션 서버 2      |      |        |
|             | API Load Balancer & Reverse Proxy |      |        |
| **AWS RDS** |          MySQL DB Server          |      |        |
| **AWS S3**  |       Remote 파일 스토리지        |      |        |

 

## 배포 단계(1차 스펙)

#### 1. ec2 접속

```cmd
ssh -i "keyfile" ec2-user@13.124.201.59
```



#### 2. 파일전송

```cmd
scp -i "keyfile" "전송할 파일" ec2-user@13.124.201.59:~/
```



#### 3. jar 실행

```cmd
java -jar "파일명"
```



#### 4. ec2에서 실시간 로그 보기

~~~cmd
tail -f "filename"
~~~



## 패키지 설명

|           패키지명           |                         간략한 설명                          |
| :--------------------------: | :----------------------------------------------------------: |
|  com.ssafy.obosa.annotation  |                   Custom Annotation을 저장                   |
|     com.ssafy.obosa.aop      | 어노테이션의 포인트컷 적용 시점(CRUD 중 R을 뺀 나머지에 적용하기 위해 사용) <br/>관점 지향 프로그래밍 도입 |
|    com.ssafy.obosa.config    |       AWS S3, Redis, Swagger, Jpa, Mvc 관련 설정 파일        |
|  com.ssafy.obosa.controller  |                     컨트롤러 관련 클래스                     |
| com.ssafy.obosa.enumeration  | Status Code, Response Message, Jwt 만료 기간 관련 Enum 클래스 |
|    com.ssafy.obosa.model     |                   dto, 도메인 관련 클래스                    |
| com.ssafy.obosa.registration |                       email 인증 관련                        |
|  com.ssafy.obosa.repository  |                  jpa repository 관련 클래스                  |
|   com.ssafy.obosa.service    |              Business Logic 관련 Service 클래스              |
|     com.ssafy.obosa.util     | 단방향/양방향 암호화를 위한 Utility 클래스 <br />AWS S3관련 Utility 클래스<br /> ImgHandler Component 클래스 |
|  com.ssafy.obosa.validation  |               Backend 유효성 검사 관련 클래스                |



## Logback

**Logback**은 "자바 오픈소스 로깅 프레임워크"로 SLF4J의 구현체이자 **스프링 부트의 기본 로그** 객체다.

log4j, log4j2, JUL(java.util.logging)과 성능을 비교했을 때 logback은 훌륭한 성능을 보여준다.

그리고 결정적으로 자바 프로그램에서 로그를 사용할 때 가장 많이 사용되고 있기 때문에 알아두어야 한다.

출처: https://jeong-pro.tistory.com/154 [기본기를 쌓는 정아마추어 코딩블로그]



```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <include resource="org/springframework/boot/logging/logback/base.xml"/>

    <appender name="dailyRollingFileAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <prudent>true</prudent>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>./log/dowadog.%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>DEBUG</level>
        </filter>

        <encoder>
            <pattern>%d{yyyy:MM:dd HH:mm:ss.SSS} %-5level --- [%thread] %logger{35} : %msg %n</pattern>
        </encoder>
    </appender>

    <logger name="org.springframework.web" level="INFO"/>
    <logger name="com.sopt.dowadog" level="INFO"/>
    <logger name="org.hibernate.sql" level="ERROR"/>

    <root level="INFO">
        <appender-ref ref="dailyRollingFileAppender" />
    </root>
</configuration>
```

* maxHistory : 30일
* Logging 수준 : Debug 이상만 세팅(전역), 각 패키지별 Logging 수준 별도 세팅



## 최종 인프라 다이어그램 모델 구상도

![obosa architecture]( https://obosa.s3.ap-northeast-2.amazonaws.com/obosa/obosa+Structure.png)