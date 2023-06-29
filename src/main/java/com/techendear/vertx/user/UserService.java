package com.techendear.vertx.user;

import com.techendear.vertx.user.domain.UserResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class UserService {

  public Future<UserResponse> getUser() {
    UserResponse resp = new UserResponse();
    resp.setId("1234");
    resp.setName("Dharmendra Awasthi");
    resp.setEmail("abc@gmail.com");
    resp.setPhone("8787654579");
    resp.setAddress("1/531, sector-h,LKO");
    resp.setActive(true);
    return CompletableFuture.completedFuture(new UserResponse());
  }
}
