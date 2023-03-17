package com.fanou.facialrecognition;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fanou.facialrecognition.service.FileStorageService;

import jakarta.annotation.Resource;

@SpringBootApplication
public class FacialrecognitionApplication implements CommandLineRunner {
	@Resource
	FileStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(FacialrecognitionApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception{
		storageService.init();
	}
}
