/**
 * @author :Gustavo
 * Date :08/03/2023
 * Time :08:42
 * Project Name :IntelliJ IDEA
 **/
package com.github.bragagustavo.tests;

import com.github.bragagustavo.dao.CategoryDAO;
import com.github.bragagustavo.dao.ClientDAO;
import com.github.bragagustavo.dao.ProductDAO;
import com.github.bragagustavo.shop.model.Category;
import com.github.bragagustavo.shop.model.Product;
import com.github.bragagustavo.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CriteriaTest {
    public static void main(String[] args) {
        dbPopulate();
        EntityManager entityManager = JPAUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(entityManager);
        productDAO.findByParameterWithCriteria("Playstation 5", null, null);

    }

    private static void dbPopulate() {
        Category informatics = new Category("INFORMATICS");
        Category cellphone = new Category("CELLPHONE");
        Category laptop = new Category("LAPTOP");

        Product ps5 = new Product("Playstation 5", "Videogame", new BigDecimal("5000"), informatics);
        Product iphone = new Product("Iphone 13 X", "High Performance", new BigDecimal("70000"),
                cellphone);
        Product macbook = new Product("Macbook Pro 16", "High Performance", new BigDecimal("30000"),
                informatics);

        EntityManager entityManager = JPAUtil.getEntityManager();

        ProductDAO productDAO = new ProductDAO(entityManager);
        ClientDAO clientDAO = new ClientDAO(entityManager);
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);

        entityManager.getTransaction().begin(); // Dispara a transação para fazer o insert no banco

        categoryDAO.register(informatics);
        categoryDAO.register(cellphone);
        categoryDAO.register(laptop);

        productDAO.register(ps5);
        productDAO.register(iphone);
        productDAO.register(macbook);

        Product product = productDAO.findById(1L);
        Product product1 = productDAO.findById(2L);
        Product product2 = productDAO.findById(3L);

        entityManager.getTransaction().commit();
    }
}
