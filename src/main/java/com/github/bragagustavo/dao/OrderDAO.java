package com.github.bragagustavo.dao;

import com.github.bragagustavo.shop.model.Order;
import com.github.bragagustavo.vo.SellReportVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

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

    public List<SellReportVo> sellReport(){
        String jpql = "SELECT new com.github.bragagustavo.vo.SellReportVo("
                + " product.name, "
                + "SUM (item.ammount), "
                + "MAX (orders.date)) "
                + "FROM Order orders "
                + "JOIN orders.itens item "
                + "JOIN item.product product "
                + "GROUP BY product.name "
                + "ORDER BY item.ammount DESC";

        return entityManager.createQuery(jpql, SellReportVo.class)
                .getResultList();

    }

}
