package com.ratna.boot.batch.itemwriter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class BatchItemWriter implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> items) throws Exception {
		for (String string : items) {
			System.out.println("Writing the data " + string);
		}

	}

}
