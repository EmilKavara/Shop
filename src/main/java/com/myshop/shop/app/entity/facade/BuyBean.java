/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.entity.facade;

import com.myshop.shop.app.entity.OrderInfo;
import com.myshop.shop.app.entity.OrderInfoPK;
import com.myshop.shop.app.entity.Orders;
import com.myshop.shop.app.entity.Payment;
import com.myshop.shop.app.entity.User;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kavara
 */
@Stateless
public class BuyBean implements BuyBeanLocal{
     @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager entityManager;
     
     
     
     @Override
     public boolean afterPurchase(Integer orderId,Integer productId,Integer price,Integer quantity,Integer discount,String note,Integer shipperId){
         try{
             OrderInfo orderInfo=new OrderInfo();
             OrderInfoPK orderInfoPk=new OrderInfoPK();
             
             return true;
         }catch(Exception exception){
             exception.printStackTrace();
            return false;
         }
     }
      @Override
     public boolean purchase(Date shipDate){
         try{
             Date orderDate=Date.valueOf(LocalDate.now());
             Integer orderNumber=orderNumberGenerator();
             User user=new User();
             user.setUserId(2);
             Payment payment=new Payment();
             payment.setPaymentId(1);
             Orders orders=new Orders();
             orders.setOrderNumber(orderNumber);
             orders.setOrderDate(orderDate);
             orders.setShipDate(shipDate);
             orders.setPaymentId(payment);
             orders.setUserId(user);
             entityManager.persist(orders);
             
             return true;
         }catch(Exception exception){
             exception.printStackTrace();
            return false;
         }
     }
     private Integer orderNumberGenerator(){
         LocalDate ld=LocalDate.now();
         int year=ld.getYear();
         int month=ld.getMonthValue();
         int day=ld.getDayOfMonth();
         Random random=new Random();
         int randomNum=random.nextInt(100);
         String orderNum=year+""+month+""+day+""+randomNum;
         int orderNumber=Integer.parseInt(orderNum);
         return orderNumber;
     }
}
