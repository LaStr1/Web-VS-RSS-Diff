package cz.lastr.WebVsRssDiff.Service;

import com.rometools.rome.io.FeedException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class RssGetterTest {

    private RssGetter rssGetter;

    @Test
    public void testRssGetterIsNotNull() throws IOException, FeedException {
        URL url = new URL("https://ihned.cz/?m=rss");

        rssGetter = new RssGetter(url);
        assertNotNull(rssGetter.getRss());
    }

}