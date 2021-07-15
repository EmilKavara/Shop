/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.beans;


import com.myshop.shop.app.entity.Item;
import com.myshop.shop.app.entity.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author kavara
 */
@SessionScoped
@Named(value = "cartManagedBean")
public class CartManagedBean implements Serializable{
    private List<Item> items;    
	public CartManagedBean() {
		this.items = new ArrayList<Item>();
	}


                

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String buy(Product product) {
		int index = this.exists(product);
		if (index == -1) {
			this.items.add(new Item(product, 1));
		} else {
                    if(product.getQuantity()>=this.items.get(index).getQuantity()+1){
                        int quantity = this.items.get(index).getQuantity() + 1;
			this.items.get(index).setQuantity(quantity);
                    }else{
                        return "cart?faces-redirect=true";
                    }
			
		}
		return "cart?faces-redirect=true";
	}

	public String delete(Product product) {
		int index = this.exists(product);
		this.items.remove(index);
		return "cart?faces-redirect=true";
	}

	public double total() {
		double s = 0;
		for(Item item : this.items) {
			s += item.getProduct().getPrice().doubleValue() * item.getQuantity();
		}
		return s;
	}

	private int exists(Product product) {
		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i).getProduct().getProductId() == product.getProductId()) {
				return i;
			}
		}
		return -1;
	}
}
