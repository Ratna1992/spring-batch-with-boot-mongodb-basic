package com.ratna.boot.batch.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratna.boot.batch.mongo.model.Domain;
import com.ratna.boot.batch.mongo.service.DomainService;

@RestController
public class DomainController {

	@Autowired
	DomainService domainService;

	@GetMapping("/launchData")
	public List<Domain> getAllDomains() {
		return domainService.getAllDomains();
	}

}
