package com.fanou.facialrecognition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fanou.facialrecognition.model.UploadedImage;
import com.fanou.facialrecognition.service.UploadedImageService;

@RestController
public class UploadedImageController {
    @Autowired
    private UploadedImageService imageService;

    @PostMapping("/upload")
    @ResponseBody
    public String addImage(@RequestBody UploadedImage image){
        return imageService.addImages(image);
    }
}
