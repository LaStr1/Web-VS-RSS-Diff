package cz.lastr.WebVsRssDiff.Controller;

import cz.lastr.WebVsRssDiff.Model.WebArticle;
import cz.lastr.WebVsRssDiff.Getter.RssGetAndSave;
import cz.lastr.WebVsRssDiff.Service.WebArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    private final RssGetAndSave rssGetAndSave;
    private final WebArticleService webArticleService;

    public IndexController(RssGetAndSave rssGetAndSave, WebArticleService webArticleService) {
        this.rssGetAndSave = rssGetAndSave;
        this.webArticleService = webArticleService;
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        rssGetAndSave.getDataFromRssAndSave();

        List<WebArticle> differentArticles = webArticleService.getDiffBetweenWebAndRss();
        model.addAttribute("articles", differentArticles);
        return "index";
    }
}
