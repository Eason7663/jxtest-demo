package org.jiuxin.spring.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jiuxin.spring.demo.model.ResponseEntity;
import org.jiuxin.spring.demo.service.MqttClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
/**
 * @author : Eason
 * @date: 2019/3/20
 * @description:
 */
@RestController
public class UpdateController {
    @Autowired
    private MqttClientService mqttClientService;

    @GetMapping("/update")
    public ResponseEntity<?> updateGw(){
        String updateTopic = "com/2000000003/1000000112/update";
        Map<String,String> map = new HashMap<>();
        map.put("macaddr","99:00:AA:AA:00:4A");
        map.put("type","2");
        map.put("filename","APP-IIG1000-V1.0.0.4-2.bin");
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));
//        mqttClientService.publish(updateTopic,itemJSONObj.toString());
        mqttClientService.subscribe();
        return new ResponseEntity<>(true,"success",null);
    }
}
