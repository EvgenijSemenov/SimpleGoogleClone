package app.controller;

import app.service.WebPageService;
import app.sql.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

    @Autowired
    WebPageService webPageService;

    @RequestMapping(path = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/search")
    public ModelAndView search(@RequestParam(value="q") String searchText,
                               @RequestParam(value="start", required = false, defaultValue = "1") int startResultNumber) {
        ModelAndView model = new ModelAndView("search");
        SearchResult searchResult = webPageService.fulltextSearch(searchText, startResultNumber);

        model.addObject("searchText", searchResult.getSearchText());
        model.addObject("searchResultList", searchResult.getWebPageList());
        model.addObject("searchResultCount", searchResult.getCount());

        return model;
    }

}
