package com.fanou.facialrecognition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanou.facialrecognition.model.UploadedImage;
import com.fanou.facialrecognition.repository.UploadedImageRepository;

@Service
public class UploadedImageService {
    @Autowired
    private UploadedImageRepository imageRepository;

    public String addImages(UploadedImage image){
        imageRepository.save(image);
        System.out.println("L'image uploader par "+image.getUser().getUsername()+" se trouvant à l'emplacement "+image.getPath()+"  a été enregistré!");
        return "L'image uploader par "+image.getUser().getUsername()+" se trouvant à l'emplacement "+image.getPath()+" a été enregistré!";
    }

    public List<UploadedImage> getImages(){
        return (List<UploadedImage>)imageRepository.findAll();
    }

}
