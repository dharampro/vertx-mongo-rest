package com.techendear.vertx.application;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class MainClass {

  public static void main(String[] args) {
    DeploymentOptions workerOpts = new DeploymentOptions()
      .setWorker(true)
      .setInstances(2)
      .setWorkerPoolSize(2);
    Vertx vertx1 = Vertx.vertx();
    vertx1.deployVerticle(MainVertical.class.getName());
    vertx1.deployVerticle(WorkerVerticle.class.getName(), workerOpts);
  }
}
