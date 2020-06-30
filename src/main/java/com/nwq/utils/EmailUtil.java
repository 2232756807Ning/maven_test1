package com.nwq.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * @Author: nwq
 * @Description:
 * @Date: 2020/6/30 9:48
 * @Version: 1.0
 */
public class EmailUtil {
    /**
     * @Author nwq
     * @Description
     * @Date  2020/6/30 10:49
     * @Param [email, code]
     * @return boolean
     **/

    public static boolean send(String email, String code) {
        try {
            //设置发件人
            String from = "1223127421@qq.com";
            //设置收件人
            String to = email;
            //设置邮件发送的服务器，这里为QQ邮件服务器
            String host = "smtp.qq.com";

            //获取系统属性
            Properties properties = System.getProperties();

            //SSL加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            //设置系统属性
            properties.setProperty("mail.smtp.host", host);
            properties.put("mail.smtp.auth", "true");

            //获取发送邮件会话、获取第三方登录授权码
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, "ofrvevisrbtpbadc");
                }
            });

            Message message = new MimeMessage(session);

            //防止邮件被当然垃圾邮件处理，披上Outlook的马甲
            message.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //邮件标题
            message.setSubject("小标会议忘记密码验证码");
            BodyPart bodyPart = new MimeBodyPart();

            //邮件正文
            bodyPart.setText("验证码：" + code);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);

            //附件
//            bodyPart = new MimeBodyPart();
//            String fileName = "文件路径";
//            DataSource dataSource = new FileDataSource(fileName);
//            bodyPart.setDataHandler(new DataHandler(dataSource));
//            bodyPart.setFileName("文件显示的名称");
//            multipart.addBodyPart(bodyPart);

            message.setContent(multipart);

            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
