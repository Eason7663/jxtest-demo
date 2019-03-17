package org.jiuxin.spring.demo.domain;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @ClassName SSHClient
 * @Description 自定义属性配置
 * @User Administrator
 * @Date 2019/3/9 17:11
 * @Version 1.0
 **/
@Component
//@ConfigurationProperties(prefix = "book")
public class SSHClient {

    @Value("${sshClient.hostip}")
    private String hostIp;
    @Value("${sshClient.username}")
    private String username;
    @Value("${sshClient.password}")
    private String password;

    public SSHClient(String hostIp, String username, String password) {
        this.hostIp = hostIp;
        this.username = username;
        this.password = password;
    }

    public SSHClient() {
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
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
