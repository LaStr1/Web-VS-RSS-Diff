package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.Model.ArticleFromRSS;
import cz.lastr.WebVsRssDiff.Model.ArticleFromWeb;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RssAndWebGetter {
    RssGetter rssGetter;
    WebGetter webGetter;

    public RssAndWebGetter(RssGetter rssGetter, WebGetter webGetter) {
        this.rssGetter = rssGetter;
        this.webGetter = webGetter;
    }

    public void getDataFromRssAndWeb(String fromDate) {
        List<ArticleFromRSS> rssFeed = rssGetter.getArticlesFromRSS();
        // save to db

        List<ArticleFromWeb> webFeed = webGetter.getArticlesFromWeb(fromDate);
        // save to db

    }
}
