package org.jiuxin.spring.demo.domain;

import lombok.Data;
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
@Data
public class MqttServer {
    @Value("${mqtt.hostip}")
    private String hostip;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;
}
