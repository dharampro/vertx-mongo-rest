package com.techendear.vertx.application;

import com.google.inject.Singleton;
import com.techendear.vertx.user.UserHandler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

@Singleton
public class Routers {

  private static final String ROOT_URL = "/ums/api";
  private static final String USER_URL = ROOT_URL + "/v1/user";
  private static final String USER_URL_GET = USER_URL + "/:taskId";

  private UserHandler userHandler;
  public Routers(UserHandler userHandler) {
    this.userHandler = userHandler;
  }

  public Router gerRouter(Vertx vertx) {
    Router router = Router.router(vertx);
//    router.route(USER_URL + "/*").handler(BodyHandler.create());
    router.post(USER_URL).handler(userHandler::createUser);
    router.get(USER_URL_GET).handler(userHandler::fetchUser);
    return router;
  }
}
