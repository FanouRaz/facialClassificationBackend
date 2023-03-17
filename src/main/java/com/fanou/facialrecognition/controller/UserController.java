package com.fanou.facialrecognition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fanou.facialrecognition.service.UserService;
import com.fanou.facialrecognition.model.User;


@RestController
public class UserController {
    @Autowired
    private UserService userService;    
    
    @PostMapping(path="/addUser")
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping(path="/user")
    @ResponseBody
    public User getUser(@RequestParam String email,@RequestParam String password){
        System.out.println("email:"+email+"\npassword:"+password);
        return userService.getUser(email,password);
    }
}
