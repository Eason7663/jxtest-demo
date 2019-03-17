package org.jiuxin.spring.demo.service;import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;


/**
 * @ClassName TestConn
 * @Description TODO
 * @User Administrator
 * @Date 2019/3/9 16:19
 * @Version 1.0
 **/
public class TestConn {

    public static void main(String[] args) throws IOException, InterruptedException {

        InputStream stdout ;
        BufferedReader br ;
        String a;

        //通过URL获取连接
        Connection conn = new Connection("119.3.44.155");
        conn.connect();

        //输入用户名和密码 返回布尔变量true表示连接成功
        boolean isAuthenticated = conn.authenticateWithPassword("root", "jiuxin123");
        System.out.println(isAuthenticated);

        //获得session即打开会话框（终端）
        Session session = conn.openSession();

        //服务端编写脚本：vi + name 建立文本   写入待执行的命令   保存推出  chmod 755 +name 修改为可执行文件

        //执行脚本
        session.execCommand("pwd");

        //获得脚本执行之后界面的输出值，用于展示等
        stdout = new StreamGobbler(session.getStdout());
        br = new BufferedReader(new InputStreamReader(stdout));
        while((a = br.readLine())!=null){
            System.out.println(a);
        }

        //session.getExitStatus() 表示脚本执行成功与否，返回0则表示成功，非0则失败
        System.out.println("ExitCode: " + session.getExitStatus());
        conn.close();
    }
}