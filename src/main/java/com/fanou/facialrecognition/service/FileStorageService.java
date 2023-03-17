package com.fanou.facialrecognition.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fanou.facialrecognition.model.UploadedImage;

@Service
public class FileStorageService{
    @Autowired
    private ImagePredictionService predict;

    @Autowired
    private UploadedImageService uploadedImageService;

    private final Path root = Paths.get("uploads");

    public void init(){
        try{
            Files.createDirectories(root);
        }catch(IOException e){
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public void save(MultipartFile file,Integer user_id){
        try{
            Files.copy(file.getInputStream(),this.root.resolve(file.getOriginalFilename()));
            UploadedImage img = predict.labelPredict(this.root.resolve(file.getOriginalFilename()).toString(),user_id);
            uploadedImageService.addImages(img);
        }catch(Exception e){
            if(e instanceof FileAlreadyExistsException) throw new RuntimeException("A file of that name already exists.");
            
            throw new RuntimeException(e.getMessage());
        }
    }

    public Resource load(String filename){
        try{
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) return resource;
            
            else  throw new RuntimeException("Could not read the file!");

            } catch (MalformedURLException e) {
              throw new RuntimeException("Error: " + e.getMessage());
            }
    }


    public Stream<Path> loadAll() {
        try {
        return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
        throw new RuntimeException("Could not load the files!");
        }
    }
}
