package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.Model.WebArticle;
import cz.lastr.WebVsRssDiff.ModelForTempTable.WebArticleTempTable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebGetter {

    public List<WebArticleTempTable> getArticlesFromWeb(String fromDate) {
        List<WebArticleTempTable> articlesFromWeb = new ArrayList<>();
        String urlWithoutDate = "https://archiv.ihned.cz/?p=0A0000&archive[source_date]=";
        String url = urlWithoutDate + fromDate;

        Document document = getWebPage(url);

        if(document != null) {
            articlesFromWeb = parseWebPage(document);
        }

        return articlesFromWeb;
    }

    private Document getWebPage(String url) {
        Document htmlPage = null;
        try {
            htmlPage = getHtmlPage(url);
        }
        catch (IOException ioException){
            System.out.println(ioException.toString());
        }
        return htmlPage;
    }

    private Document getHtmlPage(String url) throws IOException {
        String userAgent = "Mozilla/5.0 (Linux x86_64; rv:78.0) Gecko/20100101 Firefox/78.0";

        return Jsoup
                .connect(url)
                .userAgent(userAgent)
                .get();
    }

    public List<WebArticleTempTable> parseWebPage(Document document) {
        List<String> allUrls = getAllUrls(document);
        List<Integer> allArticleID = getAllArticleID(allUrls);
        List<String> allDates = getAllDates(document);
        List<String> allTitles = getAllTitles(document);
        List<String> allPerexs = getAllPerexs(document);

        List<WebArticleTempTable> articlesFromWebPage = makeAllArticles(allUrls, allArticleID, allDates, allTitles, allPerexs);
        return articlesFromWebPage;
    }

    private List<String> getAllUrls(Document document) {
        List<String> allUrls = new ArrayList<>();
        Elements urls = document.select(".article-title > a:eq(0)");
        for (Element url : urls){
            String urlOfArticleWithoutPrefix = url.attr("href");
            String prefix = "https:";
            String urlOfArticle = prefix.concat(urlOfArticleWithoutPrefix);

            allUrls.add(urlOfArticle);
        }
        return allUrls;
    }

    private List<Integer> getAllArticleID(List<String> allUrls) {
        List<Integer> allArticleID = new ArrayList<>();
        for (String url : allUrls){
            String charBeforeArticleID = "c1-";
            int lengthOfCharBeforeArticleID = charBeforeArticleID.length();

            int indexOfCharBeforeArticleID = url.indexOf(charBeforeArticleID) + lengthOfCharBeforeArticleID;
            int lengthOfArticleID = 8;

            String articleIDAsString = url.substring(indexOfCharBeforeArticleID, indexOfCharBeforeArticleID + lengthOfArticleID);
            int articleID = Integer.parseInt(articleIDAsString);

            allArticleID.add(articleID);
        }
        return allArticleID;
    }

    private List<String> getAllDates(Document document) {
        List<String> allDates = new ArrayList<>();
        Elements dates = document.select(".article-time");
        for (Element date : dates){
            String dateOfArticle;
            dateOfArticle = date.text();

            allDates.add(dateOfArticle);
        }
        return allDates;
    }

    private List<String> getAllTitles(Document document) {
        List<String> allTitles = new ArrayList<>();
        Elements titles = document.select(".article-title");
        for (Element title : titles){
            String titleOfArticle;
            titleOfArticle = title.text();

            allTitles.add(titleOfArticle);
        }
        return allTitles;
    }

    private List<String> getAllPerexs(Document document) {
        List<String> allPerexs = new ArrayList<>();

        Elements perexs = document.select(".article-perex");
        for (Element perex : perexs){
            String perexOfArticle;
            perexOfArticle = perex.text();

            allPerexs.add(perexOfArticle);
        }
        return allPerexs;
    }

    private List<WebArticleTempTable> makeAllArticles(List<String> allUrls,
                                             List<Integer> allArticleID,
                                             List<String> allDates,
                                             List<String> allTitles,
                                             List<String> allPerexs) {

        List<WebArticleTempTable> articlesFromWebPage = new ArrayList<>();

        int allItemsCount = allArticleID.size();
        for (int itemsCount = 0; itemsCount < allItemsCount; ++itemsCount) {
            int articleID = allArticleID.get(itemsCount);
            String url = allUrls.get(itemsCount);
            String date = allDates.get(itemsCount);
            String title = allTitles.get(itemsCount);
            String perex = allPerexs.get(itemsCount);

            WebArticleTempTable article = new WebArticleTempTable();
            article.setArticleID(articleID);
            article.setUrl(url);
            article.setDate(date);
            article.setTitle(title);
            article.setPerex(perex);

            articlesFromWebPage.add(article);
        }
        return articlesFromWebPage;
    }
}
