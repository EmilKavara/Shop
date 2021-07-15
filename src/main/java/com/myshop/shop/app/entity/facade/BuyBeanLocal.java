/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.entity.facade;




import java.sql.Date;
import javax.ejb.Local;

/**
 *
 * @author kavara
 */
@Local
public interface BuyBeanLocal {
    public boolean purchase(Date shipperDate);
    public boolean afterPurchase(Integer orderId,Integer productId,Integer price,Integer quantity,Integer discount,String note,Integer shipperId);
}
