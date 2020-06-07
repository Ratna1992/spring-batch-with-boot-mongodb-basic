package com.ratna.boot.batch.mongo.batchconfig;

import org.springframework.batch.item.ItemProcessor;

import com.ratna.boot.batch.mongo.model.Domain;

public class BatchCsvParser implements ItemProcessor<Domain, Domain> {

	@Override
	public Domain process(Domain item) throws Exception {
		if (item.getName().equalsIgnoreCase("NA")) {
			item.setName("Yet To Update");
		}
		return item;
	}

}
