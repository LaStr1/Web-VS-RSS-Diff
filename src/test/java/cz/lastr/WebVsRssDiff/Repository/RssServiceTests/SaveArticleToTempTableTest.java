package cz.lastr.WebVsRssDiff.Repository.RssServiceTests;

import cz.lastr.WebVsRssDiff.Model.RssArticle;
import cz.lastr.WebVsRssDiff.ModelForTempTable.RssArticleTempTable;
import cz.lastr.WebVsRssDiff.ModelForTempTable.WebArticleTempTable;
import cz.lastr.WebVsRssDiff.Service.RssArticleService;
import cz.lastr.WebVsRssDiff.Service.WebArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class SaveArticleToTempTableTest {

    @Autowired
    RssArticleService rssArticleService;

    @Test
    public void saveArticleToTempTable() {
        RssArticleTempTable article1 = new RssArticleTempTable(
                66783210);

        List<RssArticleTempTable> articlesTempTable = new ArrayList<>();
        articlesTempTable.add(article1);

        rssArticleService.saveToTempTable(articlesTempTable);

        List<RssArticleTempTable> articlesFromTempTable = rssArticleService.findAll();

        assertTrue(articlesFromTempTable.contains(article1));
    }
}
