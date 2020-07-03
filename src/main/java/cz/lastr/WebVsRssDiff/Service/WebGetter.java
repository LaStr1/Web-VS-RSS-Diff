package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.Model.ArticleFromWeb;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        List<String> allUrls = getAllUrls(document);
        List<Integer> allArticleID = getAllArticleID(allUrls);
        List<String> allDates = getAllDates(document);
        List<String> allTitles = getAllTitles(document);
        List<String> allPerexs = getAllPerexs(document);

        List<ArticleFromWeb> articlesFromWebPage = makeAllArticles(allUrls, allArticleID, allDates, allTitles, allPerexs);
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

    private List<ArticleFromWeb> makeAllArticles(List<String> allUrls,
                                                 List<Integer> allArticleID,
                                                 List<String> allDates,
                                                 List<String> allTitles,
                                                 List<String> allPerexs) {

        List<ArticleFromWeb> articlesFromWebPage = new ArrayList<>();

        int allItemsCount = allArticleID.size();
        for (int itemsCount = 0; itemsCount < allItemsCount; ++itemsCount) {
            int articleID = allArticleID.get(itemsCount);
            String url = allUrls.get(itemsCount);
            String date = allDates.get(itemsCount);
            String title = allTitles.get(itemsCount);
            String perex = allPerexs.get(itemsCount);

            ArticleFromWeb article = new ArticleFromWeb(articleID, url, date, title, perex);

            articlesFromWebPage.add(article);
        }
        return articlesFromWebPage;
    }
}
