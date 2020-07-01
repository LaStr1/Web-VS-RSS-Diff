package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.Model.ArticleFromWeb;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebGetter {

    public List<ArticleFromWeb> getArticlesFromWeb() throws IOException {
        String urlWithoutDate = "https://archiv.ihned.cz/?p=0A0000&archive[source_date]=";
        String fromDate = "2020-06-30";

        String urlWithDate = urlWithoutDate + fromDate;

        Document document = Jsoup.connect(urlWithDate).get();

        List<ArticleFromWeb> articlesFromWeb;
        articlesFromWeb = parseWebPage(document);

        return articlesFromWeb;
    }

    public List<ArticleFromWeb> parseWebPage(Document document){
        ArticleFromWeb firstTestedArticle = new ArticleFromWeb(
                66783210,
                "https://archiv.ihned.cz/c1-66783210-kureci-kartel-u-pilgrim-s",
                "26. 6.",
                "Kuřecí kartel u Pilgrim's",
                "Americké ministerstvo spravedlnosti přišlo začátkem měsíce se zprávou, že producent kuřat Pilgrim's Pride spolu s dalšími na trhu fixoval...");

        List<ArticleFromWeb> articlesFromWebPage = new ArrayList<>();
        articlesFromWebPage.add(firstTestedArticle);
        return articlesFromWebPage;
    }
}
