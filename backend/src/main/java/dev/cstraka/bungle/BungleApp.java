package dev.cstraka.bungle;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BungleApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BungleApp.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
