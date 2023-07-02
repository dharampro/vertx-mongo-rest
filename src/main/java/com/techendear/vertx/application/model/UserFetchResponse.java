package com.techendear.vertx.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.Objects;

@DataObject
public class UserFetchResponse {
  private String taskId;
  private String name;
  private String email;
  private String phone;
  private Boolean active;
  private UserType userType;
  private String userName;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserFetchResponse that = (UserFetchResponse) o;
    return Objects.equals(taskId, that.taskId) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(active, that.active) && userType == that.userType && Objects.equals(userName, that.userName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskId, name, email, phone, active, userType, userName);
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
      '}';
  }
}
