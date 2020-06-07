package com.ratna.boot.batch.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ratna.boot.batch.mongo.model.Domain;
@Repository
public interface DomainRepository extends MongoRepository<Domain, Integer>{

}
