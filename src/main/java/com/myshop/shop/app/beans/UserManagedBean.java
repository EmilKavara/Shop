/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.beans;

import com.myshop.shop.app.entity.User;
import com.myshop.shop.app.entity.facade.UserFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author kavara
 */
@Named(value="userManagedBean")
@RequestScoped
public class UserManagedBean implements Serializable{
    
    private List<User> _userList;
    @Inject
    UserFacadeLocal userFacadeLocal;
    @PostConstruct
    private void init(){
        
    _userList=userFacadeLocal.findAll();
        
    }
    
    public UserManagedBean(){
        
    }
    public List<User> getUserList() {
        return _userList;
    }

    public void setUserList(List<User> _userList) {
        this._userList = _userList;
    }
    
      
    
}
