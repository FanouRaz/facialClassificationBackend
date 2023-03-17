package com.fanou.facialrecognition.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="Image")
public class UploadedImage {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer image_id;
    private String path;
    private String label;    

    @ManyToOne
    @JoinColumn(name="id",nullable=false)
    private User user;

    public UploadedImage(String path,String label, User user){
        this.path = path;
        this.label = label;
        this.user = user;
    }  
}
