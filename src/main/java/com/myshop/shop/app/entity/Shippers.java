/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kavar
 */
@Entity
@Table(name = "shippers")
@NamedQueries({
    @NamedQuery(name = "Shippers.findAll", query = "SELECT s FROM Shippers s"),
    @NamedQuery(name = "Shippers.findByShipperId", query = "SELECT s FROM Shippers s WHERE s.shipperId = :shipperId"),
    @NamedQuery(name = "Shippers.findByShipperName", query = "SELECT s FROM Shippers s WHERE s.shipperName = :shipperName"),
    @NamedQuery(name = "Shippers.findByPhoneNumber", query = "SELECT s FROM Shippers s WHERE s.phoneNumber = :phoneNumber")})
public class Shippers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "shipper_id")
    private Integer shipperId;
    @Size(max = 25)
    @Column(name = "shipper_name")
    private String shipperName;
    @Size(max = 12)
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany(mappedBy = "shipperId")
    private List<OrderInfo> orderInfoList;

    public Shippers() {
    }

    public Shippers(Integer shipperId) {
        this.shipperId = shipperId;
    }

    public Integer getShipperId() {
        return shipperId;
    }

    public void setShipperId(Integer shipperId) {
        this.shipperId = shipperId;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<OrderInfo> getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(List<OrderInfo> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shipperId != null ? shipperId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shippers)) {
            return false;
        }
        Shippers other = (Shippers) object;
        if ((this.shipperId == null && other.shipperId != null) || (this.shipperId != null && !this.shipperId.equals(other.shipperId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.myshop.shop.app.entity.Shippers[ shipperId=" + shipperId + " ]";
    }
    
}
