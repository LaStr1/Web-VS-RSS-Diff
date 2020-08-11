package cz.lastr.webvsrssdiff.Repository.RssServiceTests;

import cz.lastr.webvsrssdiff.Model.RssArticle;
import cz.lastr.webvsrssdiff.Service.RssArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class SaveArticleToRegularTableTest {

    @Autowired
    private RssArticleService rssArticleService;

    @Test
    public void saveArticleToRegularTable() {
        RssArticle article1 = new RssArticle(
                66783210);

        List<RssArticle> articles = new ArrayList<>();
        articles.add(article1);

        rssArticleService.saveToRegularTable(articles);

        List<RssArticle> articlesFromTable = rssArticleService.findAllInRegularTable();

        assertTrue(articlesFromTable.contains(article1));
    }
}
