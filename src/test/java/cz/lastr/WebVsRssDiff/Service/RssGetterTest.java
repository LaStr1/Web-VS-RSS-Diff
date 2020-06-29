package cz.lastr.WebVsRssDiff.Service;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RssGetterTest {

    private RssGetter rssGetter;

    @Test
    public void testRssGetterGetArticlesFromRssFeed() throws IOException, FeedException, URISyntaxException {
        String fileWithRssFeedInXML = "src/test/java/cz/lastr/WebVsRssDiff/Service/rssFeedExample.xml";

        FileInputStream fileInputStream = new FileInputStream(fileWithRssFeedInXML);

        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(fileInputStream));

        rssGetter = new RssGetter();

        List<Integer> listOfArticlesFromFeed;
        listOfArticlesFromFeed = rssGetter.parseRssFeed(feed);

        assertTrue(listOfArticlesFromFeed.contains(66783280));
        assertTrue(listOfArticlesFromFeed.contains(66783260));
    }

}