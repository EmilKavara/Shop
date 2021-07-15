/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.beans;

import com.myshop.shop.app.entity.Product;
import com.myshop.shop.app.entity.facade.ProductFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author kavara
 */
@Named(value="productManagedBean")
@RequestScoped
public class ProductManagedBean implements Serializable{
    private List<Product> _productList;
     private List<Product> _productList2;
      private List<Product> _productList3;
    @Inject
    ProductFacadeLocal productFacadeLocal;
    @PostConstruct
    private void init(){
        
    _productList=productFacadeLocal.findLaptops();
     _productList2=productFacadeLocal.findConsoles();
      _productList3=productFacadeLocal.findTV();
        
    }

    public List<Product> getProductList2() {
        return _productList2;
    }

    public void setProductList2(List<Product> _productList2) {
        this._productList2 = _productList2;
    }

    public List<Product> getProductList3() {
        return _productList3;
    }

    public void setProductList3(List<Product> _productList3) {
        this._productList3 = _productList3;
    }
    
    public ProductManagedBean(){
        
    }
    public List<Product> getProductList() {
        return _productList;
    }

    public void setProductList(List<Product> _productList) {
        this._productList = _productList;
    }
}
