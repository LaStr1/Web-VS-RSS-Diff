package cz.lastr.WebVsRssDiff.Controller;

import cz.lastr.WebVsRssDiff.Model.WebArticle;
import cz.lastr.WebVsRssDiff.Service.Getter.RssGetAndSave;
import cz.lastr.WebVsRssDiff.Service.Getter.WebGetAndSave;
import cz.lastr.WebVsRssDiff.Service.WebArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(IndexController.class);

    private RssGetAndSave rssGetAndSave;
    private WebGetAndSave webGetAndSave;
    private WebArticleService webArticleService;


    public IndexController(RssGetAndSave rssGetAndSave, WebGetAndSave webGetAndSave, WebArticleService webArticleService) {
        this.rssGetAndSave = rssGetAndSave;
        this.webGetAndSave = webGetAndSave;
        this.webArticleService = webArticleService;
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        //rssGetAndSave.getDataFromRssAndSave();

        List<WebArticle> differentArticles = webArticleService.getDiffBetweenWebAndRss();
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
            logger.error(dateTimeParseException.getMessage(), dateTimeParseException);
        }

        return "redirect:/";
    }
}
