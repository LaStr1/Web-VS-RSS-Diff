package cz.lastr.WebVsRssDiff.ModelForTempTable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class RssArticleTempTable {

    @Id
    @GeneratedValue
    private long id;

    private int articleID;

    public RssArticleTempTable(){

    }

    public RssArticleTempTable(int articleID) {
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
        if (o == null || getClass() != o.getClass()) return false;
        RssArticleTempTable that = (RssArticleTempTable) o;
        return articleID == that.articleID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleID);
    }
}
