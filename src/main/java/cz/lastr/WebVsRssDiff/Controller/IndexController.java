package cz.lastr.WebVsRssDiff.Controller;

import org.springframework.ui.Model;

public class IndexController {

    public String Index(Model model){

        model.addAttribute("articles", "article");

        return "index";
    }
}
