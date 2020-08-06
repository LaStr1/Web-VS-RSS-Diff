package cz.lastr.WebVsRssDiff.Service.Getter;

import cz.lastr.WebVsRssDiff.ModelForTempTable.RssArticleTempTable;
import cz.lastr.WebVsRssDiff.Service.RssArticleService;
import cz.lastr.WebVsRssDiff.Service.RssGetter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RssGetAndSave {
    private RssGetter rssGetter;

    private RssArticleService rssArticleService;

    public RssGetAndSave(RssGetter rssGetter, RssArticleService rssArticleService) {
        this.rssGetter = rssGetter;
        this.rssArticleService = rssArticleService;
    }

    public void getDataFromRssAndSave() {
        List<RssArticleTempTable> rssFeed = rssGetter.getArticlesFromRSS();
        rssArticleService.save(rssFeed);
    }
}
