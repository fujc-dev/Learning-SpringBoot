package com.kggs.wg5000sdk;

import com.kggs.wg5000sdk.factory.WG5000Factory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Wg5000sdkApplicationTests {

    @Test
    void contextLoads() {
        WG5000Factory.Open("127.0.0.1", 6000, "abc", "123", "10001啥也不是");

    }
}
