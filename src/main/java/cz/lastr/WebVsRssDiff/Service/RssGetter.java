package cz.lastr.WebVsRssDiff.Service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssGetter {

    URL url;

    public RssGetter(URL url){
        this.url = url;
    }

    public List<Integer> getRss() throws IOException, FeedException {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(url));

        List<SyndEntry> items = feed.getEntries();
        List<Integer> articlesFromRSS = new ArrayList<>();

        for (SyndEntry item : items){
            int indexOfDash = item.getUri().indexOf("-") + 1;

            String articleItemInString = item.getUri().substring(indexOfDash);
            int articleItem = Integer.parseInt(articleItemInString);

            System.out.println(articleItem);
            articlesFromRSS.add(articleItem);
        }
        return articlesFromRSS;
    }
}
