package cz.lastr.WebVsRssDiff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebVsRssDiffApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebVsRssDiffApplication.class, args);
    }

}
