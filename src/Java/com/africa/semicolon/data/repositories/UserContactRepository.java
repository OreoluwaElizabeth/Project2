package com.africa.semicolon.data.repositories;

import com.africa.semicolon.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserContactRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
