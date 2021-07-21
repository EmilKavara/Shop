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
import javax.persistence.Query;
import org.mindrot.jbcrypt.BCrypt;

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
            Query query = entityManager.createNamedQuery("User.findByUsername").setParameter("username",username);
            String dbPassword = (String) query.getSingleResult();
            boolean isPassword=checkPass(password,dbPassword);
            if(isPassword){
                return (User) entityManager.createNamedQuery("User.findByUsername2")
                    .setParameter("username", username)                    
                    .getSingleResult();
            }else{
                return null;
            }
            
        }catch(Exception exception){
            exception.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean checkPass(String password,String dbPassword) {
		if (BCrypt.checkpw(password,dbPassword ))
			return true;
		else
			return false;
	}
}
