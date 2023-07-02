package com.techendear.vertx.userworker;

import com.techendear.vertx.application.model.UserCreateResponse;
import com.techendear.vertx.application.model.UserFetchResponse;
import com.techendear.vertx.application.model.UserRequest;
import com.techendear.vertx.application.model.UserType;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;

public class WorkerHandler {
  private WorkerService workerService;
  private WebClient webClient;

  public WorkerHandler(WorkerService workerService, WebClient webClient) {
    this.workerService = workerService;
    this.webClient = webClient;
  }

  private UserCreateResponse createResponse(JsonObject jsonObject) {
    UserRequest resp = jsonObject.mapTo(UserRequest.class);
    UserCreateResponse response = new UserCreateResponse();
    response.setTaskId(resp.getTaskId());
    response.setUserType(resp.getUserType());
    return response;
  }

  public void createUser(Message<Object> userRequestMessage) {

    UserRequest request = JsonObject.mapFrom(userRequestMessage.body()).mapTo(UserRequest.class);
    if (request.getUserType().equals(UserType.INTERNAL)) {
      workerService.createUser(request).onComplete(response -> {
        if (response.succeeded()) {
          userRequestMessage.reply(response.result().toString());
        } else {
          response.cause();
        }
      });
    } else {
      webClient.get(80, "api.github.com", "/users/" + request.getUserName())
        .send().onComplete(result -> {
          if (result.succeeded()) {
            JsonObject resultJson = JsonObject.mapFrom(result.result().bodyAsJsonObject());
            String userName = resultJson.getString("login");
            String name = resultJson.getString("name");
            request.setUserName(userName);
            request.setName(name);
            workerService.createUser(request)
              .onComplete(response -> {
                if (response.succeeded()) {
                  userRequestMessage.reply(response.result().toString());
                } else {
                  response.cause();
                }
              });
          } else {
            result.cause();
          }
        });
    }
  }

  public void getUser(Message<Object> objectMessage) {
    String taskId = objectMessage.body().toString();
    workerService.getUser(taskId).onComplete(result -> {
      if (result.succeeded()) {
        objectMessage.reply(result.result());
      } else {
        result.cause();
      }
    });
  }

  private void createResponse(Vertx vertx) {


  }
}
