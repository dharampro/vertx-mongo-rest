package com.techendear.vertx.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.vertx.codegen.annotations.DataObject;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@DataObject
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProduct implements Serializable {
  private String taskId;
  private String productId;
  private List<String> userId;

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public List<String> getUserId() {
    return userId;
  }

  public void setUserId(List<String> userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserProduct that = (UserProduct) o;
    return Objects.equals(taskId, that.taskId) && Objects.equals(productId, that.productId) && Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskId, productId, userId);
  }

  @Override
  public String toString() {
    return "UserProduct{" +
      "taskId='" + taskId + '\'' +
      ", productId='" + productId + '\'' +
      ", userId=" + userId +
      '}';
  }
}
