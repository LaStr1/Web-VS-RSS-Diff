package cz.lastr.WebVsRssDiff.Model;

import java.util.Objects;

public class ArticleFromRSS {

    private int articleID;

    public ArticleFromRSS(int articleID) {
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

        ArticleFromRSS that = (ArticleFromRSS) o;

        return articleID == that.articleID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleID);
    }
}
