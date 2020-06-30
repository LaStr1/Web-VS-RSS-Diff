package cz.lastr.WebVsRssDiff.Service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import cz.lastr.WebVsRssDiff.Model.ArticleFromRSS;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssGetter {

    public RssGetter(){
    }

    public List<ArticleFromRSS> getArticlesFromRSS() throws IOException, FeedException {
        URL url = new URL("https://ihned.cz/?m=rss");
        List<ArticleFromRSS> articlesFromRSS;

        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(url));

        articlesFromRSS = parseRssFeed(feed);

        return articlesFromRSS;
    }

    public List<ArticleFromRSS> parseRssFeed(SyndFeed feed) {
        List<SyndEntry> itemsInFeed;
        List<ArticleFromRSS> articlesFromRSS;

        itemsInFeed = feed.getEntries();

        articlesFromRSS = getArticlesFromFeed(itemsInFeed);

        return articlesFromRSS;
    }

    private List<ArticleFromRSS> getArticlesFromFeed(List<SyndEntry> itemsInFeed) {
        List<ArticleFromRSS> articlesFromRSS = new ArrayList<>();

        for (SyndEntry item : itemsInFeed){
            int indexOfDash = getIndexOfDash(item);

            String articleAsString = getArticleAsString(item, indexOfDash);
            int articleAsInteger = parseArticleToInteger(articleAsString);

            ArticleFromRSS articleFromRSS = new ArticleFromRSS(articleAsInteger);

            articlesFromRSS.add(articleFromRSS);
        }

        return articlesFromRSS;
    }

    private int getIndexOfDash(SyndEntry item) {
        return item.getUri().indexOf("-") + 1;
    }

    private String getArticleAsString(SyndEntry item, int indexOfDash) {
        return item.getUri().substring(indexOfDash);
    }

    private int parseArticleToInteger(String articleItemAsString) {
        return Integer.parseInt(articleItemAsString);
    }

}
