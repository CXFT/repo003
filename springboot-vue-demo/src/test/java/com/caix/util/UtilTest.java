package com.caix.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilTest {

    @Resource
    private SendEmailUtil sendEmailUtil;

    /*短信测试*/
    @Test
    public void sendMsg(){
        //SendMessageUtil.send("SMS账户","接口秘钥","目标号码","发送内容");
        Integer resultCode = SendMessageUtil.send(
                "CXFT",
                "d41d8cd98f00b204e980",
                "18829536200",
                "最近过得好吗？给你一个验证码——"+ SendMessageUtil.getRandomCode(6));
        System.out.println(SendMessageUtil.getMessage(resultCode));
    }

    /*邮件测试*/
    @Test
    public void sendEmail(){
        sendEmailUtil.sendSimpleMail("1987793563@qq.com","人生之路","最近过得好吗？");
    }

    /*附件邮件测试*/
    @Test
    public void sendEmailAttachment(){
        sendEmailUtil.sendAttachmentsMail("1500416121@qq.com",
                "钉子户签到",
                "有附件，请查收",
                "D:\\desktop\\云创3营签到表.xlsx"
                );
    }
}
