package com.github.bragagustavo.dao;

import com.github.bragagustavo.shop.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProductDAO {

    private EntityManager entityManager;

    public ProductDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void register(Product product) {
        this.entityManager.persist(product);
    }

    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public List<Product> findAll() {
        String jpql = "SELECT p FROM Product p";
        return entityManager.createQuery(jpql, Product.class).getResultList();
    }

    public List<Product> findByName(String name) {
        String jpql = "SELECT p FROM Product p WHERE p.name = :name";
        return entityManager.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    /**
     * Exemplo do uso de uma named query (nao utilizada no restante da aplicação)
     **/
    public List<Product> findByCategoryName(String name) {
        return entityManager.createNamedQuery("Product.findByCategoryName", Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public BigDecimal findProductPriceByName(String name) {
        String jpql = "SELECT p.price FROM Product p WHERE p.name = :name";
        return entityManager.createQuery(jpql, BigDecimal.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Product> findByParameter(String name, BigDecimal price, LocalDate registerDate) {
        String jpql = " SELECT p FROM Product p WHERE 1=1";

        if (name != null && !name.trim().isEmpty()) {
            jpql = " AND p.name = :name ";
        }

        if (price != null) {
            jpql = "AND p.price = :price";
        }

        if (registerDate != null) {
            jpql = "AND p.registerDate = :registerDate";
        }

        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);

        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", name);
        }

        if (price != null) {
            query.setParameter("price", price);
        }

        if (registerDate != null) {
            query.setParameter("registerDate", registerDate);

        }
        return query.getResultList();
    }

    public List<Product> findByParameterWithCriteria(String name, BigDecimal price, LocalDate registerDate) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> from = query.from(Product.class);

        Predicate filters = builder.and();

        if (name != null && !name.trim().isEmpty()) {
            filters = builder.and(filters, builder.equal(from.get("name"), name));
        }

        if (price != null) {
            filters = builder.and(filters, builder.equal(from.get("price"), price));
        }

        if (registerDate != null) {
            filters = builder.and(filters, builder.equal(from.get("registerDate"), registerDate));
        }
        query.where(filters);
        return entityManager.createQuery(query).getResultList();
    }
}
