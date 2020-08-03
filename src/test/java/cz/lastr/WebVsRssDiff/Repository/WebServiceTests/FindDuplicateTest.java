package cz.lastr.WebVsRssDiff.Repository.WebServiceTests;

import cz.lastr.WebVsRssDiff.Model.WebArticle;
import cz.lastr.WebVsRssDiff.Service.WebArticleService;
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
    WebArticleService webArticleService;

    @Test
    public void findDuplicate(){
        WebArticle article1 = new WebArticle(
                66783210,
                "https://",
                "1. 1.",
                "Title",
                "Perex");

        WebArticle article2 = new WebArticle(
                62222220,
                "https://",
                "1. 1.",
                "Title",
                "Perex");

        List<WebArticle> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);

        webArticleService.saveToRegularTable(articles);

        assertFalse(webArticleService.containDuplicate());

        WebArticle article3 = new WebArticle(
                62222220,
                "https://",
                "1. 1.",
                "Title",
                "Perex");

        articles.add(article3);

        webArticleService.saveToRegularTable(articles);

        assertTrue(webArticleService.containDuplicate());
    }
}
