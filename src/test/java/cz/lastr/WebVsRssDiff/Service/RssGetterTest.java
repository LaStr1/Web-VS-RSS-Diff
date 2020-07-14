package cz.lastr.WebVsRssDiff.Service;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import cz.lastr.WebVsRssDiff.Model.ArticleFromRSS;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RssGetterTest {
    private RssGetter rssGetter = new RssGetter();

    String fileWithRssFeedInXML = "src/test/java/cz/lastr/WebVsRssDiff/Service/resources/rssFeedExample.xml";

    private FileInputStream fileInputStream;

    private SyndFeedInput input;
    private SyndFeed feed;

    private List<ArticleFromRSS> ArticlesFromFeed;

    private ArticleFromRSS firstTestedArticle;
    private ArticleFromRSS secondTestedArticle;

    @Test
    public void testRssGetterGetArticlesFromRssFeed() throws IOException, FeedException {
        fileInputStream = new FileInputStream(fileWithRssFeedInXML);

        input = new SyndFeedInput();
        feed = input.build(new XmlReader(fileInputStream));

        ArticlesFromFeed = rssGetter.parseRssFeed(feed);

        firstTestedArticle = new ArticleFromRSS(66783280);
        secondTestedArticle = new ArticleFromRSS(66783260);

        assertTrue(ArticlesFromFeed.contains(firstTestedArticle));
        assertTrue(ArticlesFromFeed.contains(secondTestedArticle));
    }

}