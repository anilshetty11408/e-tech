package com.engg.madel;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;

	@Column
	private String departmentName;

	@Column
	private Double serialNo;

	@Column
	private String topics;

	@Column
	private String platform;

	@Column
	private String link;

	@Column
	private double start;

	@Column
	private double end;

	@Column
	private String subject;

	@Column
	private String lastUpdatedBy;

	@Column
	private LocalDateTime lastUpdatedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Double getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Double serialNo) {
		this.serialNo = serialNo;
	}

	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public double getStart() {
		return start;
	}

	public void setStart(double start) {
		this.start = start;
	}

	public double getEnd() {
		return end;
	}

	public void setEnd(double end) {
		this.end = end;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public LocalDateTime getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(LocalDateTime lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column
	private String year;

	@Column
	private int orderForDisplay;

	@Column
	private String courseName;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getOrderForDisplay() {
		return orderForDisplay;
	}

	public void setOrderForDisplay(int orderForDisplay) {
		this.orderForDisplay = orderForDisplay;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
}