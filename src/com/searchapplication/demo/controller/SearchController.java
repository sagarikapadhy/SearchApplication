package com.searchapplication.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.searchapplication.demo.model.SearchModel;
import com.searchapplication.demo.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		SearchModel searchModel = new SearchModel();
		model.addAttribute("searchModel", searchModel);
		return "index";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdateUser(@ModelAttribute("searchModel") SearchModel searchModel,
			BindingResult result, Model model) {
		System.out.println(searchModel);
		String searchCount = searchService.getSearchResult(searchModel.getKeyword(), searchModel.getUrl());
		return 	searchCount;	
	}

}
