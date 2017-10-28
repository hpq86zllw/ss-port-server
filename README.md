# 管理ss端口的微信小程序（服务端）  
## 相关  
[服务端](https://github.com/hpq86zllw/ss-port-server)  
[代理端](https://github.com/hpq86zllw/ss-port-agent)  
[客户端](https://github.com/hpq86zllw/ss-port-client)  
关系图  
![](https://raw.githubusercontent.com/hpq86zllw/asset-repository/master/img/relation.jpg)  
## 功能  
与客户端通信    
收集由代理端传递过来的端口流量数据  
向代理端发送命令，如添加端口和删除端口等  
当用户流量超过最大可用值时，禁用端口  
## 使用技术  
框架 Spring Boot  
数据库 H2  
缓存 Ehcache  
通信 RESTful API  
## 运行要求  
只支持python版的ss  
## 构建  
1. 运行maven生成jar包（需要JDK8）  
使用mvn clean package  
2. 把target/ss-port-server-0.0.1.jar放在服务端实际运行的服务器中，并创建如下文件夹  
logs，run，tomcat和config  
3. 在config文件夹中创建application.properties，在里面配置如下参数  
port=启动端口  
appid=微信小程序appid  
appsecret=微信小程序appsecret  
## 运行方法  
nohup java -jar ss-port-server-0.0.1.jar &> logs/nohup.out &  
导入agent.sql，port.sql和user.sql  
## 导入sql文件  
1. 下载[H2客户端](http://www.h2database.com/html/main.html)  
2. 运行bin/h2.sh，用浏览器访问服务器的8082端口， 连接信息按照源码里application.properties里面datasource信息进行填写  
3. 复制文件内容进去直接运行即可  