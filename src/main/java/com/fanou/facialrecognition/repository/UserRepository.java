package com.fanou.facialrecognition.repository;

import org.springframework.data.repository.CrudRepository;
import com.fanou.facialrecognition.model.User;

public interface UserRepository extends CrudRepository <User,Integer>{}
