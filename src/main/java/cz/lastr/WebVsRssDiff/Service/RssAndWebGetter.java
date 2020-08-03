package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.Service.Getter.RssGetAndSave;
import cz.lastr.WebVsRssDiff.Service.Getter.WebGetAndSave;
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
