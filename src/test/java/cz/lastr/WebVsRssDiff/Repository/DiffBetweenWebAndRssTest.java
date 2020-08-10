package cz.lastr.WebVsRssDiff.Repository;

import cz.lastr.WebVsRssDiff.Model.RssArticle;
import cz.lastr.WebVsRssDiff.Model.WebArticle;
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
    private WebArticleService webArticleService;

    @Autowired
    private RssArticleService rssArticleService;

    private final List<WebArticle> webArticles = new ArrayList<>();
    private final List<RssArticle> rssArticles = new ArrayList<>();

    private final WebArticle webArticle1 = new WebArticle(
            61111110, "https://", "1. 7.", "Title 1", "Text 1");
    private final WebArticle webArticle2 = new WebArticle(
            62222220, "https://", "1. 7.", "Title 2", "Text 2");
    private final WebArticle webArticle3 = new WebArticle(
            63333330, "https://", "1. 7.", "Title 3", "Text 3");
    private final WebArticle webArticle4 = new WebArticle(
            64444440, "https://", "1. 7.", "Title 4", "Text 4");
    private final WebArticle webArticle5 = new WebArticle(
            65555550, "https://", "1. 7.", "Title 5", "Text 5");
    private final WebArticle webArticle6 = new WebArticle(
            66666660, "https://", "1. 7.", "Title 6", "Text 6");
    private final WebArticle webArticle7 = new WebArticle(
            67777770, "https://", "1. 7.", "Title 7", "Text 7");

    private final RssArticle rssArticle1 = new RssArticle(
            61111110);
    private final RssArticle rssArticle2 = new RssArticle(
            62222220);
    private final RssArticle rssArticle3 = new RssArticle(
            64444440);
    private final RssArticle rssArticle4 = new RssArticle(
            67777770);

    @BeforeEach
    private void setup(){
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
        webArticleService.saveToRegularTable(webArticles);
        rssArticleService.saveToRegularTable(rssArticles);

        List<WebArticle> diffWebArticles = webArticleService.getDiffBetweenWebAndRss();

        assertTrue(diffWebArticles.contains(webArticle3));
        assertTrue(diffWebArticles.contains(webArticle5));
        assertTrue(diffWebArticles.contains(webArticle6));
    }
}
