package org.jiuxin.spring.demo.service;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.jiuxin.spring.demo.domain.SSHClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @ClassName SSHClientService
 * @Description TODO
 * @User Administrator
 * @Date 2019/3/9 18:37
 * @Version 1.0
 **/
@Service
public class SSHClientService {

    @Autowired
    private SSHClient sshClient;

    private static final int TIME_OUT = 1000 * 5 * 60;


    private Session initSession(){
        Session session =null;
        try {
            Connection conn = new Connection(sshClient.getHostIp());
            conn.connect();
            boolean isAuthencatied = conn.authenticateWithPassword(sshClient.getUsername(),sshClient.getPassword());
            if(isAuthencatied) {
                session = conn.openSession();
                return session;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }

    public String exeCmd(String cmdStr){

        InputStream stdOut = null;
        InputStream stdErr = null;
        String outStr = "";
        String outErr = "";

        BufferedReader br ;
        String a;
        Session session = initSession();

        try {
            String cmd = "cd /opt&&cd app&&./test";
            session.execCommand(cmd);


            stdOut = new StreamGobbler(session.getStdout());
            outStr = processStream(stdOut, "utf8");

            stdErr = new StreamGobbler(session.getStderr());
            outErr = processStream(stdErr, "utf8");

            session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);

            System.out.println("outStr=" + outStr);
            System.out.println("outErr=" + outErr);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        if (0 == session.getExitStatus()) {
            return outStr;
        }else {
            return outErr;
        }
    }

    private String processStream(InputStream in, String charset) throws Exception {
        byte[] buf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (in.read(buf) != -1) {
            sb.append(new String(buf, charset));
        }
        return sb.toString();
    }

}
