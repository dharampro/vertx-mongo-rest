package com.techendear.vertx.user;

import com.techendear.vertx.user.model.UserRequest;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;

public class UserService {

  private UserRepository userRepository;
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Future<String> createUser(UserRequest userRequest) {
    return userRepository.createUser(userRequest);
  }

  public Future<JsonObject> getUser(String taskId) {
    return userRepository.getUserByTaskId(taskId);
  }

  public Future<JsonObject> createExternalUser(UserRequest request) {
    return null;
  }
}
