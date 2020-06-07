package com.ratna.boot.batch.h2.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;

import com.ratna.boot.batch.h2.model.Users;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemReader<Users> reader,
			ItemProcessor<Users, Users> processor, ItemWriter<Users> writer) {
		// generating step
		Step step = stepBuilderFactory.get("Load-CSV-FILE").<Users, Users>chunk(100).reader(reader).processor(processor)
				.writer(writer).build();
		// building job
		return jobBuilderFactory.get("Load-CSV").incrementer(new RunIdIncrementer()).start(step).build();
	}

	// creating reader
	@Bean
	@StepScope
	public FlatFileItemReader<Users> fileReader(@Value("#{jobParameters[externalFile]}") String pathToFile) {
		FlatFileItemReader<Users> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new FileSystemResource(pathToFile));
		flatFileItemReader.setName("CSV_FILE_READER");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}

	// creating line mapper for csv
	@Bean
	@StepScope
	public LineMapper<Users> lineMapper() {
		// default line mapper
		DefaultLineMapper<Users> defaultLineMapper = new DefaultLineMapper<>();

		// reading header
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames(new String[] { "id", "name", "dept", "salary" });

		// matching fields
		BeanWrapperFieldSetMapper<Users> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Users.class);

		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		return defaultLineMapper;
	}

}
