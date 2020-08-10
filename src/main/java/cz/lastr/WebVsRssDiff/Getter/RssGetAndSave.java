package cz.lastr.WebVsRssDiff.Getter;

import cz.lastr.WebVsRssDiff.ModelForTempTable.RssArticleTempTable;
import cz.lastr.WebVsRssDiff.Service.RssArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RssGetAndSave {
    private final RssGetter rssGetter;
    private final RssArticleService rssArticleService;

    public RssGetAndSave(RssGetter rssGetter, RssArticleService rssArticleService) {
        this.rssGetter = rssGetter;
        this.rssArticleService = rssArticleService;
    }

    public void getDataFromRssAndSave() {
        List<RssArticleTempTable> rssFeed = rssGetter.getArticlesFromRSS();
        rssArticleService.saveToRegularTableIfNotExist(rssFeed);
    }
}
