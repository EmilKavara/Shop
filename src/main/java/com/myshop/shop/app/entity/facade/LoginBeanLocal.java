/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.entity.facade;

import com.myshop.shop.app.entity.User;

/**
 *
 * @author kavara
 */
public interface LoginBeanLocal {
    public User validateUsernamePassword(String username,String password);
    public boolean checkPass(String password,String dbPassword);
}
