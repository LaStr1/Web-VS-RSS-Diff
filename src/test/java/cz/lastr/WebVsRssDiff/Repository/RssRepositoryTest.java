package cz.lastr.WebVsRssDiff.Repository;

import cz.lastr.WebVsRssDiff.Model.RssArticle;
import cz.lastr.WebVsRssDiff.Service.RssArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RssRepositoryTest {

    @Autowired
    RssArticleService rssArticleService;

    @Test
    public void saveRssArticleTest(){
        RssArticle rssArticle = new RssArticle(
                66783210);
        List<RssArticle> rssArticlesList = new ArrayList<>();

        rssArticlesList.add(rssArticle);
        rssArticleService.save(rssArticlesList);

        assertNotNull(rssArticleService.findAll());
        assertEquals(rssArticleService.findAll().get(0).getArticleID(), 66783210);
    }
}
