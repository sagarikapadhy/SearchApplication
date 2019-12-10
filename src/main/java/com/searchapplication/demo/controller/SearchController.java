package com.searchapplication.demo.controller;

import com.searchapplication.demo.model.SearchModel;
import com.searchapplication.demo.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.validation.Valid;

@Controller
public class SearchController {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndexPage(Model model) {
        SearchModel searchModel = new SearchModel();
        model.addAttribute("searchModel", searchModel);
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public String getUrlSearchCount(@ModelAttribute("searchModel") @Valid SearchModel searchModel,
                                   BindingResult result) throws Exception {

        logger.info("Search Model" + searchModel);

        if (result.hasErrors()) {
            return "All fields are required";
        } else {
            return searchService.getSearchResult(searchModel.getKeyword(), searchModel.getUrl());
        }

    }

}
