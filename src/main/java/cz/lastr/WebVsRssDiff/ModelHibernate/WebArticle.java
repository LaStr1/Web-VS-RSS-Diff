package cz.lastr.WebVsRssDiff.ModelHibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class WebArticle {

    @Id
    @GeneratedValue
    private long id;

    private int articleID;

    private String url;

    private String date;

    private String title;

    private String perex;

    public WebArticle(){

    }
    public WebArticle(long id, int articleID, String url, String date, String title, String perex) {
        this.id = id;
        this.articleID = articleID;
        this.url = url;
        this.date = date;
        this.title = title;
        this.perex = perex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        WebArticle article = (WebArticle) o;
        return articleID == article.articleID &&
                Objects.equals(url, article.url) &&
                Objects.equals(date, article.date) &&
                Objects.equals(title, article.title) &&
                Objects.equals(perex, article.perex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleID, url, date, title, perex);
    }
}
