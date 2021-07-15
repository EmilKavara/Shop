/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.beans;

import com.myshop.shop.app.entity.facade.BuyBeanLocal;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
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

@Named(value="buyManagedBean")
@SessionScoped

public class BuyManagedBean implements Serializable{
    
    @Inject
    private BuyBeanLocal buyBeanLocal;
    
    private String shipper;
    
    public BuyManagedBean(){
        
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }
    
    
     public String purchase() {
         int shipperId;
         //shipper="2";
        switch (shipper) {
            case "1":
                shipperId=1;
                break;
            case "2":
                shipperId=4;
                break;
            default:
                shipperId=7;
                break;
        }
          Date shipperDate=shipperDate(shipperId);
        boolean userCreated = buyBeanLocal.purchase(shipperDate);
        if (userCreated) {
            return "index";
        } else {
            return "cart";
        }
    }
     
     private Date shipperDate(int shipperId){
        LocalDate localDate=LocalDate.now().plusDays(shipperId);
        Date shipperDate=Date.valueOf(localDate);
         return shipperDate;
     }
     public String checkRadio() {
		return "";
	}
     
     public void isLoggedIn() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		Object loggedIn = session.getAttribute("loggedIn");
		if(loggedIn == null) {
			ConfigurableNavigationHandler nav
			   = (ConfigurableNavigationHandler)
					   facesContext.getApplication().getNavigationHandler();
 
			nav.performNavigation("login");
                        //return "Login";
		} else {
			//return "Logout";
		}
	}	
     
     
    
}
