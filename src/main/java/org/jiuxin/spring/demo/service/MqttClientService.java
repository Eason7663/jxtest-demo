package org.jiuxin.spring.demo.service;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.jiuxin.spring.demo.domain.MqttServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName MqttClientService
 * @Description TODO
 * @User Administrator
 * @Date 2019/3/16 23:20
 * @Version 1.0
 **/
@Service
public class MqttClientService {
    @Autowired
    private MqttServer mqttServer;

    public void publish(String topic, String content){
//        String topic = "mqtt/test";
//        String content = "hello 哈哈";
        int qos = 1;
        String broker = "tcp://emqceshi.jiuxiniot.com:1883";
        String userName = "admin";
        String password = "public";
        String clientId = "pubClient";
        //内存存储
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            //创建客户端
            MqttClient mqttClient = new MqttClient(broker,clientId,persistence);
            //创建连接参数
            MqttConnectOptions connOptions = new MqttConnectOptions();
            //在重新启动和重新连接时，记住状态
            connOptions.setCleanSession(false);
            //设置连接用户名
            connOptions.setUserName("admin");
            connOptions.setPassword("public".toCharArray());
            //建立连接
            mqttClient.connect(connOptions);
            //创建消息
            MqttMessage mqttMessage = new MqttMessage(content.getBytes());
            //设置消息服务器的质量
            mqttMessage.setQos(0);
            //发布消息
            mqttClient.publish(topic,mqttMessage);
            //断开连接
            mqttClient.disconnect();
            //关闭客户端
            mqttClient.close();

        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}
