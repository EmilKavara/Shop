/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.entity.facade;

import com.myshop.shop.app.entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kavara
 */
@Stateless
public class RegisterBean implements RegisterBeanLocal{
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager entityManager;
    
     @Override
    public boolean register(String username, String firstName, String lastName,String password,String address, String phoneNum,String email) {
        try {
            Query query = entityManager.createNamedQuery("User.findByUsername").setParameter("username", username);
            List<User> users = (List<User>) query.getResultList();
            if(!users.isEmpty()){
                return false;
            }
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            
            
                    
                    
            user.setPassword(password);
            user.setUsername(username);
            user.setAddress(address);
            user.setEmail(email);
            user.setMobileNumber(phoneNum);
            user.setPrivilege("User");
            entityManager.persist(user);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        } 
    }
    
    
}
