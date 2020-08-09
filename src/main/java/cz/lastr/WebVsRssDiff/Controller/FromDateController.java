package cz.lastr.WebVsRssDiff.Controller;

import cz.lastr.WebVsRssDiff.Service.Getter.WebGetAndSave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

@Controller
public class FromDateController {
    Logger logger = LoggerFactory.getLogger(FromDateController.class);

    private final WebGetAndSave webGetAndSave;

    public FromDateController(WebGetAndSave webGetAndSave) {
        this.webGetAndSave = webGetAndSave;
    }

    @GetMapping(path = "/{fromDate}")
    public String fromDate(Model model,
                           @PathVariable String fromDate){

        LocalDate validDate = null;
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("uuuu-MM-dd")
                .withResolverStyle(ResolverStyle.STRICT);

        try {
            validDate = LocalDate.parse(fromDate, formatter);
        }
        catch (DateTimeParseException dateTimeParseException){
            model.addAttribute("errorMessage", "Error: " + dateTimeParseException.getMessage());
            return "index";
        }

        int responseCode = checkUrl(validDate.toString());
        if (responseCode != HttpURLConnection.HTTP_OK) {
            logger.error("Response code: " + responseCode);

            model.addAttribute("errorMessage", responseCode + " on: " + validDate.toString());
            return "index";
        }

        webGetAndSave.getDataFromWebAndSave(validDate.toString());

        return "redirect:/";
    }

    private int checkUrl(String validDate){
        int responseCode = 0;

        String urlWithoutDate = "https://archiv.ihned.cz/?p=0A0000&archive[source_date]=";
        String urlWithDate = urlWithoutDate + validDate;

        try {
            responseCode = getResponseCode(urlWithDate);
        }
        catch (IOException ioException){
            logger.error(ioException.getMessage(), ioException);
        }

        return responseCode;
    }

    private int getResponseCode(String urlWithDate) throws IOException {
        URL url = new URL(urlWithDate);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("HEAD");

        return httpURLConnection.getResponseCode();
    }
}
