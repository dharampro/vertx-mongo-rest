package com.techendear.vertx.userworker;

import com.techendear.vertx.application.model.UserRequest;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

public class WorkerRepository {


  private static final String USER_COLLECTION = "userCollection";
  private MongoClient mongoClient;

  public WorkerRepository(MongoClient mongoClient) {
    this.mongoClient = mongoClient;
  }

  public Future<String> createUser(UserRequest userRequest) {
    return mongoClient.save(USER_COLLECTION, JsonObject.mapFrom(userRequest));
  }

  public Future<JsonObject> getUserByTaskId(String taskId) {
    JsonObject query = new JsonObject().put("_id", taskId);
    return mongoClient.findOne(USER_COLLECTION, query, null);
  }
}
