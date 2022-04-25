package com.example.demo.model.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport{

	
	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
	
	private final JdbcTemplate jdbcTemplate;

	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		
		if(jobExecution.getStatus()==BatchStatus.COMPLETED) {
			log.info("!!! Job Finished..Time to verify the result");
			
			jdbcTemplate.query("select batting_team ,bowling_team from match",
					(rs,row)->"Team1 :"+rs.getString(1)+"Team2 :"+ rs.getString(2)).forEach(str->System.out.println(str));
		}
		
	}
}
