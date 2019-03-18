package org.jiuxin.spring.demo.controller;

import org.apache.commons.io.IOUtils;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.jiuxin.spring.demo.service.MqttClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName FileController
 * @Description TODO
 * @User Administrator
 * @Date 2019/3/17 13:58
 * @Version 1.0
 **/
@Controller
@RequestMapping("/api")
public class FileController {
    @Autowired
    private MqttClientService mqttClientService;

    @GetMapping("/file")
    public Object fileIndex(){
        return "index";
    }
    @PostMapping("/file/upload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file,
                             @RequestParam(value = "topic",required = false) String topic){
        if (file.isEmpty()){
            return "上传失败，请选择文件";
        }
//        String fileName = file.getOriginalFilename();
//        String filePath = "";
//        File dest = new File(filePath + fileName);
//        if (!dest.exists()){
//            dest.mkdirs();
//        }
        try {
//            file.transferTo(dest);
            file.getInputStream();
            String str = IOUtils.toString(file.getInputStream(), "utf-8");
//            System.out.println(str.split("\r\n").length);
            String[] message = str.split("\r\n");
            for (int i = 0; i < message.length; i++) {
                Thread.sleep(2000);
                mqttClientService.publish(topic,message[i]);
            }
            return "配置完成下发";

        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "上传失败";
    }


}
