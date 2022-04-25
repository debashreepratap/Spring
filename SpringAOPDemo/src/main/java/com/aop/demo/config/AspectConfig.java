package com.aop.demo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ResponseStatusException;

import com.aop.demo.exception.TaskException;

@Configuration
@Aspect
public class AspectConfig {

	private Logger log = LoggerFactory.getLogger(AspectConfig.class);

	@Before("allServiceLoggrs()")
	public void logStatementBefore(JoinPoint joinPoint) {
		log.info("Executing {}", joinPoint);
	}

	@After(value = "execution(* com.aop.demo.controller.*.*(..))")
	public void logStatementAfter(JoinPoint joinPoint) {
		log.info("Completing Executing {}", joinPoint);
	}

	@Around(value = "execution(* com.aop.demo.service.*.*(..))")
	public Object taskHandler(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			Object obj = joinPoint.proceed();
			return obj;
		} catch (TaskException e) {
			log.info("TaskException Status Code {}", e.getStatusCode());
			log.info("TaskException Message {}", e.getMessage());
			throw new ResponseStatusException(e.getStatusCode(), e.getMessage());
		}
	}

	@Around(value = "execution(* com.aop.demo.controller.*.*(..))")
	public Object timeTracker(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		try {
			Object obj = joinPoint.proceed();
			long timeTaken=System.currentTimeMillis()-startTime;
			log.info("Time taken by {}",joinPoint,timeTaken);
			return obj;
		} catch (TaskException e) {
			log.info("TaskException Status Code {}", e.getStatusCode());
			log.info("TaskException Message {}", e.getMessage());
			throw new ResponseStatusException(e.getStatusCode(), e.getMessage());
		}
	}
	
	@Pointcut("within(com.aop.demo.service..*)")
	public void allServiceLoggrs() {
	}
	
	/*
	 * @Before("args(name)") public void methodWithNoarg(String name) {
	 * log.info("Executing methodWithNoarg"+name); }
	 */
}
