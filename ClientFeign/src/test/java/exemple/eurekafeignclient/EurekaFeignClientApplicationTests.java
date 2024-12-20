package exemple.eurekafeignclient;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EurekaFeignClientApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class EurekaFeignClientApplicationTests {

    @Test
    void contextLoads() {
        // Test si le contexte Spring Boot se charge correctement
    }
}
