package org.jiuxin.spring.demo.controller;

import org.apache.commons.io.IOUtils;
import org.jiuxin.spring.demo.model.ResponseEntity;
import org.jiuxin.spring.demo.service.MqttClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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

    private final MqttClientService mqttClientService;

    @Autowired
    public FileController(MqttClientService mqttClientService) {
        this.mqttClientService = mqttClientService;
    }

    @GetMapping("/file")
    public String fileIndex(){
        return "fileupload";
    }

    @PostMapping("/file/upload")
    @ResponseBody
    public ResponseEntity<?> fileUpload(@RequestParam("file") MultipartFile file,
                             @RequestParam(value = "topic",required = false) String topic){
        if (file.isEmpty()){
//            return "上传失败，请选择文件";
            return new ResponseEntity<>(false,"上传失败，请选择文件",null);
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
//            return "配置完成下发";
            return new ResponseEntity<>(true,"配置下发完成",null);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
//        return "上传失败";
        return new ResponseEntity<>(false,"上传失败",null);
    }

    /**
     * ajax文件上传，有进度条功能
     * @param file
     * @return
     */
    @ResponseBody
    @PostMapping("/to/upload")
    public ResponseEntity<?> uploadFile(@RequestPart("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return new ResponseEntity<>(false, "file is empty", null);
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 文件存储路径
        String filePath = "D:/data/" + UUID.randomUUID().toString().replaceAll("-", "") + "_" + fileName;
//        logger.info("save file to:" + filePath);
        File dest = new File(filePath);
        if (!dest.getParentFile().exists()) {
            //noinspection ResultOfMethodCallIgnored
            dest.getParentFile().mkdirs();
        }
        try {
//            file.transferTo(dest);
            file.getInputStream();
            String str = IOUtils.toString(file.getInputStream(), "utf-8");
//            System.out.println(str.split("\r\n").length);
            String[] message = str.split("\r\n");
            for (int i = 0; i < 1; i++) {
                Thread.sleep(2000);
                mqttClientService.publish("testtopic","test message");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, e.getMessage(), null);
        }
        return new ResponseEntity<>(true, "success", null);
    }

}
