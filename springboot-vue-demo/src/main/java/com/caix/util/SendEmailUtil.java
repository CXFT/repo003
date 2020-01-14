package com.caix.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class SendEmailUtil {

    //用于发送文件
    @Resource
     JavaMailSender mailSender;

    //使用@Value注入application.properties中指定的用户名
    @Value("3345347614@qq.com")
    private String from;

    /* 邮件发送*/
    public void sendSimpleMail(String toStudent, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        //收信人
        message.setTo(toStudent);
        //主题
        message.setSubject(subject);
        //内容
        message.setText(content);
        //发信人
        message.setFrom(from);
        mailSender.send(message);
    }

    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message=mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            FileSystemResource file=new FileSystemResource(new File(filePath));
            String fileName=filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName,file);
            mailSender.send(message);
            System.out.println("带附件的邮件发送成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("发送带附件的邮件失败");
        }
    }
}

