package cz.lastr.webvsrssdiff.Repository.RssServiceTests;

import cz.lastr.webvsrssdiff.ModelForTempTable.RssArticleTempTable;
import cz.lastr.webvsrssdiff.Service.RssArticleService;
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
    private RssArticleService rssArticleService;

    @Test
    public void saveOnlyNewWebArticles() {
        RssArticleTempTable article1 = new RssArticleTempTable(
                61111110);

        RssArticleTempTable article2 = new RssArticleTempTable(
                62222220);

        List<RssArticleTempTable> firstInsertArticles = new ArrayList<>();
        firstInsertArticles.add(article1);
        firstInsertArticles.add(article2);

        rssArticleService.saveToTempTable(firstInsertArticles);

        rssArticleService.saveFromTempTableToRegularTableIfNotExist();

        List<RssArticleTempTable> secondInsertArticles = new ArrayList<>();
        secondInsertArticles.add(article1);
        secondInsertArticles.add(article2);

        RssArticleTempTable article3 = new RssArticleTempTable(
                67777770);

        secondInsertArticles.add(article3);

        rssArticleService.saveToTempTable(secondInsertArticles);
        rssArticleService.saveFromTempTableToRegularTableIfNotExist();

        assertFalse(rssArticleService.containDuplicate());
    }
}
