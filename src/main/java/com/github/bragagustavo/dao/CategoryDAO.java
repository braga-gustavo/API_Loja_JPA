package com.github.bragagustavo.dao;

import com.github.bragagustavo.shop.model.Category;

import javax.persistence.EntityManager;

public class CategoryDAO {
    private EntityManager entityManager;

    public CategoryDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public CategoryDAO() {
    }

    public void register(Category category) {
        this.entityManager.persist(category);
    }

    public void update(Category category) {
        this.entityManager.merge(category);
    }

    public void remove(Category category) {
        category = entityManager.merge(category);
        this.entityManager.remove(category);
    }
}
