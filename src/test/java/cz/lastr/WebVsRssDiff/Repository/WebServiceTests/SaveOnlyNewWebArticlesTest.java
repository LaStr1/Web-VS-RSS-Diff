package cz.lastr.WebVsRssDiff.Repository.WebServiceTests;

import cz.lastr.WebVsRssDiff.ModelForTempTable.WebArticleTempTable;
import cz.lastr.WebVsRssDiff.Service.WebArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
public class SaveOnlyNewWebArticlesTest {

    @Autowired
    WebArticleService webArticleService;

    @Test
    public void saveOnlyNewWebArticles() {
        WebArticleTempTable article1 = new WebArticleTempTable(
                61111110,
                "https://",
                "1. 1.",
                "Title 1",
                "Perex 1");

        WebArticleTempTable article2 = new WebArticleTempTable(
                62222220,
                "https://",
                "2. 2.",
                "Title 2",
                "Perex 2");

        List<WebArticleTempTable> testArticles = new ArrayList<>();
        testArticles.add(article1);
        testArticles.add(article2);

        webArticleService.saveToTemp(testArticles);

        webArticleService.saveToRegularIfNotExist();

        List<WebArticleTempTable> testArticlesWebArticleTempTables = new ArrayList<>();
        testArticlesWebArticleTempTables.add(article1);
        testArticlesWebArticleTempTables.add(article2);

        WebArticleTempTable article3 = new WebArticleTempTable(
                67777770,
                "https://",
                "2. 2.",
                "Title 2",
                "Perex 2");

        testArticlesWebArticleTempTables.add(article3);

        webArticleService.saveToTemp(testArticlesWebArticleTempTables);
        webArticleService.saveToRegularIfNotExist();

        assertFalse(webArticleService.containDuplicate());
    }
}
