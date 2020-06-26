package cz.lastr.WebVsRssDiff.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping
    public String Index(Model model){

        model.addAttribute("articles", "contents of articles");

        return "index";
    }
}
