package cz.lastr.WebVsRssDiff.Model;

import java.util.Objects;

public class RssArticle {

    private int articleID;

    public RssArticle(int articleID) {
        this.articleID = articleID;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        RssArticle that = (RssArticle) o;

        return articleID == that.articleID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleID);
    }
}
