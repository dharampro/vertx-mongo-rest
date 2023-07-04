package com.techendear.vertx.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;
import java.util.Objects;

@DataObject
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse implements Serializable {
  @JsonProperty("_id")
  private String taskId;
  private String code;

  public ProductResponse(JsonObject jsonObject) {
    this.taskId = jsonObject.getString("_id");
    this.code = jsonObject.getString("name");
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductResponse that = (ProductResponse) o;
    return Objects.equals(taskId, that.taskId) && Objects.equals(code, that.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskId, code);
  }

  @Override
  public String toString() {
    return "ProductResponse{" +
      "taskId='" + taskId + '\'' +
      ", name='" + code + '\'' +
      '}';
  }
}
