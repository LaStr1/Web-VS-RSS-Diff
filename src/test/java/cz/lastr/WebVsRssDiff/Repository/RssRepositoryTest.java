package cz.lastr.WebVsRssDiff.Repository;

import cz.lastr.WebVsRssDiff.ModelForTempTable.RssArticleTempTable;
import cz.lastr.WebVsRssDiff.Service.RssArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class RssRepositoryTest {

    @Autowired
    RssArticleService rssArticleService;

    @Test
    public void saveRssArticleTest(){
        RssArticleTempTable rssArticle = new RssArticleTempTable(
                66783210);
        List<RssArticleTempTable> rssArticlesList = new ArrayList<>();

        rssArticlesList.add(rssArticle);
        rssArticleService.save(rssArticlesList);

        assertNotNull(rssArticleService.findAll());
        assertEquals(rssArticleService.findAll().get(0).getArticleID(), 66783210);
    }
}
