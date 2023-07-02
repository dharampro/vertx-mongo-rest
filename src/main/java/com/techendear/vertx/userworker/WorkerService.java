package com.techendear.vertx.userworker;

import com.techendear.vertx.application.model.UserRequest;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public class WorkerService {

  private WorkerRepository workerRepository;

  public WorkerService(WorkerRepository workerRepository) {
    this.workerRepository = workerRepository;
  }

  public Future<String> createUser(UserRequest userRequest) {
    return workerRepository.createUser(userRequest);
  }

  public Future<JsonObject> getUser(String taskId) {
    return workerRepository.getUserByTaskId(taskId);
  }

}
