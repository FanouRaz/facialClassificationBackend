package com.fanou.facialrecognition.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.fanou.facialrecognition.service.FileStorageService;
import com.fanou.facialrecognition.service.ImagePredictionService;
import com.fanou.facialrecognition.service.UploadedImageService;
import com.fanou.facialrecognition.message.FileResponseMessage;
import com.fanou.facialrecognition.model.UploadedImage;

@RestController
@CrossOrigin("http://localhost:*")
public class FilesController {
    @Autowired
    private FileStorageService storageService;
    @Autowired
    private UploadedImageService uploadedImageService;

    @PostMapping("/uploadImg/{user_id}")
    public ResponseEntity<FileResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,@PathVariable Integer user_id){
        String message ="";
        try{
            storageService.save(file,user_id);
            message = "Uploaded the file successfully: "+file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new FileResponseMessage(message));
        }catch(Exception e){
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<UploadedImage>> getListFiles(){
        List<UploadedImage> uploadedImages =  uploadedImageService.getImages();   
        return ResponseEntity.status(HttpStatus.OK).body(uploadedImages);
    } 

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
        .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
