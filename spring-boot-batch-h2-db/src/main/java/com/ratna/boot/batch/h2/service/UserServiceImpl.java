package com.ratna.boot.batch.h2.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UsersService {

	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job job;
	@Autowired
	ResourceLoader resourceLoader;

	@Override
	public String createFile(String name) {
		File f = new File("test.csv");
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return f.getAbsolutePath();

	}

	@Override
	public String triggerJob(MultipartFile file) {
		String msg = "Job Triggered Successfully";
		JobExecution run = null;
		BatchStatus status = null;
		try {
			// creating file in project location
			File fileToImport = new File(file.getOriginalFilename());
			fileToImport.createNewFile();

			// copying data from multi-part file to output file
			OutputStream outputStream = new FileOutputStream(fileToImport);
			IOUtils.copy(file.getInputStream(), outputStream);
			outputStream.flush();
			outputStream.close();

			// job parameters
			JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
			jobParametersBuilder.addString("externalFile", fileToImport.getAbsolutePath());

			// launch the job
			run = jobLauncher.run(job, jobParametersBuilder.toJobParameters());
			status = run.getStatus();
			while (run != null && run.isRunning()) {
				System.out.println("Job is Still Running");
			}
		} catch (Throwable e) {
			msg = e.getMessage();
		}

		return msg + status;

	}

}
