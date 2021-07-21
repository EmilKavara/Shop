/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.entity.facade;




import com.myshop.shop.app.entity.Item;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kavara
 */
@Local
public interface BuyBeanLocal {
    public boolean afterPurchase(Date shipperDate);
    public boolean purchase(List<Item> prodcut,int quantity,int shipperId);
}
