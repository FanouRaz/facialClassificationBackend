package com.fanou.facialrecognition.repository;

import org.springframework.data.repository.CrudRepository;

import com.fanou.facialrecognition.model.UploadedImage;

public interface UploadedImageRepository extends CrudRepository<UploadedImage,Integer>{}
