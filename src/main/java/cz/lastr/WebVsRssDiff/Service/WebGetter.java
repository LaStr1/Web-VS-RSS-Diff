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
        List<ArticleFromWeb> articlesFromWebPage = new ArrayList<>();

        List<Integer> allArticleID = new ArrayList<>();
        List<String> allUrls = new ArrayList<>();
        List<String> allDates = new ArrayList<>();
        List<String> allTitles = new ArrayList<>();
        List<String> allPerexs = new ArrayList<>();

        Elements urls = document.select(".article-title > a:eq(0)");
        for (Element url : urls){
            String urlOfArticleWithoutPrefix = url.attr("href");
            String prefix = "https:";
            String urlOfArticle = prefix.concat(urlOfArticleWithoutPrefix);

            allUrls.add(urlOfArticle);


            String charBeforeArticleID = "c1-";
            int lengthOfCharBeforeArticleID = charBeforeArticleID.length();

            int indexOfCharBeforeArticleID = urlOfArticle.indexOf(charBeforeArticleID) + lengthOfCharBeforeArticleID;
            int lengthOfArticleID = 8;

            String articleIDAsString = urlOfArticle.substring(indexOfCharBeforeArticleID, indexOfCharBeforeArticleID + lengthOfArticleID);
            int articleID = Integer.parseInt(articleIDAsString);

            allArticleID.add(articleID);
        }

        Elements dates = document.select(".article-time");
        for (Element date : dates){
            String dateOfArticle;
            dateOfArticle = date.text();

            allDates.add(dateOfArticle);
        }

        Elements titles = document.select(".article-title");
        for (Element title : titles){
            String titleOfArticle;
            titleOfArticle = title.text();

            allTitles.add(titleOfArticle);
        }

        Elements perexs = document.select(".article-perex");
        for (Element perex : perexs){
            String perexOfArticle;
            perexOfArticle = perex.text();

            allPerexs.add(perexOfArticle);
        }

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
