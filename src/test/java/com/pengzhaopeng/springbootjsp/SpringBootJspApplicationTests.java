package com.pengzhaopeng.springbootjsp;

import com.pengzhaopeng.springbootjsp.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJspApplicationTests {

//    @Autowired
//    DataSource dataSource;

//    @Test
//    public void contextLoads() throws SQLException {
//        System.out.println(dataSource.getClass());
//        Connection connection = dataSource.getConnection();
//        System.out.println(connection);
//        connection.close();
//    }

    @Test
    public void contextLoads() throws SQLException {
    }

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    public void test2() throws MessagingException {
        //1、创建一个复杂的消息邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //邮件设置
        helper.setSubject("通知-今晚开会");
        helper.setText("<b style='color:red'> 今天7:30开会</b>",true);

        helper.setTo("17688700269@163.com");
        helper.setFrom("763745153@qq.com");

        //上传文件
        helper.addAttachment("1.jpg",new File("C:\\Users\\Administrator\\Desktop\\表情包\\v2-38e420cbc4b71732e529cfcd7642ee1c_hd.jpg"));
        //helper.addAttachment("2.jpg",new File(""));
        javaMailSender.send(mimeMessage);
    }

}
