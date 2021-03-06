package cz.lastr.webvsrssdiff.Schedule;

import cz.lastr.webvsrssdiff.Service.Getter.RssGetAndSave;
import cz.lastr.webvsrssdiff.Service.Getter.WebGetAndSave;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class Schedule {
    private final RssGetAndSave rssGetAndSave;
    private final WebGetAndSave webGetAndSave;

    public Schedule(RssGetAndSave rssGetAndSave, WebGetAndSave webGetAndSave) {
        this.rssGetAndSave = rssGetAndSave;
        this.webGetAndSave = webGetAndSave;
    }

    /*
    if the system was shutdown for multiple days,
    then user can manually select from which date get (web) articles in "url/{fromDate}"
     */

    // if the user start and shutdown system every day
    @PostConstruct
    public void onStartup(){
        getDataFromRssAndWebFromToday();
    }

    // if the user have running system multiple days
    @Scheduled(cron ="0 0 3 * * *" )
    public void at3am(){
        getDataFromRssAndWebFromToday();
    }

    private void getDataFromRssAndWebFromToday() {
        String today = getTodayDate();
        rssGetAndSave.getDataFromRssAndSave();
        webGetAndSave.getDataFromWebAndSave(today);
    }

    private String getTodayDate(){
        String today = LocalDate.now().toString();
        return today;
    }
}
