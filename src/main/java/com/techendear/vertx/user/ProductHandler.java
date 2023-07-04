package com.techendear.vertx.user;

import com.google.common.base.Strings;
import com.techendear.vertx.application.model.Product;
import com.techendear.vertx.application.model.ProductResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.internal.StringUtil;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.io.BufferedReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ProductHandler {

  public void createProduct(RoutingContext rc) {
    rc.request().bodyHandler(handler -> {

      rc.vertx().eventBus().request("product.create.ops.bus", handler.toJsonObject()).onComplete(result -> {
        if (result.succeeded()) {
          rc.response()
            .setStatusCode(HttpResponseStatus.OK.code())
            .putHeader("Content-Type", "application/json")
            .end(Json.encode(JsonObject.of("taskId", result.result().body())
              .put("code", getHash(result.result().body().toString()))));
        } else {
          rc.response()
            .setStatusCode(HttpResponseStatus.OK.code())
            .putHeader("Content-Type", "application/json")
            .end(Json.encode(result.cause().getMessage()));
        }
      });
    });
  }

  public void fetchProduct(RoutingContext rc) {
    String taskId = rc.request().getParam("taskId");
    rc.vertx().eventBus().request("product.fetch.ops.bus", taskId).onComplete(result -> {
      if (result.succeeded()) {
        Product response = new Product(JsonObject.mapFrom(result.result().body()));
        response.setCode(getHash(response.getTaskId()));
        response.setTaskId(response.getTaskId());
        rc.response()
          .setStatusCode(HttpResponseStatus.OK.code())
          .putHeader("Content-Type", "application/json")
          .end(Json.encode(response));
      } else {
        rc.response()
          .setStatusCode(HttpResponseStatus.OK.code())
          .putHeader("Content-Type", "application/json")
          .end(Json.encode(result.cause().getMessage()));
      }
    });
  }

  private String getHash(String key) {
    StringBuffer sb = new StringBuffer();
    Integer flaag = 1;
    for (int i = 12; i > 0; i--) {
      if (flaag == 4 && i > 1) {
        sb.append(String.valueOf(key.charAt(i)).toUpperCase());
        sb.append("-");
        flaag = 1;
      } else {
        sb.append(String.valueOf(key.charAt(i)).toUpperCase());
        flaag++;
      }
    }
    return sb.toString();
  }
}
