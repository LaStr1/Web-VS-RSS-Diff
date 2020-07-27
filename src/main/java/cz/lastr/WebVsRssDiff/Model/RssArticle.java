package cz.lastr.WebVsRssDiff.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class RssArticle {

    @Id
    @GeneratedValue
    private long id;

    private int articleID;

    public RssArticle(){

    }

    public RssArticle(int articleID) {
        this.articleID = articleID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RssArticle)) return false;
        RssArticle that = (RssArticle) o;
        return articleID == that.articleID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleID);
    }
}
