/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.entity.facade;

import com.myshop.shop.app.entity.Product;
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
public class ProductFacade extends AbstractFacade<Product> implements ProductFacadeLocal{
     
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager entityManager;

    public ProductFacade() {
        super(Product.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Product> findConsoles() {
        Query query = entityManager.createNamedQuery("Product.findByRange").setParameter("num1",6).setParameter("num2",10);
            List<Product> products = (List<Product>) query.getResultList();
            return products;
    }
    
    @Override
    public List<Product> findLaptops() {
        Query query = entityManager.createNamedQuery("Product.findByRange").setParameter("num1",1).setParameter("num2",5);
            List<Product> products = (List<Product>) query.getResultList();
            return products;
    }
    
    @Override
    public List<Product> findTV() {
        Query query = entityManager.createNamedQuery("Product.findByRange").setParameter("num1",11).setParameter("num2",15);
            List<Product> products = (List<Product>) query.getResultList();
            return products;
    }
    
    @Override
    public void delete(int productId) {
        Query query=entityManager.createNamedQuery("Product.findByProductId").setParameter("productId", productId);
        Product product=(Product)query.getSingleResult();
        if (entityManager.contains(product)) {
            entityManager.remove(product);
        } else {
            Product product2 = entityManager.find(Product.class, product.getProductId());
            if (product2 != null) {
                entityManager.remove(product2);
            }
        }
    }
    @Override
    public void save(String name,int quantity,double price) {
           Product product=new Product();
           product.setDescription("");
           product.setName(name);
           product.setImageName(name);
           product.setPrice(price);
           product.setQuantity(quantity);
           entityManager.persist(product);
    
}
}
