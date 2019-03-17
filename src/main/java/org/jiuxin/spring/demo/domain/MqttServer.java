package org.jiuxin.spring.demo.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName MqttClient
 * @Description TODO
 * @User Administrator
 * @Date 2019/3/16 23:17
 * @Version 1.0
 **/
@Component
public class MqttServer {
    @Value("${mqtt.hostip}")
    private String hostip;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    public String getHostip() {
        return hostip;
    }

    public void setHostip(String hostip) {
        this.hostip = hostip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
