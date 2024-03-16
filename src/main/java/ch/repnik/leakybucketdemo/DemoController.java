package ch.repnik.leakybucketdemo;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import io.github.bucket4j.local.LocalBucket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class DemoController {

    private final LocalBucket bucket;

    public DemoController() {
        Bandwidth limit = Bandwidth.builder().capacity(5).refillGreedy(2, Duration.ofMinutes(1)).build();
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {

        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok("Hello World");
        }

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

}
