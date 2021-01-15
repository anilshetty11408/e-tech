package com.engg.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.engg.madel.Subject;
import com.engg.repository.UploadRepository;
import com.engg.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	private UploadRepository repository;

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Id", "Title", "Description", "Published" };
	static String SHEET = "Departments";

	@Override
	public void uploadService(MultipartFile file, String year, int orderForDisplay, String courseName) {

		try {
			repository.deleteDataIfAvailable(year, orderForDisplay, courseName);

			List<Subject> dept = excelTodepartments(file.getInputStream(), year, orderForDisplay, courseName);

			repository.saveAll(dept);
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}

	}

	private List<Subject> excelTodepartments(InputStream inputStream, String year, int displayOrder,
			String courseName) {
		List<Subject> deptList = new LinkedList<>();
		int k = 0;
		try {
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
			for (int j = 2; j < sheet.getPhysicalNumberOfRows() - 1; j++) {
				Subject dept = new Subject();
				dept.setYear(year);
				dept.setOrderForDisplay(displayOrder);
				dept.setCourseName(courseName);

				row = sheet.getRow(j);
				k = 0;

//				dept.setDepartmentName(wb.getSheetAt(0).getSheetName());

				if (org.apache.commons.lang3.StringUtils.isNotBlank(row.getCell((short) k).getStringCellValue())) {
					System.out.println("1 " + row.getCell((short) k).getStringCellValue());
					dept.setSubject(row.getCell((short) k).getStringCellValue());

				}
				k++;
				if (org.apache.commons.lang3.StringUtils.isNotBlank(row.getCell((short) k).getStringCellValue())) {
					System.out.println("2 " + row.getCell((short) k).getStringCellValue());
					dept.setTopics(row.getCell((short) k).getStringCellValue());
				}
				k = 7;

				if (org.apache.commons.lang3.StringUtils.isNotBlank(row.getCell((short) k).getStringCellValue())) {
//					CellValue cellValue = evaluator.evaluate(row.getCell((short) k));

					System.out.println("3 " + evaluator.evaluate(row.getCell((short) k)).getStringValue());
					dept.setLink(evaluator.evaluate(row.getCell((short) k)).getStringValue());

				}

				if (org.apache.commons.lang3.StringUtils.isNotEmpty(dept.getSubject())) {
					dept.setLastUpdatedBy("anil");
					dept.setLastUpdatedOn(LocalDateTime.now());
					System.out.println("<><><><><><><>><><><><><><><><><><><>");
					deptList.add(dept);
				}

			}

			wb.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return deptList;
	}

	@Override
	public List<Subject> getDepartmentName() {

		return repository.findAll();
	}

	@Override
	public List<Subject> retriveSubjectExcelData(String courseName, String year) {

		return repository.retriveSubjectExcelData(courseName, year);
	}

	@Override
	public Optional<Subject> getDepartmentById(Long id) {

		return repository.findById(id);
	}

	@Override
	public List<String> getFirstYearCourseName(String year) {
		return repository.getFirstYearCourseName(year);
	}

	@Override
	public List<String> getOtherYearCourseName(String year) {
		System.out.println(year);
		// TODO Auto-generated method stub
		return repository.getOtherYearCourseName(year);
	}

}
