package com.qf.service.impl;

import com.qf.domain.User;
import com.qf.mapper.UserMapper;
import com.qf.service.UserService;
import com.qf.utils.EmailUtils;
import com.qf.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    public UserMapper userMapper;
    @Autowired
    private EmailUtils emailUtils;
    @Override
    public boolean getCode(String email) {

        if(emailUtils.getCode(email)>0){
            return true;
        }

        return false;
    }

    @Override
    public boolean register(User user, String code) {
        String codeStr =  userMapper.selectCodeByEmail(user.getEmail());
        int flag = 0;
        System.out.println(codeStr);
        if(codeStr.equals(code)){
            user.setPass(Md5Utils.encryptPassword(user.getPass(),"likun".toString()));
          flag =  userMapper.save(user);
        }
        if(flag==1){
            return true;
        }

        return false;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
    @Override
    public User selectOne(int id) {
        return userMapper.selectOne(id);
    }
    @Override
    public int Uplaod(User user) {
        user.setPass(Md5Utils.encryptPassword(user.getPass(),"likun".toString()));
        return userMapper.save(user);
    }

    @Override
    public int delete(int id) {
        return userMapper.delete(id);
    }

    @Override
    public int update(User user) {
        user.setPass(Md5Utils.encryptPassword(user.getPass(),"likun"));
        return userMapper.update(user);
    }
}
