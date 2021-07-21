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
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager entityManager;

    public UserFacade() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<User> findRange(int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
     
 @Override
    public void delete(int userId) {
        Query query=entityManager.createNamedQuery("User.findByUserId").setParameter("userId", userId);
        User user=(User)query.getSingleResult();
        if (entityManager.contains(user)) {
            entityManager.remove(user);
        } else {
            User user2 = entityManager.find(User.class, user.getUserId());
            if (user2 != null) {
                entityManager.remove(user2);
            }
        }
    }
    @Override
    public void save(String name,String lastName,String username) {
           User user=new User();
           user.setFirstName(name);
           user.setLastName(lastName);
           user.setAddress("address");
           user.setUsername(username);
           user.setPassword("password");
           user.setMobileNumber("223");
           user.setPrivilege("User");
           user.setAmount(200);
           user.setEmail("email");
           entityManager.persist(user);
    
}
       
            
    }

