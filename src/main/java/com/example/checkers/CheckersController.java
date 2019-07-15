package com.example.checkers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckersController {

  @GetMapping("/hello")
  public String demo() {
    return "Hello World";
  }
}
