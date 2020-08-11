package cz.lastr.webvsrssdiff.Service.Getter;

import cz.lastr.webvsrssdiff.ModelForTempTable.WebArticleTempTable;
import cz.lastr.webvsrssdiff.Service.WebArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebGetAndSave {
    private final WebGetter webGetter;
    private final WebArticleService webArticleService;

    public WebGetAndSave(WebGetter webGetter, WebArticleService webArticleService) {
        this.webGetter = webGetter;
        this.webArticleService = webArticleService;
    }

    public void getDataFromWebAndSave(String fromDate) {
        List<WebArticleTempTable> webFeed = webGetter.getArticlesFromWeb(fromDate);
        webArticleService.saveToRegularTableIfNotExist(webFeed);
    }
}
