package com.engg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.engg.madel.Subject;
import com.engg.service.UploadService;

@Controller
public class RedirectController {

	@Autowired
	private UploadService service;

	@GetMapping("/")
	public String home(ModelAndView model) {
		return "home";
	}

	@GetMapping("/aboutUs")
	public String aboutUs() {
		return "aboutUs";
	}

	@GetMapping("/signIn")
	public String signIn() {
		return "signIn";
	}

	@GetMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("subject", new Subject());
		return "admin";
	}

	@GetMapping("/civilEngineering")
	public String civilDepartment(Model model) {
		List<String> enggList = service.getOtherYearCourseName("1");

		Map<Integer, List<String>> map = new TreeMap<>();
		List<String> list = new ArrayList<>();
//		enggList.stream().map(s -> s.split(",")[0]).forEach(map.put(s, value));

		for (String string : enggList) {
			if (map.containsKey(Integer.valueOf(string.split(",")[0]))) {
				list = map.get(Integer.valueOf(string.split(",")[0]));
				list.add(string.split(",")[1]);
				map.put(Integer.valueOf(string.split(",")[0]), list);
			} else {
				list = new ArrayList<>();
				list.add(string.split(",")[1]);
				map.put(Integer.valueOf(string.split(",")[0]), list);
			}
		}

		model.addAttribute("courseList", map);

		return "civilEngg";
	}

	@GetMapping("/header")
	public String header() {
		return "header";
	}

	@GetMapping("/video")
	public String getVideo() {
		return "video";
	}

	@GetMapping("/firstYear")
	public String firstYear(Model model) {
		List<String> firstYearList = service.getFirstYearCourseName("1");
		model.addAttribute("courseList", firstYearList);
		return "firstYear";
	}
}
