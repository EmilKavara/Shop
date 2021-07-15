/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kavara
 */
@Named(value="logoutBean")
@SessionScoped
public class LogoutManagedBean implements Serializable{
    
    public String logout(){
        
		FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		Object loggedIn = session.getAttribute("loggedIn");
		if(loggedIn == null) {
                        return "login";
		} else {
                    
                    session.removeAttribute("loggedIn");
                    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                    
			return "index";
		}
	}
    
    public String isUserLoggedIn() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		Object loggedIn = session.getAttribute("loggedIn");
		if(loggedIn == null) {
			//ConfigurableNavigationHandler nav
			  // = (ConfigurableNavigationHandler)
			//		   facesContext.getApplication().getNavigationHandler();
 
			//nav.performNavigation("login");
                        return "Login";
		} else {
			return "Logout";
		}
	}
    }

