package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.demo.batch.MatchDataProcessor;
import com.example.demo.model.Match;
import com.example.demo.model.MatchInput;
import com.example.demo.model.listener.JobCompletionNotificationListener;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	private final String[] FIELD_NAMES= new String[]{"matchId", "id", "inning", "over", "ball", "batsman", "non_striker", "bowler", "batsman_runs", "extra_runs", "total_runs","non_boundary",	"is_wicket", "dismissal_kind", "player_dismissed", "fielder", "extras_type", "batting_team", "bowling_team"};

	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public FlatFileItemReader<MatchInput> reader(){
		return new FlatFileItemReaderBuilder<MatchInput>().name("MatchItemReader")
				  .resource(new ClassPathResource("match-data1.csv")).delimited()
				  .names(FIELD_NAMES)
				  .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInput>(){
					  {
						  setTargetType(MatchInput.class);
					  }
			
		}).build();
				  
	}
	@Bean
	public MatchDataProcessor processor() {
		return new MatchDataProcessor();
	}
	
	@Bean 
	public JdbcBatchItemWriter<Match> writer(DataSource datasource){
		return new JdbcBatchItemWriterBuilder<Match>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("Insert into match (match_id, id,   inning,  over,  ball, batsman,  non_striker, bowler,  batsman_runs, total_runs, is_wicket, fielder, batting_team, bowling_team) "
					      + " values (:matchId, :id, :inning, :over, :ball, :batsman, :nonStriker, :bowler, :batsmanRuns, :totalRuns, :isWicket, :fielder, :battingTeam, :bowlingTeam); ")
			
				.dataSource(datasource).build();
				
		
	}
	
	@Bean
	public Job importUserJob (JobCompletionNotificationListener listener,Step step1) {
		return jobBuilderFactory
				.get("importUserJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1)
				.end()
				.build();
	}
	
	@Bean
	public Step step1(JdbcBatchItemWriter<Match> writer) {
		return stepBuilderFactory
				.get("step1")
				.<MatchInput,Match>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer)
				.build();
		
	}
}
