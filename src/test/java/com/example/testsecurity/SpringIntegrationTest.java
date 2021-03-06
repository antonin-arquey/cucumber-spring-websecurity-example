package com.example.testsecurity;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SpringIntegrationTest {

  static MvcResult latestMvcResult;

  @Autowired
  private MockMvc mockMvc;

  private Authentication authentication;

  protected void setAuthentication(Authentication authentication) {
    this.authentication = authentication;
  }

  void executeGet(String endpoint) throws Exception {
    latestMvcResult = mockMvc.perform(get(endpoint)
        .accept(MediaType.APPLICATION_JSON)
        .with(authentication(authentication)))
        .andReturn();
  }

}
