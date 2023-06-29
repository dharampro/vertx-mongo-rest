package com.techendear.vertx.user;

import com.techendear.vertx.user.domain.UserResponse;

import java.util.concurrent.Future;

public class UserHandler {
  private final UserService userService;

  public UserHandler(UserService userService) {
    this.userService = userService;
  }

  public Future<UserResponse> getUser() {
    return userService.getUser();
  }
}
