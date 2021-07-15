/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author kavar
 */
@Entity
@Table(name = "order_info")
@NamedQueries({
    @NamedQuery(name = "OrderInfo.findAll", query = "SELECT o FROM OrderInfo o"),
    @NamedQuery(name = "OrderInfo.findByOrderId", query = "SELECT o FROM OrderInfo o WHERE o.orderInfoPK.orderId = :orderId"),
    @NamedQuery(name = "OrderInfo.findByProductId", query = "SELECT o FROM OrderInfo o WHERE o.orderInfoPK.productId = :productId"),
    @NamedQuery(name = "OrderInfo.findByPrice", query = "SELECT o FROM OrderInfo o WHERE o.price = :price"),
    @NamedQuery(name = "OrderInfo.findByQuantity", query = "SELECT o FROM OrderInfo o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "OrderInfo.findByDiscount", query = "SELECT o FROM OrderInfo o WHERE o.discount = :discount"),
    @NamedQuery(name = "OrderInfo.findByNote", query = "SELECT o FROM OrderInfo o WHERE o.note = :note")})
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderInfoPK orderInfoPK;
    @Column(name = "price")
    private Integer price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "discount")
    private Integer discount;
    @Size(max = 50)
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "shipper_id", referencedColumnName = "shipper_id")
    @ManyToOne
    private Shippers shipperId;

    public OrderInfo() {
    }

    public OrderInfo(OrderInfoPK orderInfoPK) {
        this.orderInfoPK = orderInfoPK;
    }

    public OrderInfo(int orderId, int productId) {
        this.orderInfoPK = new OrderInfoPK(orderId, productId);
    }

    public OrderInfoPK getOrderInfoPK() {
        return orderInfoPK;
    }

    public void setOrderInfoPK(OrderInfoPK orderInfoPK) {
        this.orderInfoPK = orderInfoPK;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Shippers getShipperId() {
        return shipperId;
    }

    public void setShipperId(Shippers shipperId) {
        this.shipperId = shipperId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderInfoPK != null ? orderInfoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderInfo)) {
            return false;
        }
        OrderInfo other = (OrderInfo) object;
        if ((this.orderInfoPK == null && other.orderInfoPK != null) || (this.orderInfoPK != null && !this.orderInfoPK.equals(other.orderInfoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.myshop.shop.app.entity.OrderInfo[ orderInfoPK=" + orderInfoPK + " ]";
    }
    
}
