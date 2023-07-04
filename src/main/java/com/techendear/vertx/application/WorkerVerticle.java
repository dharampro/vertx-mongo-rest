package com.techendear.vertx.application;

import com.techendear.vertx.worker.WorkerHandler;
import com.techendear.vertx.worker.WorkerRepository;
import com.techendear.vertx.worker.WorkerService;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.client.WebClient;

public class WorkerVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    configuration().getConfig().onComplete(config -> {
      JsonObject jsonConfig = config.result();
      WorkerRepository repository = new WorkerRepository(MongoClient.createShared(vertx, jsonConfig.getJsonObject("db")));
      WorkerService service = new WorkerService(repository);
      WorkerHandler handler = new WorkerHandler(service, WebClient.create(vertx));
      vertx.eventBus().consumer("user.create.ops.bus").handler(handler::createUser);
      vertx.eventBus().consumer("user.fetch.ops.bus").handler(handler::getUser);
      vertx.eventBus().consumer("product.create.ops.bus").handler(handler::createProduct);
      vertx.eventBus().consumer("product.fetch.ops.bus").handler(handler::getProduct);
      vertx.eventBus().consumer("user.product.fetch.ops.bus").handler(handler::getProduct);
      vertx.eventBus().consumer("user.product.subscribe.ops.bus").handler(handler::getProduct);
    });
  }

  private ConfigRetriever configuration() {
    ConfigRetrieverOptions options = new ConfigRetrieverOptions()
      .addStore(new ConfigStoreOptions().setType("file")
        .setConfig(new JsonObject().put("path", "config/application.json")));
    return ConfigRetriever.create(vertx, options);
  }
}
