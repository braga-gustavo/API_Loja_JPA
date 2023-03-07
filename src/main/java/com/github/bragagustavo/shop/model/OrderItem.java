package com.github.bragagustavo.shop.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unity_price")
    private BigDecimal unityPrice;
    
    private int ammount;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    public OrderItem(){

    }

    public OrderItem(int ammount, Order order, Product product) {
        this.ammount = ammount;
        this.order = order;
        this.unityPrice = product.getPrice();
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getUnityPrice() {
        return unityPrice;
    }

    public void setUnityPrice(BigDecimal unitPrice) {
        this.unityPrice = unitPrice;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return  unityPrice.multiply(new BigDecimal(ammount));
    }
}
