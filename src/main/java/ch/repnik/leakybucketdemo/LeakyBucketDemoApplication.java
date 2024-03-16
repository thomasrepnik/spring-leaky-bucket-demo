package ch.repnik.leakybucketdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LeakyBucketDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeakyBucketDemoApplication.class, args);
    }

}
