package cz.lastr.webvsrssdiff;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class WebVsRssDiffApplication extends SpringBootServletInitializer {

    public static void main(String[] args)  {
        SpringApplication.run(WebVsRssDiffApplication.class, args);
    }

}
