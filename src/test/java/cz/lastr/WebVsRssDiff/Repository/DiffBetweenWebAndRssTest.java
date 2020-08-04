package cz.lastr.WebVsRssDiff.Repository;

import cz.lastr.WebVsRssDiff.ModelForTempTable.RssArticleTempTable;
import cz.lastr.WebVsRssDiff.ModelForTempTable.WebArticleTempTable;
import cz.lastr.WebVsRssDiff.Service.RssArticleService;
import cz.lastr.WebVsRssDiff.Service.WebArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class DiffBetweenWebAndRssTest {

    @Autowired
    WebArticleService webArticleService;

    @Autowired
    RssArticleService rssArticleService;

    List<WebArticleTempTable> webArticles = new ArrayList<>();
    List<RssArticleTempTable> rssArticles = new ArrayList<>();

    WebArticleTempTable webArticle1;
    WebArticleTempTable webArticle2;
    WebArticleTempTable webArticle3;
    WebArticleTempTable webArticle4;
    WebArticleTempTable webArticle5;
    WebArticleTempTable webArticle6;
    WebArticleTempTable webArticle7;

    RssArticleTempTable rssArticle1;
    RssArticleTempTable rssArticle2;
    RssArticleTempTable rssArticle3;
    RssArticleTempTable rssArticle4;

    @BeforeEach
    private void setup(){
        webArticle1 = new WebArticleTempTable(61111110, "https://", "1. 7.", "Title 1", "Text 1");
        webArticle2 = new WebArticleTempTable(62222220, "https://", "1. 7.", "Title 2", "Text 2");
        webArticle3 = new WebArticleTempTable(63333330, "https://", "1. 7.", "Title 3", "Text 3");
        webArticle4 = new WebArticleTempTable(64444440, "https://", "1. 7.", "Title 4", "Text 4");
        webArticle5 = new WebArticleTempTable(65555550, "https://", "1. 7.", "Title 5", "Text 5");
        webArticle6 = new WebArticleTempTable(66666660, "https://", "1. 7.", "Title 6", "Text 6");
        webArticle7 = new WebArticleTempTable(67777770, "https://", "1. 7.", "Title 7", "Text 7");

        rssArticle1 = new RssArticleTempTable(61111110);
        rssArticle2 = new RssArticleTempTable(62222220);
        rssArticle3 = new RssArticleTempTable(64444440);
        rssArticle4 = new RssArticleTempTable(67777770);

        webArticles.add(webArticle1);
        webArticles.add(webArticle2);
        webArticles.add(webArticle3);
        webArticles.add(webArticle4);
        webArticles.add(webArticle5);
        webArticles.add(webArticle6);
        webArticles.add(webArticle7);

        rssArticles.add(rssArticle1);
        rssArticles.add(rssArticle2);
        rssArticles.add(rssArticle3);
        rssArticles.add(rssArticle4);
    }

    @Test
    public void selectWhichArticlesIsInWebPage_And_NotInRss(){
        webArticleService.saveToTempTable(webArticles);
        rssArticleService.saveToTempTable(rssArticles);

        List<WebArticleTempTable> diffWebArticles = webArticleService.getDiffBetweenRssAndWeb();

        assertTrue(diffWebArticles.contains(webArticle3));
        assertTrue(diffWebArticles.contains(webArticle5));
        assertTrue(diffWebArticles.contains(webArticle6));
    }
}
