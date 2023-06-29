package com.techendear.vertx.application;

import com.techendear.vertx.user.UserRoutes;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class Routers {
  public static Router gerRouter(Vertx vertx) {
      Router router = Router.router(vertx);
      router.mountSubRouter(RoutConstants.ROOT_URL, UserRoutes.getUserRouts(vertx));

      return router;
  }
}
