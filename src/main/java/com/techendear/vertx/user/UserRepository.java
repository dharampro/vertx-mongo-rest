package com.techendear.vertx.user;

import com.techendear.vertx.user.model.UserFetchResponse;
import com.techendear.vertx.user.model.UserRequest;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

public class UserRepository {

  private static final String USER_COLLECTION = "userCollection";
  private MongoClient mongoClient;

  public UserRepository(MongoClient mongoClient) {
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
