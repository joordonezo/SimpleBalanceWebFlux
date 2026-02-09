package com.app.simplebalancewebflux.controllers;

import com.app.simplebalancewebflux.configuration.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(HomeController.class)
@Import(SecurityConfig.class)
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
    @WithMockUser
    public void rootWithMockUserStatusIsOK() throws Exception {
        this.webTestClient.get().uri("/").exchange().expectStatus().isOk();
    }
}