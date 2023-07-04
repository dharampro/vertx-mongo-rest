package com.techendear.vertx.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;
import java.util.Objects;

@DataObject
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product implements Serializable {

  private String taskId;
  private String code;
  private String name;
  private String description;

  public Product(JsonObject jsonObject) {
    this.taskId = jsonObject.getString("_id");
    this.code = jsonObject.getString("code");
    this.name = jsonObject.getString("name");
    this.description = jsonObject.getString("description");
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(taskId, product.taskId) && Objects.equals(code, product.code) && Objects.equals(name, product.name) && Objects.equals(description, product.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskId, code, name, description);
  }

  @Override
  public String toString() {
    return "Product{" +
      "taskId='" + taskId + '\'' +
      ", productCode='" + code + '\'' +
      ", productName='" + name + '\'' +
      ", description='" + description + '\'' +
      '}';
  }
}
