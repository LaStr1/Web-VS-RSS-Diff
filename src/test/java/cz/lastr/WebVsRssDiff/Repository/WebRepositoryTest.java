package cz.lastr.WebVsRssDiff.Repository;

import cz.lastr.WebVsRssDiff.Model.WebArticle;
import cz.lastr.WebVsRssDiff.Service.WebArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class WebRepositoryTest {

    @Autowired
    WebArticleService webArticleService;

    @Test
    public void saveWebArticleTest(){
        WebArticle webArticle = new WebArticle(
                66783210,
                "https://archiv.ihned.cz/c1-66783210-kureci-kartel-u-pilgrim-s",
                "26. 6.",
                "Kuřecí kartel u Pilgrim's",
                "Komentář Marka Dongresa Americké ministerstvo spravedlnosti přišlo začátkem měsíce se zprávou, že producent kuřat Pilgrim's Pride spolu s dalšími na trhu fixoval...");

        List<WebArticle> webArticlesList = new ArrayList<>();
        webArticlesList.add(webArticle);

        webArticleService.save(webArticlesList);

        assertNotNull(webArticleService.findAll());
        assertEquals(webArticleService.findAll().get(0).getArticleID(), 66783210);
    }
}
