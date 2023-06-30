package com.techendear.vertx.user;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.techendear.vertx.application.MainVertical;
import com.techendear.vertx.user.model.UserCreateResponse;
import com.techendear.vertx.user.model.UserFetchResponse;
import com.techendear.vertx.user.model.UserRequest;
import com.techendear.vertx.user.model.UserType;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class UserHandler {

  private final Logger log = LoggerFactory.getLogger(MainVertical.class);

  private UserService userService;
  private WebClient webClient;

  public UserHandler(UserService userService, WebClient webClient) {
    this.userService = userService;
    this.webClient = webClient;
  }

  public void createUser(RoutingContext rc) {
    rc.request().bodyHandler(body -> {
      JsonObject bodyReq = body.toJsonObject();
      log.info("Body {}", bodyReq);
      if (bodyReq.getString("userType").equalsIgnoreCase(UserType.INTERNAL.name())) {
        userService.createUser(new UserRequest(body.toJsonObject()))
          .onComplete(result -> {
            if (result.succeeded()) {
              log.info("Success: {}", result.result());
              System.out.println(result.result());
              rc.response()
                .setStatusCode(HttpResponseStatus.OK.code())
                .putHeader("Content-Type", "application/json")
                .end(new JsonObject().put("taskId", result.result()).encode());
            } else {
              log.error("Success: {}", result.cause());
              rc.response()
                .setStatusCode(HttpResponseStatus.OK.code())
                .putHeader("Content-Type", "application/json")
                .end(Json.encode(result.cause()));
            }
          });
      } else {
        webClient.get(80, "api.github.com", "/users/"+bodyReq.getString("userName"))
          .send().onComplete(result -> {

          if (result.succeeded()) {
            log.info("Success: {}", result.result());
            JsonObject resultJson = JsonObject.mapFrom(result.result().bodyAsJsonObject());
            String userName = resultJson.getString("login");
            String name = resultJson.getString("name");
            UserRequest request = new UserRequest(body.toJsonObject());
            request.setUserName(userName);
            request.setName(name);
            userService.createUser(request)
              .onComplete(response -> {
                if (response.succeeded()) {
                  log.info("Success: {}", response.result());
                  rc.response()
                    .setStatusCode(HttpResponseStatus.OK.code())
                    .putHeader("Content-Type", "application/json")
                    .end(new JsonObject().put("taskId", response.result()).encode());
                } else {
                  log.error("Success: {}", response.cause());
                  rc.response()
                    .setStatusCode(HttpResponseStatus.OK.code())
                    .putHeader("Content-Type", "application/json")
                    .end(Json.encode(response.cause()));
                }
              });
          } else {
            log.error("Success: {}", result.cause());
            rc.response()
              .setStatusCode(HttpResponseStatus.OK.code())
              .putHeader("Content-Type", "application/json")
              .end(Json.encode(result.cause()));
          }
        });
      }
    });
  }

  public void getUser(RoutingContext rc) {
    String taskId = rc.request().getParam("taskId");
    userService.getUser(taskId).onComplete(result -> {
      if (result.succeeded()) {
        rc.response()
          .setStatusCode(HttpResponseStatus.OK.code())
          .putHeader("Content-Type", "application/json")
          .end(Json.encode(new UserFetchResponse(result.result())));
      } else {
        rc.response()
          .setStatusCode(HttpResponseStatus.OK.code())
          .putHeader("Content-Type", "application/json")
          .end(Json.encode(result.cause()));
      }
    });
  }

  private UserCreateResponse createResponse(JsonObject jsonObject) {
    UserRequest resp = jsonObject.mapTo(UserRequest.class);
    UserCreateResponse response = new UserCreateResponse();
    response.setTaskId(resp.getTaskId());
    response.setUserType(resp.getUserType());
    return response;
  }
}
