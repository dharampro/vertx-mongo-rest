package com.techendear.vertx.user.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.vertx.codegen.annotations.DataObject;

import java.io.Serializable;
import java.util.Objects;

@DataObject
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse implements Serializable {
  private String id;
  private String name;
  private String email;
  private String phone;
  private String address;
  private Boolean active;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserResponse that = (UserResponse) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(address, that.address) && Objects.equals(active, that.active);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, phone, address, active);
  }

  @Override
  public String toString() {
    return "UserResponse{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", email='" + email + '\'' +
      ", phone='" + phone + '\'' +
      ", address='" + address + '\'' +
      ", active=" + active +
      '}';
  }
}
