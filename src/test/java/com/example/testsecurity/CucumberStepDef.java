package com.example.testsecurity;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class CucumberStepDef extends SpringIntegrationTest {

  @Given("a client with the following roles : {string}")
  public void aUserWithTheFollowingRolesCatsReaderCatsWriter(String roles) {
    if (roles.isEmpty()) {
      setAuthentication(generateJwtAuth(Collections.emptyList()));
    } else {
      List<GrantedAuthority> roleList = Arrays.stream(roles.split(",")).map(String::trim).map(
          SimpleGrantedAuthority::new)
          .collect(toList());
      setAuthentication(generateJwtAuth(roleList));
    }
  }

  @Then("the client receives status code of {int}")
  public void theClientReceivesStatusCodeOf(int statusCode) {
    assertEquals(statusCode, latestMvcResult.getResponse().getStatus());
  }

  @When("the client calls the {string} endpoint")
  public void theClientCallsCats(String endpoint) throws Exception {
    executeGet(endpoint);
  }

  @And("the client receives a cat name {string}")
  public void theClientReceivesACatNameJoe(String catName) throws UnsupportedEncodingException {
    assertTrue(latestMvcResult.getResponse().getContentAsString().contains(catName));
  }

  private static Authentication generateJwtAuth(List<GrantedAuthority> roleList) {
    Jwt jwt = new Jwt("toto", Instant.MIN, Instant.MAX, Collections.singletonMap("test", "test"),
        Collections.singletonMap("test", "test"));
    return new JwtAuthenticationToken(jwt, roleList);
  }
}
