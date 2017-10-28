# 管理ss端口的微信小程序（服务端）  
## 相关  
[服务端](https://github.com/hpq86zllw/ss-port-server)  
[代理端](https://github.com/hpq86zllw/ss-port-agent)  
[客户端](https://github.com/hpq86zllw/ss-port-client)  
## 构建  
1. 运行maven生成jar包  
使用mvn clean package  
2. 并创建如下文件夹  
logs，run，tomcat和config  
3. 在config文件夹中创建application.properties，在里面配置如下参数  
port=启动端口  
appid=微信小程序appid  
appsecret=微信小程序appsecret  
## 运行方法  
nohup java -jar ss-port-server-0.0.1.jar &> logs/nohup.out &  