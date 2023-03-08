package com.github.bragagustavo.shop.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price = BigDecimal.ZERO;
    private LocalDate date = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY) // (fetch = FetchType.LAZY) faz com que as queries realizem
    // join apenas nas tabelas que sao utilizadas
    private Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> itens = new ArrayList<>();

    public Order(Client client) {
        this.client = client;
    }

    public void addItem(OrderItem item) {
        item.setOrder(this);
        this.itens.add(item);
        this.price = this.price.add(item.getPrice());
    }

    public Order(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<OrderItem> getItens() {
        return itens;
    }

    public void setItens(List<OrderItem> itens) {
        this.itens = itens;
    }
}
