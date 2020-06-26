package cz.lastr.WebVsRssDiff.Model;

public class ArticleFromRSS {

    private long id;
    private int articleID;

    public ArticleFromRSS(long id, int articleID) {
        this.id = id;
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
}
