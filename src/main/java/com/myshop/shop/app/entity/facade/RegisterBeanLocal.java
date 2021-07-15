/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.entity.facade;

import javax.ejb.Local;

/**
 *
 * @author kavara
 */
@Local
public interface RegisterBeanLocal {
    public boolean register(String username,String firstName,String lastName,String password,String address,String phoneNum,String email);
}
