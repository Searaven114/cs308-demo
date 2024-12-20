package com.borau.cs308demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Log4j2
@EnableAsync
@EnableScheduling // -> belirli aralıklarla cartları check edecek bitmiş yada silinmiş ürüne sahip olan kullanıcı var mı diye, eğer var ise cartlarından silip, uyarı gönderecek
@SpringBootApplication
public class Cs308DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Cs308DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("[DEBUG] Check http://localhost:8080/startup-report for startup report");
        log.info("[DEBUG] Check http://localhost:8080/swagger-ui.html for API documentation");
    }
}
