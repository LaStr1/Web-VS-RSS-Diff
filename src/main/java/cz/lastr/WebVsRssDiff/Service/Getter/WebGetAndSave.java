package cz.lastr.WebVsRssDiff.Service.Getter;

import cz.lastr.WebVsRssDiff.ModelForTempTable.WebArticleTempTable;
import cz.lastr.WebVsRssDiff.Service.WebArticleService;
import cz.lastr.WebVsRssDiff.Service.WebGetter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebGetAndSave {
    private WebGetter webGetter;

    private WebArticleService webArticleService;

    public WebGetAndSave(WebGetter webGetter, WebArticleService webArticleService) {
        this.webGetter = webGetter;
        this.webArticleService = webArticleService;
    }

    public void getDataFromWebAndSave(String fromDate) {
        List<WebArticleTempTable> webFeed = webGetter.getArticlesFromWeb(fromDate);
        webArticleService.save(webFeed);
    }
}
