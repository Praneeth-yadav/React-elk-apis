package com.ep.ecom_api.repository;

import com.ep.ecom_api.entity.LoginDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginDetailsRepo extends MongoRepository<LoginDetails, String> {



    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<LoginDetails> findAll(String category);


    Optional<LoginDetails> findByEmail(String email);
}
