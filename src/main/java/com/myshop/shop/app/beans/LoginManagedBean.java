/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.beans;

import com.myshop.shop.app.entity.User;
import com.myshop.shop.app.entity.facade.LoginBeanLocal;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kavara
 */
@Named(value = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable{
    
    public LoginManagedBean(){
        
    }
    
    @Inject
    private LoginBeanLocal loginBeanLocal;
    
    private String username;
    private String password;
    
    public String validateUsernamePassword(){
        User user = loginBeanLocal.validateUsernamePassword(username, password);
        if(user !=null){
                   FacesContext facesContext = FacesContext.getCurrentInstance();
                   HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                   session.setAttribute("loggedIn",true);
                   String privilege=user.getPrivilege();
                   session.setAttribute("privilege",privilege);
                   int userId=user.getUserId();
                   session.setAttribute("userId", userId);
                   session.setAttribute("user",user);
                   if(user.getPrivilege().equals("User")){
                       return "laptops";
                   }else{
                       return "admin";
                   }
        }else{
            
            return "login";
        }
    }
    
    public void isLoggedIn() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		Object loggedIn = session.getAttribute("loggedIn");
                Object privilege=session.getAttribute("privilege");
		if(loggedIn == null || privilege.equals("User")) {
			ConfigurableNavigationHandler nav
			   = (ConfigurableNavigationHandler)
					   facesContext.getApplication().getNavigationHandler();
 
			nav.performNavigation("index");
                        //return "Login";
		} else {
			
		}
	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
