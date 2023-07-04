package com.techendear.vertx.worker;

import com.techendear.vertx.application.model.*;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;

import java.util.List;

public class WorkerHandler {
  private WorkerService workerService;
  private WebClient webClient;

  public WorkerHandler(WorkerService workerService, WebClient webClient) {
    this.workerService = workerService;
    this.webClient = webClient;
  }

  private UserCreateResponse createResponse(JsonObject jsonObject) {
    User resp = jsonObject.mapTo(User.class);
    UserCreateResponse response = new UserCreateResponse();
    response.setTaskId(resp.getTaskId());
    response.setUserType(resp.getUserType());
    return response;
  }

  public void createUser(Message<Object> userRequestMessage) {

    User request = JsonObject.mapFrom(userRequestMessage.body()).mapTo(User.class);
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
        User user = result.result().mapTo(User.class);
        List<String> productIds = user.getProductIds();

        objectMessage.reply(result.result());
      } else {
        result.cause();
      }
    });
  }

  public void createProduct(Message<Object> productRequestMessage) {
    Product request = new Product(JsonObject.mapFrom(productRequestMessage.body()));
    workerService.createProduct(request).onComplete(result -> {
      if (result.succeeded()) {
        productRequestMessage.reply(result.result().toString());
      } else {
        result.cause();
      }
    });
  }

  public void getProduct(Message<Object> objectMessage) {
    String taskId = objectMessage.body().toString();
    workerService.getProduct(taskId).onComplete(result -> {
      if (result.succeeded()) {
        objectMessage.reply(result.result());
      } else {
        result.cause();
      }
    });
  }

  public Future<JsonObject> userCourse(String taskId) {
    return null;
  }

  public Future<JsonObject> userProductSubscribe(UserProduct userProduct) {
    return null;
  }
}
