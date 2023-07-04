package com.techendear.vertx.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Objects;

@DataObject
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFetchResponse {
  private String taskId;
  private String name;
  private String email;
  private String phone;
  private Boolean active;
  private UserType userType;
  private String userName;
  private List<ProductResponse> products;

  public UserFetchResponse() {
  }

  public UserFetchResponse(JsonObject jsonObject) {
    this.taskId = jsonObject.getString("_id");
    this.name = jsonObject.getString("name");
    this.active = jsonObject.getBoolean("active");
    this.phone = jsonObject.getString("phone");
    this.email = jsonObject.getString("email");
    this.userName = jsonObject.getString("userName");
    this.userType = UserType.valueOf(jsonObject.getString("userType"));
    this.products = jsonObject.getJsonArray("products").getList();
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public List<ProductResponse> getProducts() {
    return products;
  }

  public void setProducts(List<ProductResponse> products) {
    this.products = products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserFetchResponse that = (UserFetchResponse) o;
    return Objects.equals(taskId, that.taskId) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(active, that.active) && userType == that.userType && Objects.equals(userName, that.userName) && Objects.equals(products, that.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskId, name, email, phone, active, userType, userName, products);
  }

  @Override
  public String toString() {
    return "UserFetchResponse{" +
      "taskId='" + taskId + '\'' +
      ", name='" + name + '\'' +
      ", email='" + email + '\'' +
      ", phone='" + phone + '\'' +
      ", active=" + active +
      ", userType=" + userType +
      ", userName='" + userName + '\'' +
      ", products=" + products +
      '}';
  }
}
