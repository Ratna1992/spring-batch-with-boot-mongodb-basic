package com.ratna.boot.batch.h2.service;

import org.springframework.web.multipart.MultipartFile;

public interface UsersService {

	String createFile(String name);

	String triggerJob(MultipartFile name);
}
