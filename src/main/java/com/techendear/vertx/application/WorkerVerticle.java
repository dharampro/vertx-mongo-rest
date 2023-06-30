package com.techendear.vertx.application;

import com.techendear.vertx.user.UserHandler;
import com.techendear.vertx.user.UserRepository;
import com.techendear.vertx.user.UserService;
import com.techendear.vertx.user.model.UserRequest;
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
      UserRepository repository = new UserRepository(MongoClient.createShared(vertx, jsonConfig.getJsonObject("db")));
      UserService service = new UserService(repository);
      UserHandler handler = new UserHandler(service, WebClient.create(vertx));
      Routers routers = new Routers(handler);
      vertx.createHttpServer()
        .requestHandler(routers.gerRouter(vertx))
        .listen(jsonConfig.getJsonObject("server").getInteger("port"));
      vertx.eventBus().consumer("create.user.EXTERNAL", res ->
        service.createUser(JsonObject.mapFrom(res.body()).mapTo(UserRequest.class))
      );
    });
  }

  private ConfigRetriever configuration() {
    ConfigRetrieverOptions options = new ConfigRetrieverOptions()
      .addStore(new ConfigStoreOptions().setType("file")
        .setConfig(new JsonObject().put("path", "config/application.json")));
    return ConfigRetriever.create(vertx, options);
  }
}
