package com.aop.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Task {
	@Id
	private Integer id;
	private String taskName;
	private String taskType;
	private Date assignDate;
	
	public Task() {
	}
	public Task(Integer id, String taskName, String taskType, Date assignDate) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.taskType = taskType;
		this.assignDate = assignDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public Date getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + ", taskType=" + taskType + ", assignDate=" + assignDate
				+ "]";
	}
	
	

}
