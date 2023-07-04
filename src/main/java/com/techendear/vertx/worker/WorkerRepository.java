package com.techendear.vertx.worker;

import com.techendear.vertx.application.model.Product;
import com.techendear.vertx.application.model.User;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

import java.util.List;

public class WorkerRepository {

  private static final String USER_COLLECTION = "userCollection";
  private static final String PRODUCT_COLLECTION = "productCollection";

  private MongoClient mongoClient;

  public WorkerRepository(MongoClient mongoClient) {
    this.mongoClient = mongoClient;
  }

  public Future<String> createUser(User user) {
    return mongoClient.save(USER_COLLECTION, JsonObject.mapFrom(user));
  }

  public Future<JsonObject> getUserByTaskId(String taskId) {
    JsonObject query = new JsonObject().put("_id", taskId);
    return mongoClient.findOne(USER_COLLECTION, query, null);
  }

  public Future<String> createProduct(Product product) {
    return mongoClient.save(PRODUCT_COLLECTION, JsonObject.mapFrom(product));
  }

  public Future<JsonObject> getProductByTaskId(String taskId) {
    JsonObject query = new JsonObject().put("_id", taskId);
    return mongoClient.findOne(PRODUCT_COLLECTION, query, null);
  }

  public Future<List<JsonObject>> getProducts(List<String> productIds) {
    return mongoClient.find(PRODUCT_COLLECTION, JsonObject.of("_id", JsonArray.of(productIds)));
  }
}
