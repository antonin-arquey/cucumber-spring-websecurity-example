package com.example.testsecurity;

import java.util.Collections;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatController {

  @GetMapping("/cats")
  public List<Cat> getCats(Authentication auth) {
    return Collections.singletonList(new Cat("Joe"));
  }
}
