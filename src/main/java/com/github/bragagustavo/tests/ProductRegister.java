package com.github.bragagustavo.tests;

import com.github.bragagustavo.dao.CategoryDAO;
import com.github.bragagustavo.dao.ProductDAO;
import com.github.bragagustavo.shop.model.Category;
import com.github.bragagustavo.shop.model.Product;
import com.github.bragagustavo.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductRegister {
    public static void main(String[] args) {
        productRegister();
        Long id  = 1L;
        EntityManager entityManager = JPAUtil.getEntityManagerFactory();
        ProductDAO productDAO = new ProductDAO(entityManager);

        Product product = productDAO.findById(1L);
        System.out.println(product.getPrice());

        List<Product> all = productDAO.findAll();
        all.forEach( product1 -> System.out.println(product1.getName()));

        List<Product> todos = productDAO.findByCategoryName("INFORMATICS");

        BigDecimal productPriceByName = productDAO.findProductPriceByName("Macbook Pro 16");
        System.out.println(productPriceByName);
    }

    private static void productRegister() {
        Category informatics = new Category("INFORMATICS");
        Category cellphone = new Category("PHONES");
        Category laptops = new Category("LAPTOPS");

        Product ps5 = new Product("Playstation 5","Videogame", new BigDecimal("5000"), informatics);

        Product iphone = new Product("Iphone 13 Ultra X S ","High Performance",
                new BigDecimal("70000"),
                cellphone);

        Product macbook = new Product("Macbook Pro 16","High Performance", new BigDecimal("30000"),
                laptops);

        EntityManager entityManager = JPAUtil.getEntityManagerFactory();
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);
        ProductDAO productDAO = new ProductDAO(entityManager);

        entityManager.getTransaction().begin(); // Dispara a transação para fazer o insert no banco

        categoryDAO.register(informatics);
        categoryDAO.register(cellphone);
        categoryDAO.register(laptops);

        productDAO.register(ps5);
        productDAO.register(iphone);
        productDAO.register(macbook);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
