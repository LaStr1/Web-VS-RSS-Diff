package cz.lastr.WebVsRssDiff.Model;

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
}
