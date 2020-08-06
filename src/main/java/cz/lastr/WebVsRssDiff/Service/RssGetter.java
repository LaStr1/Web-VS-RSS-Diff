package cz.lastr.WebVsRssDiff.Service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import cz.lastr.WebVsRssDiff.Controller.IndexController;
import cz.lastr.WebVsRssDiff.ModelForTempTable.RssArticleTempTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class RssGetter {
    Logger logger = LoggerFactory.getLogger(RssGetter.class);

    public List<RssArticleTempTable> getArticlesFromRSS() {
        List<RssArticleTempTable> articlesFromRSS;

        SyndFeed feed = getSyndFeed();
        articlesFromRSS = parseRssFeed(feed);

        return articlesFromRSS;
    }

    private SyndFeed getSyndFeed() {
        URL url = getUrl();
        SyndFeed feed = null;
        SyndFeedInput input = new SyndFeedInput();
        try {
            XmlReader reader = new XmlReader(url);
            feed = input.build(reader);
        }
        catch (IOException ioException){
            logger.error(ioException.getMessage(), ioException);
        }
        catch (FeedException feedException){
            logger.error(feedException.getMessage(), feedException);
        }
        return feed;
    }

    private URL getUrl()  {
        URL url = null;
        try {
            url = new URL("https://ihned.cz/?m=rss");
        }
        catch (MalformedURLException malformedURLException){
            logger.error(malformedURLException.getMessage(), malformedURLException);
        }
        return url;
    }

    public List<RssArticleTempTable> parseRssFeed(SyndFeed feed) {
        List<SyndEntry> itemsInFeed;
        List<RssArticleTempTable> articlesFromRSS;

        itemsInFeed = feed.getEntries();

        articlesFromRSS = getArticlesFromFeed(itemsInFeed);

        return articlesFromRSS;
    }

    private List<RssArticleTempTable> getArticlesFromFeed(List<SyndEntry> itemsInFeed) {
        List<RssArticleTempTable> articlesFromRSS = new ArrayList<>();

        for (SyndEntry item : itemsInFeed){
            int indexOfDash = getIndexOfDash(item);

            String articleAsString = getArticleAsString(item, indexOfDash);
            int articleAsInteger = parseArticleToInteger(articleAsString);

            RssArticleTempTable rssArticle = new RssArticleTempTable();
            rssArticle.setArticleID(articleAsInteger);

            articlesFromRSS.add(rssArticle);
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
