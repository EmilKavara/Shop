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
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author kavara
 */
@Named("dtAddRowView")
@ViewScoped
public class AddRow implements Serializable{
    public AddRow(){
        
    }
     private List<Product> products1;
     private List<User> usersList;

    @Inject
    private ProductFacadeLocal list;
    
    @Inject
    private UserFacadeLocal users;

    @PostConstruct
    public void init() {
        products1 = list.findAll();
        usersList=users.findAll();
    }

    public List<Product> getProducts1() {
        return products1;
    }
    
    public List<User> getUsersList() {
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

    public void onAddNew() {
        // Add one new product to the table:
        Product newProduct = new Product();
        newProduct.setPrice(1D);
        newProduct.setQuantity(1);
        newProduct.setDescription("");
        newProduct.setName("");
        products1.add(newProduct);
        FacesMessage msg = new FacesMessage("New Product added", String.valueOf(newProduct.getProductId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onAddNew2() {
        // Add one new product to the table:
        User user=new User();
        user.setUsername("");
        user.setAddress("");
        user.setLastName("");
        user.setFirstName("");
        user.setPassword("");
        usersList.add(user);
        FacesMessage msg = new FacesMessage("New User added", String.valueOf(user.getUserId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
