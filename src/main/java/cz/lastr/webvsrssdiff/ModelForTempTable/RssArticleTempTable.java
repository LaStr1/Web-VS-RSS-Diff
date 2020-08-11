package cz.lastr.webvsrssdiff.ModelForTempTable;

import cz.lastr.webvsrssdiff.Model.RssArticle;

import javax.persistence.Entity;

@Entity
public class RssArticleTempTable extends RssArticle {

    public RssArticleTempTable() {
    }

    public RssArticleTempTable(int articleID) {
        super(articleID);
    }
}
