package cz.lastr.WebVsRssDiff.Service;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import cz.lastr.WebVsRssDiff.ModelForTempTable.RssArticleTempTable;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RssGetterTest {
    private final RssGetter rssGetter = new RssGetter();

    @Test
    public void testRssGetterGetArticlesFromRssFeed() throws IOException, FeedException {
        String fileWithRssFeedInXML = "src/test/java/cz/lastr/WebVsRssDiff/Service/resources/rssFeedExample.xml";
        FileInputStream fileInputStream = new FileInputStream(fileWithRssFeedInXML);

        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(fileInputStream));

        List<RssArticleTempTable> articlesFromFeed = rssGetter.parseRssFeed(feed);

        RssArticleTempTable firstTestedArticle = new RssArticleTempTable(66783280);
        RssArticleTempTable secondTestedArticle = new RssArticleTempTable(66783260);

        assertTrue(articlesFromFeed.contains(firstTestedArticle));
        assertTrue(articlesFromFeed.contains(secondTestedArticle));
    }

}