package app.controller;

import app.service.WebPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class IndexPageController {

    @Autowired
    WebPageService webPageService;

    @RequestMapping(path = "/index", method=GET)
    public String indexPage() {
        return "index/index";
    }

    @RequestMapping(path = "/index", method=POST)
    public ModelAndView indexingPage(@RequestParam(value="q") String url,
                                     @RequestParam(value="searchUrlDeep", required = false, defaultValue = "3") int searchUrlDeep) {
        ModelAndView model = new ModelAndView("index/indexResult");

        String indexMessage = webPageService.indexByUrl(url, searchUrlDeep);
        model.addObject("indexMessage", indexMessage);

        return model;
    }

}
