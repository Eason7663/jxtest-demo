package org.jiuxin.spring.demo.controller;

import org.jiuxin.spring.demo.domain.MqttServer;
import org.jiuxin.spring.demo.domain.SSHClient;
import org.jiuxin.spring.demo.service.MqttClientService;
import org.jiuxin.spring.demo.service.SSHClientService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DemoController
 * @Description TODO
 * @User Administrator
 * @Date 2019/3/1 23:00
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/v1")
public class DemoController {

//    @Autowired
//    private Book book;
    @Autowired
    private SSHClient sshClient;

    @Autowired
    private SSHClientService sshClientService;

    @Autowired
    private MqttServer mqttServer;

    @Autowired
    private MqttClientService mqttClientService;

    @GetMapping
    @ResponseBody
    public Object getAll(@RequestParam("page") int page,@RequestParam(value = "size",defaultValue = "10") int size){
        return "hello";
    }

    @GetMapping("/book/{id}")
    public Object getOne(@PathVariable long id){

        return null;
    }

    @GetMapping("/sshclient")
    public Object getSshClient(){
        return sshClient;
    }

    @GetMapping("/sshclient/cmd")
    public Object exeCmd(@RequestParam String cmdStr){
       return sshClientService.exeCmd(cmdStr);
    }

    @GetMapping("/mqttinfo")
    public Object getMqtt(){
        return mqttServer;
    }

    @GetMapping("/publisher")
    public Object publish(){
        mqttClientService.publish("testtopic","text");
        return "ok";
    }

}
