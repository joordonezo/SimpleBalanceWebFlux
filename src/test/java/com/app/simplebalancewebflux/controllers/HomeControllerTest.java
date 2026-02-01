package com.app.simplebalancewebflux.controllers;

import com.app.simplebalancewebflux.configuration.SecurityConfig;
import com.app.simplebalancewebflux.services.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest({HomeController.class, AuthController.class})
@Import({SecurityConfig.class, TokenService.class})
class HomeControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void rootWhenUnauthenticatedThen401() throws Exception {
        this.webTestClient
                .get()
                .uri("/")
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

    @Test
    void rootWhenAuthenticatedThenSaysHelloUser() throws Exception {
        EntityExchangeResult<String> result = this.webTestClient
                .post()
                .uri("/token")
                .headers(headers -> headers.setBasicAuth("jorge","password"))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .returnResult();

        String token = result.getResponseBody();

        this.webTestClient
                .get()
                .uri("/")
                .header("Authorization", "Bearer "+ token);
    }

    @Test
    @WithMockUser
    public void rootWithMockUserStatusIsOK() throws Exception {
        this.webTestClient.get().uri("/").exchange().expectStatus().isOk();
    }
}