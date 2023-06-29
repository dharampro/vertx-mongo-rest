package com.techendear.vertx.user;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class UserRoutes {
   private UserHandler userHandler;
  public UserRoutes(UserHandler userHandler) {
    this.userHandler = userHandler;
  }
  public static Router getUserRouts(Vertx vertx) {
    Router router = Router.router(vertx);
    router.route("/user/*").handler(BodyHandler.create());
    router.post("/user").handler(userHandler::getUser);
  }
}
