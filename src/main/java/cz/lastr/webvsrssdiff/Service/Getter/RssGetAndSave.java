package cz.lastr.webvsrssdiff.Service.Getter;

import cz.lastr.webvsrssdiff.ModelForTempTable.RssArticleTempTable;
import cz.lastr.webvsrssdiff.Service.RssArticleService;
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
