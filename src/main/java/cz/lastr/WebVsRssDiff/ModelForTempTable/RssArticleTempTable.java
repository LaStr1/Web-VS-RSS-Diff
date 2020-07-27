package cz.lastr.WebVsRssDiff.ModelForTempTable;

import cz.lastr.WebVsRssDiff.Model.RssArticle;

import javax.persistence.Entity;

@Entity
public class RssArticleTempTable extends RssArticle {

    public RssArticleTempTable() {
    }

    public RssArticleTempTable(int articleID) {
        super(articleID);
    }
}
