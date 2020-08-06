package cz.lastr.WebVsRssDiff.Repository.WebServiceTests;

import cz.lastr.WebVsRssDiff.ModelForTempTable.WebArticleTempTable;
import cz.lastr.WebVsRssDiff.Service.WebArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class DeleteAllArticlesInTempTableTest {

    @Autowired
    private WebArticleService webArticleService;

    @Test
    public void deleteAllArticlesInTempTable() {
        webArticleService.deleteAllArticles();
        List<WebArticleTempTable> articlesFromTempTable = webArticleService.findAllInTempTable();

        assertTrue(articlesFromTempTable.isEmpty());
    }
}
