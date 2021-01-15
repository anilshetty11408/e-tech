package com.engg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.engg.madel.Subject;

public interface UploadService {
	public void uploadService(MultipartFile file, String year, int displayOrder, String courseName);

	public List<Subject> getDepartmentName();

	public List<Subject> retriveSubjectExcelData(String courseName, String year);

	public Optional<Subject> getDepartmentById(Long id);

	public List<String> getFirstYearCourseName(String string);

	public List<String> getOtherYearCourseName(String string);

}
