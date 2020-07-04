package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.Model.ArticleFromWeb;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebGetterTest {

    private WebGetter webGetter = new WebGetter();

    private ArticleFromWeb firstTestedArticle;
    private ArticleFromWeb secondTestedArticle;

    private List<ArticleFromWeb> ArticlesFromFeed;

    @Test
    public void testWebGetterGetArticlesFromWeb() throws IOException {
        firstTestedArticle = new ArticleFromWeb(
                66783210,
                "https://archiv.ihned.cz/c1-66783210-kureci-kartel-u-pilgrim-s",
                "26. 6.",
                "Kuřecí kartel u Pilgrim's",
                "Komentář Marka Dongresa Americké ministerstvo spravedlnosti přišlo začátkem měsíce se zprávou, že producent kuřat Pilgrim's Pride spolu s dalšími na trhu fixoval...");

        File testPage = new File("src/test/java/cz/lastr/WebVsRssDiff/Service/testPageArchiv.ihned.cz.html");

        Document document = Jsoup.parse(testPage,"windows-1250");

        ArticlesFromFeed = webGetter.parseWebPage(document);

        assertTrue(ArticlesFromFeed.contains(firstTestedArticle));
    }
}