package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import java.util.List;

@Controller
public class UserController {

  @Inject
  UserService userService;

  @Get
  public List<User> get() {
    return userService.get();
  }
}
