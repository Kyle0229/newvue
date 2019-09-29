package com.qf.service;

import com.qf.domain.User;

import java.util.List;

public interface UserService {
    boolean getCode(String email);

    boolean register(User user, String code);
    public List<User> selectAll();
    public User selectOne(int id);
    public int Uplaod(User user);
    public int delete(int id);
    public int update(User user);
}
