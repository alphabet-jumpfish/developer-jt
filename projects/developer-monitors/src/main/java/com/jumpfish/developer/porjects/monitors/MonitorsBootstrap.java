package com.jumpfish.developer.porjects.monitors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.jumpfish.developer")
@EntityScan("com.jumpfish.developer")
public class MonitorsBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(MonitorsBootstrap.class, args);
    }
}
