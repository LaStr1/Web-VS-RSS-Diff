package cz.lastr.WebVsRssDiff.Service.Getter;

import org.springframework.stereotype.Service;

@Service
public class RssAndWebGetter {
    RssGetAndSave rssGetAndSave;
    WebGetAndSave webGetAndSave;

    public RssAndWebGetter(RssGetAndSave rssGetAndSave, WebGetAndSave webGetAndSave) {
        this.rssGetAndSave = rssGetAndSave;
        this.webGetAndSave = webGetAndSave;
    }

    public void getDataFromRssAndWeb(String fromDate) {
        rssGetAndSave.getDataFromRssAndSave();
        webGetAndSave.getDataFromWebAndSave(fromDate);
    }
}