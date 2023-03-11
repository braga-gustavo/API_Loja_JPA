package com.github.bragagustavo.tests;

import com.github.bragagustavo.dao.CategoryDAO;
import com.github.bragagustavo.dao.ClientDAO;
import com.github.bragagustavo.dao.OrderDAO;
import com.github.bragagustavo.dao.ProductDAO;
import com.github.bragagustavo.shop.model.*;
import com.github.bragagustavo.util.JPAUtil;
import com.github.bragagustavo.vo.SellReportVo;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class OrderRegister {

    public static void main(String[] args) {

        dbPopulate();
        EntityManager entityManager = JPAUtil.getEntityManager();
        ProductDAO productDAO = new ProductDAO(entityManager);
        ClientDAO clientDAO = new ClientDAO(entityManager);

        Product product = productDAO.findById(1L);
        Product product1 = productDAO.findById(2L);
        Product product2 = productDAO.findById(3L);

        Client client = clientDAO.findById(1L);
        Client client2 = clientDAO.findById(2L);

        entityManager.getTransaction().begin();

        Order order = new Order(client);
        order.addItem(new OrderItem(2, order, product));
        order.addItem(new OrderItem(69, order, product1));

        Order order1 = new Order(client2);
        order1.addItem(new OrderItem(4, order, product2));

        OrderDAO orderDAO = new OrderDAO(entityManager);
        orderDAO.register(order);
        orderDAO.register(order1);

        BigDecimal totalSoldValue = orderDAO.totalSoldValue();
        System.out.println("PEDIDO TOTAL VENDIDO FOI?: " + totalSoldValue);

        List<SellReportVo> report = orderDAO.sellReport();
        {
            report.forEach(System.out::println);
        }

        entityManager.getTransaction().commit();
    }

    private static void dbPopulate() {
        Category informatics = new Category("INFORMATICS");
        Category cellphone = new Category("CELLPHONE");
        Category laptop = new Category("LAPTOP");

        Product ps5 = new Product("Playstation 5", "Videogame", new BigDecimal("5000"), informatics);

        Product iphone = new Product("Iphone 13 Ultra X S ", "High Performance",
                new BigDecimal("70000"), cellphone);

        Product macbook = new Product("Macbook Pro 16", "High Performance", new BigDecimal("30000"),
                informatics);

        Client client = new Client("Page", "17923");
        Client client2 = new Client("Hendrix", "69696969");

        EntityManager entityManager = JPAUtil.getEntityManager();
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);
        ProductDAO productDAO = new ProductDAO(entityManager);
        ClientDAO clientDAO = new ClientDAO(entityManager);

        entityManager.getTransaction().begin(); // Dispara a transação para fazer o insert no banco

        categoryDAO.register(informatics);
        categoryDAO.register(cellphone);
        categoryDAO.register(laptop);

        productDAO.register(ps5);
        productDAO.register(iphone);
        productDAO.register(macbook);

        clientDAO.register(client);
        clientDAO.register(client2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
