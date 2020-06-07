package com.ratna.boot.batch.mongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratna.boot.batch.mongo.model.Domain;
import com.ratna.boot.batch.mongo.repository.DomainRepository;

@Service
public class DomainServiceImpl implements DomainService {

	@Autowired
	DomainRepository repository;

	@Override
	public List<Domain> getAllDomains() {
		return repository.findAll();
	}

}
