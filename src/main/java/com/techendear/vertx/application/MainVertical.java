package com.techendear.vertx.application;

import com.techendear.vertx.user.UserHandler;
import com.techendear.vertx.user.UserService;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MainVertical extends AbstractVerticle {

  private final Logger log = LoggerFactory.getLogger(MainVertical.class);

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    configuration().getConfig().onComplete(config -> {
      JsonObject jsonConfig = config.result();
      UserService service = new UserService(vertx);
      UserHandler handler = new UserHandler(service);
      Routers routers = new Routers(handler);
      vertx.createHttpServer()
        .requestHandler(routers.gerRouter(vertx))
        .listen(jsonConfig.getJsonObject("server").getInteger("port"));
    });
  }


  private ConfigRetriever configuration() {
    ConfigRetrieverOptions options = new ConfigRetrieverOptions()
      .addStore(new ConfigStoreOptions().setType("file")
        .setConfig(new JsonObject().put("path", "config/application.json")));
    return ConfigRetriever.create(vertx, options);
  }
}
