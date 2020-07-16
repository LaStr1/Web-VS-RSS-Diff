package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.Model.RssArticle;
import cz.lastr.WebVsRssDiff.Model.WebArticle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RssAndWebGetter {
    RssGetter rssGetter;
    WebGetter webGetter;

    RssArticleService rssArticleService;
    WebArticleService webArticleService;

    public RssAndWebGetter(RssGetter rssGetter, WebGetter webGetter, RssArticleService rssArticleService, WebArticleService webArticleService) {
        this.rssGetter = rssGetter;
        this.webGetter = webGetter;
        this.rssArticleService = rssArticleService;
        this.webArticleService = webArticleService;
    }

    public void getDataFromRssAndWeb(String fromDate) {
        List<RssArticle> rssFeed = rssGetter.getArticlesFromRSS();
        rssArticleService.save(rssFeed);

        List<WebArticle> webFeed = webGetter.getArticlesFromWeb(fromDate);
        webArticleService.save(webFeed);
    }
}
