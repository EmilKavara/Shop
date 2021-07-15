/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.entity.facade;

import com.myshop.shop.app.entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kavara
 */
@Stateless
public class LoginBean implements LoginBeanLocal{
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager entityManager;
    
    @Override
    public User validateUsernamePassword(String username, String password) {
        try{
            return (User) entityManager.createNamedQuery("User.findByUsernamePassword")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        }catch(Exception exception){
            exception.printStackTrace();
            return null;
        }
    }
}
