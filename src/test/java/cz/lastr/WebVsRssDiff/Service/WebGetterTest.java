package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.ModelForTempTable.WebArticleTempTable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WebGetterTest {
    private final WebGetter webGetter = new WebGetter();

    @Test
    public void testWebGetterGetArticlesFromWeb() throws IOException {
        WebArticleTempTable firstTestedArticle = new WebArticleTempTable(
                66783210,
                "https://archiv.ihned.cz/c1-66783210-kureci-kartel-u-pilgrim-s",
                "26. 6. 2020",
                "Kuřecí kartel u Pilgrim's",
                "Komentář Marka Dongresa Americké ministerstvo spravedlnosti přišlo začátkem měsíce se zprávou, že producent kuřat Pilgrim's Pride spolu s dalšími na trhu fixoval...");

        File testPage = new File("src/test/java/cz/lastr/WebVsRssDiff/Service/resources/testPageArchiv.ihned.cz.html");

        Document document = Jsoup.parse(testPage,"windows-1250");

        List<WebArticleTempTable> articlesFromFeed = webGetter.parseWebPage(document);

        assertTrue(articlesFromFeed.contains(firstTestedArticle));
    }
}