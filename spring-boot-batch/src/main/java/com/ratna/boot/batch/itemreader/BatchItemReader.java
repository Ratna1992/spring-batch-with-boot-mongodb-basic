package com.ratna.boot.batch.itemreader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class BatchItemReader implements ItemReader<String> {

	private String[] msgs = { "Welcome to Spring Batch", "Hello World!" };

	private int count = 0;

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (count < msgs.length) {
			return msgs[count++];
		} else {
			count = 0;
		}
		return null;
	}

}
