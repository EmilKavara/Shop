/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.beans;


import com.myshop.shop.app.entity.Product;
import com.myshop.shop.app.entity.User;
import com.myshop.shop.app.entity.facade.ProductFacadeLocal;
import com.myshop.shop.app.entity.facade.UserFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author kavara
 */
@Named("dtEdtiView")
@ViewScoped
public class EditRow implements Serializable{
     private List<Product> products;
     private List<User> usersList;
     
     @Inject
    private ProductFacadeLocal productFacadeLocal;
     
     @Inject
    private UserFacadeLocal userFacadeLocal;
             
             
     @PostConstruct
    public void init() {
        products =productFacadeLocal.findAll() ;
        usersList=userFacadeLocal.findAll();
    }
     public List<Product> getProducts1() {
        return products;
    }
     public List<User> getUsers() {
        return usersList;
    }
     
      public void onRowEdit(RowEditEvent<Product> event) {
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getProductId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
      
      public void onRowEdit2(RowEditEvent<User> event) {
        FacesMessage msg = new FacesMessage("User Edited", String.valueOf(event.getObject().getUserId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Product> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getProductId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowCancel2(RowEditEvent<User> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getUserId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

  public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
                if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
 
    
}
