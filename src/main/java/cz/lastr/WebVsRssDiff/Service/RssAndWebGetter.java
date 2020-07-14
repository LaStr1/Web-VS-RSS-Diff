package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.Model.RssArticle;
import cz.lastr.WebVsRssDiff.Model.WebArticle;
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
        List<RssArticle> rssFeed = rssGetter.getArticlesFromRSS();
        // save to db

        List<WebArticle> webFeed = webGetter.getArticlesFromWeb(fromDate);
        // save to db

    }
}
