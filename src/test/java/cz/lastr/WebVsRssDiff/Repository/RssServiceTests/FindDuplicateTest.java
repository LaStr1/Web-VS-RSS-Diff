package cz.lastr.WebVsRssDiff.Repository.RssServiceTests;

import cz.lastr.WebVsRssDiff.Model.RssArticle;
import cz.lastr.WebVsRssDiff.Service.RssArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class FindDuplicateTest {

    @Autowired
    private RssArticleService rssArticleService;

    @Test
    public void findDuplicate(){
        RssArticle article1 = new RssArticle(
                66783210);

        RssArticle article2 = new RssArticle(
                62222220);

        List<RssArticle> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);

        rssArticleService.saveToRegularTable(articles);

        assertFalse(rssArticleService.containDuplicate());

        RssArticle article3 = new RssArticle(
                62222220);

        articles.add(article3);

        rssArticleService.saveToRegularTable(articles);

        assertTrue(rssArticleService.containDuplicate());
    }
}
