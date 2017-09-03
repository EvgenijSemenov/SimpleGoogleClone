package app.controller;

import app.service.WebPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class IndexPageController {

    @Autowired
    WebPageServiceImpl webPageService;

    @RequestMapping(path = "/index", method=GET)
    public String indexPage() {
        return "index/index";
    }

    @RequestMapping(path = "/index", method=POST)
    public String indexingPage(@RequestParam(value="q") String url) {
        webPageService.indexByUrl(url, 2);

        return "index/indexResult";
    }

}
