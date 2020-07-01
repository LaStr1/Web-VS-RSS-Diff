package cz.lastr.WebVsRssDiff.Model;

import java.util.Objects;

public class ArticleFromWeb {

    private int articleID;
    private String url;
    private String date;
    private String title;
    private String perex;

    public ArticleFromWeb(int articleID, String url, String date, String title, String perex) {
        this.articleID = articleID;
        this.url = url;
        this.date = date;
        this.title = title;
        this.perex = perex;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerex() {
        return perex;
    }

    public void setPerex(String perex) {
        this.perex = perex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleFromWeb that = (ArticleFromWeb) o;
        return articleID == that.articleID &&
                Objects.equals(url, that.url) &&
                Objects.equals(date, that.date) &&
                Objects.equals(title, that.title) &&
                Objects.equals(perex, that.perex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleID, url, date, title, perex);
    }
}
