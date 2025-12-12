package com.lovedays.kings_cryptos.Dtos;

import java.util.HashSet;
import java.util.Set;

import com.lovedays.kings_cryptos.Model.Role;

public class ApplicationUserDto {
    private long id;
    private String userName;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();


    public ApplicationUserDto(){}
    
    public ApplicationUserDto(long id, String userName, String email, String password, Set<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return email;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
  

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

      @Override
    public String toString() {
        return "ApplicationUserDto [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password
                + ", roles=" + roles + "]";
    }
    

    
}