package com.techendear.vertx.user;

import com.techendear.vertx.application.model.UserFetchResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class UserHandler {

  private UserService userService;

  public UserHandler(UserService userService) {
    this.userService = userService;
  }

  public void createUser(RoutingContext rc) {
    rc.request().bodyHandler(handler -> {
      rc.vertx().eventBus().request("user.create.ops.bus", handler.toJsonObject()).onComplete(result -> {
        if (result.succeeded()) {
          rc.response()
            .setStatusCode(HttpResponseStatus.OK.code())
            .putHeader("Content-Type", "application/json")
            .end(Json.encode(JsonObject.of("taskId", result.result().body())
              .put("userType", handler.toJsonObject().getValue("userType"))));
        } else {
          rc.response()
            .setStatusCode(HttpResponseStatus.OK.code())
            .putHeader("Content-Type", "application/json")
            .end(Json.encode(result.cause().getMessage()));
        }
      });
    });
  }

  public void fetchUser(RoutingContext rc) {
    String taskId = rc.request().getParam("taskId");
    rc.vertx().eventBus().request("user.fetch.ops.bus", taskId).onComplete(result -> {
      if (result.succeeded()) {
        UserFetchResponse response = new UserFetchResponse(JsonObject.mapFrom(result.result().body()));
        rc.response()
          .setStatusCode(HttpResponseStatus.OK.code())
          .putHeader("Content-Type", "application/json")
          .end(Json.encode(response));
      } else {
        rc.response()
          .setStatusCode(HttpResponseStatus.OK.code())
          .putHeader("Content-Type", "application/json")
          .end(Json.encode(result.cause().getMessage()));
      }
    });
  }
}
