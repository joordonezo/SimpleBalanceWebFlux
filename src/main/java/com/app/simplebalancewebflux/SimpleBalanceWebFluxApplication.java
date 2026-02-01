package com.app.simplebalancewebflux;

import com.app.simplebalancewebflux.configuration.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class SimpleBalanceWebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleBalanceWebFluxApplication.class, args);
    }

}
