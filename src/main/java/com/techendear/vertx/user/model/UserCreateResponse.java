package com.techendear.vertx.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.codegen.annotations.DataObject;

import java.io.Serializable;
import java.util.Objects;

@DataObject
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreateResponse implements Serializable {

  @JsonProperty("_id")
  private String taskId;
  private UserType userType;

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserCreateResponse that = (UserCreateResponse) o;
    return Objects.equals(taskId, that.taskId) && userType == that.userType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskId, userType);
  }

  @Override
  public String toString() {
    return "UserCreateResponse{" +
      "taskId='" + taskId + '\'' +
      ", userType=" + userType +
      '}';
  }
}
