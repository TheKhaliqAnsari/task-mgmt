package com.taskStore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class taskList {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int taskId;
	private String task;
	private String taskDetail;
	private String status;
	private Long employeeId;
	public taskList(int taskId, String task, String taskDetail, String status) {
		super();
		this.taskId = taskId;
		this.task = task;
		this.taskDetail = taskDetail;
		this.status = status;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public taskList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getTaskDetail() {
		return taskDetail;
	}
	public void setTaskDetail(String taskDetail) {
		this.taskDetail = taskDetail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
	
}
