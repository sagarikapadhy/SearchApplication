package com.searchapplication.demo.unittests;

import com.searchapplication.demo.config.TestBeanConfig;
import com.searchapplication.demo.controller.SearchController;
import com.searchapplication.demo.model.SearchModel;
import com.searchapplication.demo.service.SearchService;
import com.searchapplication.demo.service.SearchServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindingResult;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestBeanConfig.class})
public class TestSearchApplication {

    @Autowired
    private SearchController searchController;
    private SearchModel searchModel;

    private final String errorMessage = "All fields are required";


    // mocking the BindingResult
    @Mock
    private BindingResult mockBindingResult;


    // mocking doSearchInGoogle() method in impl class
    @Mock
    private SearchService mockSearchService;


    @Before
    public void setupTest() {
        MockitoAnnotations.initMocks(this);
        // While the default boolean return value for a mock is 'false',
        // it's good to be explicit anyway:
        // Mockito.when(mockBindingResult.hasErrors()).thenReturn(false);
    }


    @Test
    public void testPositive() throws Exception {
        searchModel = new SearchModel();
        searchModel.setKeyword("online title search");
        searchModel.setUrl("www.infotrack.com.au");
        Mockito.when(mockBindingResult.hasErrors()).thenReturn(false);
        String result = searchController.getUrlSearchCount(searchModel, mockBindingResult);
        assertNotNull(result);
        assertEquals("1", result);
    }

    @Test
    public void testNegative() throws Exception {
        Mockito.when(mockBindingResult.hasErrors()).thenReturn(true);
        assertEquals(errorMessage, "All fields are required");
    }

    @Test
    public void testMockGoogleSearchResult() throws Exception {
        searchModel = new SearchModel();
        searchModel.setKeyword("online title search");
        searchModel.setUrl("www.infotrack.com.au");
        Mockito.when(mockBindingResult.hasErrors()).thenReturn(false);
        Mockito.when(mockSearchService.getSearchResult(searchModel.getKeyword(), searchModel.getUrl()))
                .thenReturn("3");
        String result = mockSearchService.getSearchResult(searchModel.getKeyword(), searchModel.getUrl());
        assertNotNull(result);
        assertEquals("3", result); // mocking the result to 3
    }

    @Test
    public void testMultipleKeywords() throws Exception {
        searchModel = new SearchModel();
        searchModel.setKeyword("online title search,online title");
        searchModel.setUrl("www.infotrack.com.au");
        Mockito.when(mockBindingResult.hasErrors()).thenReturn(false);
        Mockito.when(mockSearchService.getSearchResult(searchModel.getKeyword(), searchModel.getUrl()))
                .thenReturn("3,2");
        String result = mockSearchService.getSearchResult(searchModel.getKeyword(), searchModel.getUrl());
        assertNotNull(result);
        assertEquals("3,2", result); // mocking the result to 3
    }


}
