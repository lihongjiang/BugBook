package com.bslee.logtoolapk.model;

import java.io.Serializable;

import org.litepal.crud.DataSupport;

public class ExceptionBug extends DataSupport implements Serializable {

	public static final long serialVersionUID = 1558697194992714378L;

	private int id;

	private String appName;
	private String appPackage;

	private String fileName;
	private String className;
	private String methedName;
	private String lineNumber;
	private String message;
	private long createTime;
	private String messageInfoPath;
	private String phoneInfo;
	private String taskinfo;
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppPackage() {
		return appPackage;
	}

	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}

	public String getMessageInfoPath() {
		return messageInfoPath;
	}

	public void setMessageInfoPath(String messageInfoPath) {
		this.messageInfoPath = messageInfoPath;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public ExceptionBug() {
		super();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethedName() {
		return methedName;
	}

	public void setMethedName(String methedName) {
		this.methedName = methedName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getPhoneInfo() {
		return phoneInfo;
	}

	public void setPhoneInfo(String phoneInfo) {
		this.phoneInfo = phoneInfo;
	}

	public String getTaskinfo() {
		return taskinfo;
	}

	public void setTaskinfo(String taskinfo) {
		this.taskinfo = taskinfo;
	}

}