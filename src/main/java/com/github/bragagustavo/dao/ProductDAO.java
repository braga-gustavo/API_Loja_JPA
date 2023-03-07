package com.github.bragagustavo.dao;

import com.github.bragagustavo.shop.model.Product;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductDAO {

    private EntityManager entityManager;

    public ProductDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void register(Product product){
        this.entityManager.persist(product);
    }

    public Product findById(Long id){
        return entityManager.find(Product.class, id);
    }

    public List<Product> findAll(){
       String jpql = "SELECT p FROM Product p";
       return entityManager.createQuery(jpql, Product.class).getResultList();
    }

    public List<Product> findByName(String name){
        String jpql = "SELECT p FROM Product p WHERE p.name = :name";
        return entityManager.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Product> findByCategoryName(String name){
        String jpql = "SELECT p FROM Product p WHERE p.category.name = :name";
        return entityManager.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public BigDecimal findProductPriceByName(String name){
        String jpql = "SELECT p.price FROM Product p WHERE p.name = :name";
        return entityManager.createQuery(jpql, BigDecimal.class)
                .setParameter("name", name)
                .getSingleResult();
    }


}
