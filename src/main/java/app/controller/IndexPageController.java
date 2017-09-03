package app.controller;

import app.service.WebPageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class IndexPageController {

    @Autowired
    WebPageServiceImpl webPageService;

    @RequestMapping(path = "/index", method=GET)
    public String indexPage() {
        return "index";
    }

}
