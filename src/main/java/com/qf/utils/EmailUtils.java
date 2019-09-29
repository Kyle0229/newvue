package com.qf.utils;

import com.qf.domain.Code;
import com.qf.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

@Component
public class EmailUtils {
    @Resource
    private UserMapper userMapper;
    @Autowired
    private  JavaMailSender  javaMailSender;
    @Value("${spring.mail.username}")
    private  String mail;
    public  int getCode(String email){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(mail);
            simpleMailMessage.setTo(email);
            String codeStr = getCodeString();
            simpleMailMessage.setText(codeStr);
            simpleMailMessage.setSentDate(new Date());
            simpleMailMessage.setSubject("验证码");
            javaMailSender.send(simpleMailMessage);
            Code code = new Code();
            code.setCode(codeStr);
            code.setEmail(email);
            code.setStatus("1");
            userMapper.saveCode(code);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    private static String getCodeString() {
        StringBuffer str = new StringBuffer();
        Random r = new Random();
        for(int i =0;i<6;i++) {
            int li = r.nextInt(9);
            str.append(li);
        }

        return str.toString();
    }
}
