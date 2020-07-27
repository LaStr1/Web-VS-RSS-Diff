package cz.lastr.WebVsRssDiff.ModelForTempTable;

import cz.lastr.WebVsRssDiff.Model.WebArticle;

import javax.persistence.Entity;

@Entity
public class WebArticleTempTable extends WebArticle {

    public WebArticleTempTable() {
    }

    public WebArticleTempTable(int articleID, String url, String date, String title, String perex) {
        super(articleID, url, date, title, perex);
    }
}
