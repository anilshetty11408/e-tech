package com.engg.restcontroller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.engg.madel.AjaxModel;
import com.engg.madel.Subject;
import com.engg.service.UploadService;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

	@Autowired
	private UploadService service;

	@PostMapping(value = "/retriveSubjectExcelData", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, List<Subject>> retriveSubjectExcelData(@RequestBody AjaxModel ajax) {
		List<Subject> list = new ArrayList<>();
		list = service.retriveSubjectExcelData(ajax.getCourseName(), ajax.getYear());
		Map<String, List<Subject>> map = new LinkedHashMap<>();
		Subject dept = new Subject();
		List<Subject> newList = new ArrayList<>();
		for (Subject departments : list) {
			dept = new Subject();
			newList = new ArrayList<>();
			dept.setId(departments.getId());
			dept.setTopics(departments.getTopics());
			dept.setLink(departments.getLink());
			newList.add(dept);
			if (map.containsKey(departments.getSubject())) {
				newList.addAll(map.get(departments.getSubject()));
				newList.sort(Comparator.comparing(Subject::getId));
				map.put(departments.getSubject(), newList);
			} else {
				newList.sort(Comparator.comparing(Subject::getId));
				map.put(departments.getSubject(), newList);
			}
		}
		return map;
	}

	@GetMapping("/retriveSubjectExcelData/{id}")
	public Optional<Subject> getDepartmentById(@PathVariable("id") long id) {

		return Optional.ofNullable(service.getDepartmentById(id).orElse(null));

	}

	@PostMapping("/checkAjax")
	public String checkAjax(@RequestBody String firstName) {
		return "success";
	}
}
