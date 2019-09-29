package com.qf.controller;

import com.qf.domain.User;
import com.qf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
      @RequestMapping("/register")
      @ResponseBody
      public String register(@RequestBody User user){
        if(userService.register(user,user.getCode())) {
            return "1";
        }
        return "0";
      }
    @RequestMapping("/getCode")
    @ResponseBody
    public String getCode(@RequestParam("email") String email){
          if(email==null&&email.equals("")){
              return "{\"code\":404}";
          }
        String msg="";
       if( userService.getCode(email)){
          msg ="{\"code\":1}";
       }else{
           msg ="{\"code\":0}";
       }
        return msg;
    }
    @RequestMapping("/selectAll")
    @ResponseBody
    public List<User> selectAll() {
        List<User> list = userService.selectAll();
        return list;
    }
    @RequiresPermissions(value={"user_add"})
    @RequestMapping("/add")
    public String add(Model model) {
        List<User> list = userService.selectAll();
        model.addAttribute("list", list);
        return "1";
    }
    @RequiresPermissions(value={"user_update"})
    @RequestMapping("/addOne")
    public String save( @RequestBody User user) {

        userService.Uplaod(user);
        return "1";
    }
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "1";
    }
    @RequestMapping("/update")
    @ResponseBody
    public User up(@RequestParam("id") int id) {
        User user =userService.selectOne(id);
        return user;
    }
    @RequestMapping("/updateOne")
    public String updateOne( @RequestBody User user) {
        userService.update(user);
        return "1";
    }
    @RequestMapping("/login")
    @ResponseBody
    public String login( @RequestParam("name") String username,@RequestParam("pass")String password) {
        //userService.update(user);
     Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(username,password);
        try{
            subject.login(token);
            if (subject.isAuthenticated()){
                //session存储
                //redis存储

                return "1";
            }else {
                return "0";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "0";
    }
}
