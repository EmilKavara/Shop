/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.entity.facade;

import com.myshop.shop.app.entity.Item;
import com.myshop.shop.app.entity.OrderInfo;
import com.myshop.shop.app.entity.OrderInfoPK;
import com.myshop.shop.app.entity.Orders;
import com.myshop.shop.app.entity.Payment;
import com.myshop.shop.app.entity.Product;
import com.myshop.shop.app.entity.Shippers;
import com.myshop.shop.app.entity.User;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kavara
 */
@Stateless
public class BuyBean implements BuyBeanLocal{
     @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager entityManager;
     
     
     
     @Override
     public boolean purchase(List<Item> items,int quantity,int shipperId){
         try{
             
             Query query = entityManager.createNamedQuery("Orders.findLast").setMaxResults(1);
            int orderId = (int) query.getSingleResult();
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
             Object userId = session.getAttribute("userId");
             Object objectUser = session.getAttribute("user");
            Query query2 = entityManager.createNamedQuery("User.findAmount").setParameter("userId", userId).setMaxResults(1);
            int amount = (int) query2.getSingleResult();
             User user=(User)objectUser;                         
             for(Item item:items){
                 OrderInfo orderInfo=new OrderInfo();
             OrderInfoPK orderInfoPK=new OrderInfoPK();
             orderInfoPK.setOrderId(orderId);
             Product product=item.getProduct();
             orderInfoPK.setProductId(product.getProductId());
             orderInfo.setOrderInfoPK(orderInfoPK);
             Double price=(product.getPrice())*quantity;
             orderInfo.setPrice(price);
             orderInfo.setDiscount(0);
             orderInfo.setNote("");
             orderInfo.setQuantity(quantity);
             Shippers shippers=new Shippers();
             shippers.setShipperId(shipperId);
             orderInfo.setShipperId(shippers);
             
            amount=(int)(amount-price);
            if(amount<0){
                return false;
            }else{
                entityManager.persist(orderInfo);
                user.setAmount(amount);
              entityManager.merge(user);
            }
            
             }                                          
             return true;
         }catch(Exception exception){
             exception.printStackTrace();
            return false;
         }
     }
      @Override
     public boolean afterPurchase(Date shipDate){
         try{
             Date orderDate=Date.valueOf(LocalDate.now());
             Integer orderNumber=orderNumberGenerator();
             User user=new User();
             FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		Object userID = session.getAttribute("userId");
             Integer userId=(Integer)userID;
             user.setUserId(userId);
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
