package com.ratna.boot.batch.h2.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import com.ratna.boot.batch.h2.model.Users;

@Component
public class CsvParser implements ItemProcessor<Users, Users> {

	private static final Map<String, String> DEPT_NAMES = new HashMap<>();

	public CsvParser() {
		DEPT_NAMES.put("0001", "SOFTWARE");
		DEPT_NAMES.put("0002", "HOME");
		DEPT_NAMES.put("0003", "MEDICAL");
		DEPT_NAMES.put("0004", "ADMINISRATION");
	}

	@Override
	public Users process(Users item) throws Exception {
		item.setDept(DEPT_NAMES.get(item.getDept()));
		return item;
	}

}
