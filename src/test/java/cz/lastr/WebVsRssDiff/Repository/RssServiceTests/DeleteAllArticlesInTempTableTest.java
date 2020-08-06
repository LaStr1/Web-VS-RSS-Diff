package cz.lastr.WebVsRssDiff.Repository.RssServiceTests;

import cz.lastr.WebVsRssDiff.ModelForTempTable.RssArticleTempTable;
import cz.lastr.WebVsRssDiff.Service.RssArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class DeleteAllArticlesInTempTableTest {

    @Autowired
    private RssArticleService rssArticleService;

    @Test
    public void deleteAllArticlesInTempTable() {
        RssArticleTempTable article = new RssArticleTempTable(
                66783210);
        List<RssArticleTempTable> articlesTempTable = new ArrayList<>();
        articlesTempTable.add(article);

        rssArticleService.saveToTempTable(articlesTempTable);

        rssArticleService.deleteAllArticlesInTempTable();
        List<RssArticleTempTable> articlesFromTempTable = rssArticleService.findAllInTempTable();

        assertTrue(articlesFromTempTable.isEmpty());
    }
}
