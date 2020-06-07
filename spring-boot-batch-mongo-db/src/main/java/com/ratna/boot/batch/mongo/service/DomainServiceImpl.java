package com.ratna.boot.batch.mongo.service;

import java.util.List;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import com.ratna.boot.batch.mongo.model.Domain;
import com.ratna.boot.batch.mongo.repository.DomainRepository;

@Service
public class DomainServiceImpl implements DomainService {

	@Autowired
	DomainRepository repository;

	@Autowired
	MongoTemplate template;

	@Autowired
	JobLauncher jobLauncher;

	// we have to specify the job name we mentioned in configuration of job
	@Autowired
	Job readCSVFile;

	@Override
	public List<Domain> getAllDomains() {
		return repository.findAll();
	}

	@Override
	public String triggerJob() {
		String msg = "Job Triggered Successfully";
		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.toJobParameters();
		try {
			jobLauncher.run(readCSVFile, jobParameters);
		} catch (JobExecutionAlreadyRunningException e) {
			msg = e.getMessage();
		} catch (JobRestartException e) {
			msg = e.getMessage();
		} catch (JobInstanceAlreadyCompleteException e) {
			msg = e.getMessage();
		} catch (JobParametersInvalidException e) {
			msg = e.getMessage();
		}
		return msg;
	}

	@Override
	public String dropCollection(String name) {
		String msg = "Collection Dropped";
		template.dropCollection(name);
		return msg;
	}

}
