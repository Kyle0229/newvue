package com.qf.mapper;

import com.qf.domain.Code;
import com.qf.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {


    public  int saveCode(Code code);
    public  String selectCodeByEmail(@Param("name") String name);
    public List<User> selectAll();
    public int save(User user);
    public int delete(int id);
    public User selectOne(int id);
    public int update(User user);

    String selectPsswordByName(@Param("username") String username);
}
