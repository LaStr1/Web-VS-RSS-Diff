package cz.lastr.WebVsRssDiff.Service;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class RssGetterTest {

    private RssGetter rssGetter;

    @Test
    public void testRssGetterIsNotNull() throws IOException, FeedException {
        String fileWithRssFeedInXML = "src/test/java/cz/lastr/WebVsRssDiff/Service/rssFeedExample.xml";
        FileInputStream fileInputStream = new FileInputStream(fileWithRssFeedInXML);

        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(fileInputStream));

        rssGetter = new RssGetter();
        assertNotNull(rssGetter.parseRssFeed(feed));
    }

}