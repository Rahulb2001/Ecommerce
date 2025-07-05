package org.programming.DiscoverServer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoverServerApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(DiscoverServerApplication.class, args);
    }
}
