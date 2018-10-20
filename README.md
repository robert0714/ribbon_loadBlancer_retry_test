# ribbon_loadBlancer_retry_test

# Senario
當Eureka client的服務兩個實體中，其中一個掛掉時，其他服務在透過eureka 呼叫時，是否會跟nginx一樣遇到壞掉就會換下一個實體發送（有兩難，時間過太久，有時真的是處理太久）

# Solution
instance關機程序加入DiscoveryClient.shutdown()，EurekaDiscoveryClientConfiguration.stop()使用ribbon retry太危險

**https://github.com/spring-cloud/spring-cloud-netflix/issues/1543**
**https://stackoverflow.com/questions/49232549/programatically-unregister-instance-from-eureka**
**eureka.client.healthcheck.enabled: true" (Brixton.SR7) #1571**

#  For windows developer　
windows要先安裝Docker-toolbox 
*https://download.docker.com/win/stable/DockerToolbox.exe*

eclipse plugins 

*https://www.eclipse.org/community/eclipse_newsletter/2016/july/article2.php*


```
docker-machine create  -virtualbox-memory 4096 -virtualbox-cpu-count 2 default   

# Test URL

http://192.168.99.100:9411/zipkin

http://192.168.99.100:3000/api/user/greet

http://192.168.99.100:8761/