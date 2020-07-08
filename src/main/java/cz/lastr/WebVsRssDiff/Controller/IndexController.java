package cz.lastr.WebVsRssDiff.Controller;

import cz.lastr.WebVsRssDiff.Model.ArticleFromWeb;
import cz.lastr.WebVsRssDiff.Service.WebGetter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class IndexController {
    WebGetter webGetter;

    private List<ArticleFromWeb> ArticlesFromFeed;

    public IndexController(WebGetter webGetter){
        this.webGetter = webGetter;
    }

    @GetMapping
    public String index(Model model) {

        return "index";
    }

    @GetMapping(value = "/{date}")
    public String fromDate(@PathVariable String date, Model model){
        model.addAttribute("date" , date);

        return "index";
    }
}
