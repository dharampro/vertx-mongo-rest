package com.techendear.vertx.user;

import com.techendear.vertx.application.model.UserRequest;
import io.vertx.core.Vertx;
import org.bson.json.JsonObject;

import java.util.concurrent.Future;

public class UserService {
  private Vertx vertx;

  public UserService(Vertx vertx) {
    this.vertx = vertx;
  }

  public Future<JsonObject> createUser(UserRequest userRequest) {
    return null;
  }

  public Future<JsonObject> fetchUserByTaskId(String taskId) {
    return null;
  }
}
