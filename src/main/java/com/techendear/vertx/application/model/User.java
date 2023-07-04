package com.techendear.vertx.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@DataObject
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

  @JsonIgnore
  @JsonProperty("_id")
  private String taskId;
  private String name;
  private String email;
  private String phone;
  private Boolean active;
  private String userName;
  private UserType userType;
  private List<String> productIds;

  public User() {
  }

  public User(JsonObject jsonObject) {
    this.taskId = jsonObject.getString("_id");
    this.name = jsonObject.getString("name");
    this.active = jsonObject.getBoolean("active");
    this.phone = jsonObject.getString("phone");
    this.email = jsonObject.getString("email");
    this.userName = jsonObject.getString("userName");
    this.productIds = jsonObject.getJsonArray("productIds").getList();
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  public List<String> getProductIds() {
    return productIds;
  }

  public void setProductIds(List<String> productIds) {
    this.productIds = productIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(taskId, user.taskId) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone) && Objects.equals(active, user.active) && Objects.equals(userName, user.userName) && userType == user.userType && Objects.equals(productIds, user.productIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskId, name, email, phone, active, userName, userType, productIds);
  }

  @Override
  public String toString() {
    return "User{" +
      "taskId='" + taskId + '\'' +
      ", name='" + name + '\'' +
      ", email='" + email + '\'' +
      ", phone='" + phone + '\'' +
      ", active=" + active +
      ", userName='" + userName + '\'' +
      ", userType=" + userType +
      ", productIds=" + productIds +
      '}';
  }
}
