package com.techendear.vertx.worker;

import com.techendear.vertx.application.model.Product;
import com.techendear.vertx.application.model.User;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.List;

public class WorkerService {

  private WorkerRepository workerRepository;

  public WorkerService(WorkerRepository workerRepository) {
    this.workerRepository = workerRepository;
  }

  public Future<String> createUser(User user) {
    return workerRepository.createUser(user);
  }

  public Future<JsonObject> getUser(String taskId) {
    return workerRepository.getUserByTaskId(taskId);
  }

  public Future<String> createProduct(Product product) {
    return workerRepository.createProduct(product);
  }

  public Future<JsonObject> getProduct(String taskId) {
    return workerRepository.getProductByTaskId(taskId);
  }

  public Future<JsonObject> getProductsById(List<String> productIds) {
    return null;
  }
}
