package com.github.bragagustavo.dao;

import com.github.bragagustavo.shop.model.Order;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class OrderDAO {

    private EntityManager entityManager;

    public OrderDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void register(Order order) {
        this.entityManager.persist(order);
    }

    public BigDecimal totalSoldValue() {
        String jpql = "SELECT SUM (p.price) FROM Order p";
        return entityManager.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }


}
