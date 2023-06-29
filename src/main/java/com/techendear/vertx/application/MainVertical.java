package com.techendear.vertx.application;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVertical extends AbstractVerticle {

  private final Logger log = LoggerFactory.getLogger(MainVertical.class);

  public static void main(String[] args) {
    Vertx vertx1 = Vertx.vertx();
    vertx1.deployVerticle(MainVertical.class.getName());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    createHttpServer(vertx, startPromise, Routers.gerRouter(vertx));
  }

  private void createHttpServer(Vertx vertx, Promise<Void> promise, Router router) {
    configuration().getConfig().onComplete(config -> {
      JsonObject jsonConfig = config.result();
      log.info("configuration retried {}", jsonConfig);
      log.info("Starting server on port: {}", jsonConfig.getInteger("server.port"));
      vertx.createHttpServer()
        .requestHandler(router)
        .listen(jsonConfig.getInteger("server.port"));
      MongoClient.createShared(vertx, jsonConfig.getJsonObject("db"));
    });
  }

  private ConfigRetriever configuration() {
    ConfigStoreOptions store = new ConfigStoreOptions()
      .setType("directory")
      .setConfig(new JsonObject().put("path", "config")
        .put("filesets", new JsonArray()
          .add(new JsonObject().put("pattern", "dir/*json"))
          .add(new JsonObject().put("pattern", "dir/*.properties")
            .put("format", "properties"))
        ));
    ConfigRetrieverOptions options = new ConfigRetrieverOptions().addStore(store);
    return ConfigRetriever.create(vertx, options);
  }
}
