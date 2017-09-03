package app.controller;

import app.service.WebPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class SearchController {

    @Autowired
    WebPageService webPageService;

    @RequestMapping(path = "/", method=GET)
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/search", method=GET)
    public ModelAndView search(@RequestParam(value="q") String searchText) {
        ModelAndView model = new ModelAndView("search");
        model.addObject("searchResultList", webPageService.fulltextSearch(searchText));

        return model;
    }

}
