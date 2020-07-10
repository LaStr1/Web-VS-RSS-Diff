package cz.lastr.WebVsRssDiff.Controller;

import cz.lastr.WebVsRssDiff.Service.RssAndWebGetter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

@Controller
public class IndexController {
    RssAndWebGetter rssAndWebGetter;

    public IndexController(RssAndWebGetter rssAndWebGetter){
        this.rssAndWebGetter = rssAndWebGetter;
    }

    @GetMapping
    public String index(Model model) {

        return "index";
    }

    @GetMapping(value = "/{fromDate}")
    public String fromDate(@PathVariable String fromDate){
        LocalDate validDate = null;
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("uuuu-MM-dd")
                .withResolverStyle(ResolverStyle.STRICT);

        try {
            validDate = LocalDate.parse(fromDate,formatter);
            rssAndWebGetter.getDataFromRssAndWeb(validDate.toString());
        }
        catch (DateTimeParseException dateTimeParseException){
            System.out.println(dateTimeParseException.toString());
        }

        return "index";
    }
}
