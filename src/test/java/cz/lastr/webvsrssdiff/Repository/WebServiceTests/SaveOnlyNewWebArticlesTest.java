package cz.lastr.webvsrssdiff.Repository.WebServiceTests;

import cz.lastr.webvsrssdiff.ModelForTempTable.WebArticleTempTable;
import cz.lastr.webvsrssdiff.Service.WebArticleService;
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
    private WebArticleService webArticleService;

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

        List<WebArticleTempTable> firstInsertArticles = new ArrayList<>();
        firstInsertArticles.add(article1);
        firstInsertArticles.add(article2);

        webArticleService.saveToTempTable(firstInsertArticles);

        webArticleService.saveFromTempTableToRegularTableIfNotExist();

        List<WebArticleTempTable> secondInsertArticles = new ArrayList<>();
        secondInsertArticles.add(article1);
        secondInsertArticles.add(article2);

        WebArticleTempTable article3 = new WebArticleTempTable(
                67777770,
                "https://",
                "2. 2.",
                "Title 2",
                "Perex 2");

        secondInsertArticles.add(article3);

        webArticleService.saveToTempTable(secondInsertArticles);
        webArticleService.saveFromTempTableToRegularTableIfNotExist();

        assertFalse(webArticleService.containDuplicate());
    }
}
