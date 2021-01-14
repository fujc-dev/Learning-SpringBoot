package com.zc58s.springbootredislock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class SpringbootRedislockApplicationTests {

    @Test
    void contextLoads() {

    }

    public static String getRandom(int min, int max){
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return String.valueOf(s);

    }

}
