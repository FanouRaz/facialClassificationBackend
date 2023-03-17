package com.fanou.facialrecognition.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fanou.facialrecognition.repository.UserRepository;
import com.fanou.facialrecognition.model.User;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String addUser(User user){
        userRepository.save(user);
        System.out.println("L'utilisateur "+user+" a été créé avec succès!");
        return "L'utilisateur "+user+" a été créé avec succès!";
    }

    public User getUser(String email,String password){
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        System.out.println(users);
        return users.stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findAny()
                .orElse(null);                                     
    }

    public User getUserByID(Integer id){
        return userRepository.findById(id).orElse(null);                          
    }
}
