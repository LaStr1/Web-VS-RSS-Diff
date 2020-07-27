package cz.lastr.WebVsRssDiff.Repository;

import cz.lastr.WebVsRssDiff.ModelForTempTable.WebArticleTempTable;
import cz.lastr.WebVsRssDiff.Service.WebArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class WebServiceTest {

    @Autowired
    WebArticleService webArticleService;

    @Test
    public void deleteAllArticlesInTempTable() {
        webArticleService.deleteAllArticlesInTempTable();

        List<WebArticleTempTable> articlesFromTempTable = webArticleService.findAllInTempTable();

        assertTrue(articlesFromTempTable.isEmpty());
    }

    @Test
    public void saveArticleToTempTable() {
        webArticleService.deleteAllArticlesInTempTable();

        WebArticleTempTable article1 = new WebArticleTempTable(
                66783210,
                "https://",
                "1. 1.",
                "Title",
                "Perex");

        List<WebArticleTempTable> articlesTempTable = new ArrayList<>();
        articlesTempTable.add(article1);

        webArticleService.saveToTempTable(articlesTempTable);

        List<WebArticleTempTable> articlesFromTempTable = webArticleService.findAllInTempTable();

        assertTrue(articlesFromTempTable.contains(article1));
    }

    @Test
    public void saveOnlyNewWebArticles() {
        webArticleService.deleteAllArticlesInTempTable();

        WebArticleTempTable article1 = new WebArticleTempTable(
                66783210,
                "https://",
                "1. 1.",
                "Title 1",
                "Perex 1");

        List<WebArticleTempTable> articlesTempTables = new ArrayList<>();
        articlesTempTables.add(article1);

        //webArticleService.saveToTempTable(articlesTempTables);

        //webArticleService.saveOnlyNew();


        //assertTrue(articles.contains(article1));


        WebArticleTempTable article2 = new WebArticleTempTable(
                66666660,
                "https://",
                "2. 2.",
                "Title 2",
                "Perex 2");

        List<WebArticleTempTable> articles2 = new ArrayList<>();
        articles2.add(article1);
        articles2.add(article2);

        webArticleService.saveToTempTable(articles2);


        webArticleService.saveOnlyNew();
    }
}
