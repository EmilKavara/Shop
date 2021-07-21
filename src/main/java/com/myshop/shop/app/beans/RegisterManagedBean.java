/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.beans;

import com.myshop.shop.app.entity.facade.RegisterBeanLocal;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author kavara
 */
@Named(value = "registerManagedBean")
@SessionScoped

public class RegisterManagedBean implements Serializable{
    
    @Inject
    private RegisterBeanLocal registerBeanLocal;
    
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phoneNum;
    private String message = "";
    
    public RegisterManagedBean(){
        
    }
    
     public String register() {
         
        boolean userCreated = registerBeanLocal.register(username, firstName, lastName,password,address,phoneNum,email);
        if (userCreated) {
            return "index";
        } else {
            message = "Unijeto korisničko ime je već zauzeto ili imaš neku grešku";
            return "registration";
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
   
    
    
    
}
