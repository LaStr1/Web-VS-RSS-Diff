package cz.lastr.WebVsRssDiff.Service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssGetter {

    public RssGetter(){
    }

    public List<Integer> getArticlesFromRSS() throws IOException, FeedException {
        URL url = new URL("https://ihned.cz/?m=rss");
        List<Integer> articlesFromRSS;

        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(url));

        articlesFromRSS = parseRssFeed(feed);

        return articlesFromRSS;
    }

    public List<Integer> parseRssFeed(SyndFeed feed) throws IOException, FeedException {
        List<SyndEntry> itemsInFeed;
        List<Integer> articlesFromRSS;

        itemsInFeed = feed.getEntries();

        articlesFromRSS = getArticlesFromFeed(itemsInFeed);

        return articlesFromRSS;
    }

    private List<Integer> getArticlesFromFeed(List<SyndEntry> itemsInFeed) {
        List<Integer> articlesFromRSS = new ArrayList<>();

        for (SyndEntry item : itemsInFeed){
            int indexOfDash = item.getUri().indexOf("-") + 1;

            String articleItemInString = item.getUri().substring(indexOfDash);
            int articleItem = Integer.parseInt(articleItemInString);

            System.out.println(articleItem);
            articlesFromRSS.add(articleItem);
        }
        return articlesFromRSS;
    }
}
