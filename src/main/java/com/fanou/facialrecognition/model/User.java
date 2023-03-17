package com.fanou.facialrecognition.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String email;
    private String username;
    private String password;

    @OneToMany(mappedBy="user")
    private Set<UploadedImage> images;

    public User(String username,String email, String password){
        this.username = username;
        this.password = username;
        this.email = email;
    }

    @Override
    public String toString(){
        return String.format("{\"id\":%d,\"username\":%s , \"email\":%s, \"Uploaded\":%d }",id,username,email,images.size());
    }
}
