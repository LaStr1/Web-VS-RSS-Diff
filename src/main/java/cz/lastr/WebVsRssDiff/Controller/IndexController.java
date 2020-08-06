package cz.lastr.WebVsRssDiff.Controller;

import cz.lastr.WebVsRssDiff.Model.WebArticle;
import cz.lastr.WebVsRssDiff.Service.Getter.RssGetAndSave;
import cz.lastr.WebVsRssDiff.Service.Getter.WebGetAndSave;
import cz.lastr.WebVsRssDiff.Service.WebArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

@Controller
public class IndexController {
    private RssGetAndSave rssGetAndSave;
    private WebGetAndSave webGetAndSave;
    private WebArticleService webArticleService;

    public IndexController(RssGetAndSave rssGetAndSave, WebGetAndSave webGetAndSave, WebArticleService webArticleService) {
        this.rssGetAndSave = rssGetAndSave;
        this.webGetAndSave = webGetAndSave;
        this.webArticleService = webArticleService;
    }

    @GetMapping(value = "index")
    public String index(Model model) {
        rssGetAndSave.getDataFromRssAndSave();

        List<WebArticle> differentArticles = webArticleService.getDiffBetweenRssAndWeb();
        model.addAttribute("articles", differentArticles);
        return "index";
    }

    @GetMapping(value = "/{fromDate}")
    public String fromDate(@PathVariable String fromDate){
        LocalDate validDate;
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("uuuu-MM-dd")
                .withResolverStyle(ResolverStyle.STRICT);

        try {
            validDate = LocalDate.parse(fromDate, formatter);
            webGetAndSave.getDataFromWebAndSave(validDate.toString());
        }
        catch (DateTimeParseException dateTimeParseException){
            System.out.println(dateTimeParseException.toString());
        }

        return "redirect:/index";
    }
}
