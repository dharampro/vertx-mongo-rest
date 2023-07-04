package com.techendear.vertx.application;

import com.google.inject.Singleton;
import com.techendear.vertx.user.ProductHandler;
import com.techendear.vertx.user.UserHandler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

@Singleton
public class Routers {

  private static final String ROOT_URL = "/ums/api";
  private static final String USER_URL = ROOT_URL + "/v1/user";
  private static final String USER_URL_GET = USER_URL + "/:taskId";
  private static final String PRODUCT_URL = ROOT_URL + "/v1/product";
  private static final String PRODUCT_URL_GET = PRODUCT_URL + "/:taskId";
  private static final String USER_PRODUCT_URL_GET = USER_URL + "/product/:taskId";
  private static final String USER_PRODUCT_SUBSCRIBE = USER_URL + "/product/subscribe";
  private UserHandler userHandler;
  private ProductHandler productHandler;

  public Routers(UserHandler userHandler, ProductHandler productHandler) {
    this.userHandler = userHandler;
    this.productHandler = productHandler;
  }

  public Router gerRouter(Vertx vertx) {
    Router router = Router.router(vertx);
    router.post(USER_URL).handler(userHandler::createUser);
    router.get(USER_URL_GET).handler(userHandler::fetchUser);
    router.post(PRODUCT_URL).handler(productHandler::createProduct);
    router.get(PRODUCT_URL_GET).handler(productHandler::fetchProduct);
    router.get(USER_PRODUCT_URL_GET).handler(userHandler::userProduct);
    router.post(USER_PRODUCT_SUBSCRIBE).handler(userHandler::userProductSubscribe);
    return router;
  }
}
