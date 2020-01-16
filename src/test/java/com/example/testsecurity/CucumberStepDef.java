package com.example.testsecurity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Collections;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class CucumberStepDef extends SpringIntegrationTest {
  @Then("the client receives status code of {int}")
  public void theClientReceivesStatusCodeOf(int statusCode) {
    assertEquals(statusCode, latestMvcResult.getResponse().getStatus());
  }

  @When("the client calls /cats")
  public void theClientCallsCats() throws Exception {
    executeGet();
  }

  @And("the client receives a cat name {string}")
  public void theClientReceivesACatNameJoe(String catName) throws UnsupportedEncodingException {
    assertTrue(latestMvcResult.getResponse().getContentAsString().contains(catName));
  }

  @Before("@WithAdminUser")
  public void setupAdminUser() {
    Jwt jwt = new Jwt("toto", Instant.MIN, Instant.MAX, Collections.singletonMap("test", "test"),
        Collections.singletonMap("test", "test"));

    SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(jwt, createAuthorityList("admin")));
  }
}
