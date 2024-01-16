package dev.cstraka.bungle;

import org.springframework.boot.SpringApplication;

public class TestBungleApplication {
  public static void main(String[] args) {
    SpringApplication.from(BungleApplication::main)
        .with(TestContainersConfig.class)
        .run(args);
  }
}
