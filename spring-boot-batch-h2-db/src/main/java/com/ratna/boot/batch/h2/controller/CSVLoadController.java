package com.ratna.boot.batch.h2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ratna.boot.batch.h2.service.UsersService;

@RestController
public class CSVLoadController {
	@Autowired
	UsersService userService;

	@PostMapping("/triggerJob")
	public String triggerJob(@RequestParam("file") MultipartFile file) {
		return userService.triggerJob(file);
	}

	@GetMapping("/createFile/{name}")
	public String createFile(@PathVariable String name) {
		userService.createFile(name);
		return "File Created";
	}
}
