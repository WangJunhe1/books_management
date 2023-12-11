package com.seven;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;

@SpringBootTest
class BackEndApplicationTests {

    @Test
    void contextLoads() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("static/img/1/1.jpg");
        String imagePath = resource.getPath();
        System.out.println(imagePath);
    }
}
