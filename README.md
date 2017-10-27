# 管理ss端口的微信小程序（服务端）  
## 相关  
[服务端](https://github.com/hpq86zllw/ss-port-server)  
[代理端](https://github.com/hpq86zllw/ss-port-agent)  
[客户端](https://github.com/hpq86zllw/ss-port-client)  
## 运行前  
使用mvn clean package -Dhome=HOME -Dport=PORT -Dappid=APPID -Dappsecret=APPSECRET  
HOME 主目录  
PORT 启动端口  
APPID 微信小程序appid  
APPSECRET 微信小程序appsecret  
## 运行方法  
cp target/ss-port-server-0.0.1.jar HOME  
cd HOME  
mkdir logs  
mkdir run  
nohup java -jar ss-port-server-0.0.1.jar &> logs/nohup.out &  