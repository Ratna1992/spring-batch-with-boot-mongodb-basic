package com.ratna.boot.batch.h2.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ratna.boot.batch.h2.model.Users;
import com.ratna.boot.batch.h2.repository.UsersRepository;

@Component
public class CSVWriter implements ItemWriter<Users> {

	@Autowired
	UsersRepository usersRepository;

	@Override
	public void write(List<? extends Users> items) throws Exception {
		System.out.println("Data Saved for Users:" + items);
		usersRepository.saveAll(items);
	}

}
