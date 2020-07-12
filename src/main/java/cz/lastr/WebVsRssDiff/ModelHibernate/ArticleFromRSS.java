package cz.lastr.WebVsRssDiff.ModelHibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ArticleFromRSS {

    @Id
    @GeneratedValue
    private long id;

    private int articleID;

    public ArticleFromRSS(){

    }

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
