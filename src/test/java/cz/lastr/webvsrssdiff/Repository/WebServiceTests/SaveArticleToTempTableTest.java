package cz.lastr.webvsrssdiff.Repository.WebServiceTests;

import cz.lastr.webvsrssdiff.ModelForTempTable.WebArticleTempTable;
import cz.lastr.webvsrssdiff.Service.WebArticleService;
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
    private WebArticleService webArticleService;

    @Test
    public void saveArticleToTempTable() {
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
}
