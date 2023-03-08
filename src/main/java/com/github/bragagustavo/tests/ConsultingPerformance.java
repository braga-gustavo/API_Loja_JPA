/**
 * @author :Gustavo
 * Date :08/03/2023
 * Time :08:42
 * Project Name :IntelliJ IDEA
 **/
package com.github.bragagustavo.tests;

import com.github.bragagustavo.dao.CategoryDAO;
import com.github.bragagustavo.dao.ClientDAO;
import com.github.bragagustavo.dao.OrderDAO;
import com.github.bragagustavo.dao.ProductDAO;
import com.github.bragagustavo.shop.model.*;
import com.github.bragagustavo.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class ConsultingPerformance {
    public static void main(String[] args) {
        dbPopulate();
        EntityManager entityManager = JPAUtil.getEntityManagerFactory();

//        Order order = entityManager.find(Order.class, 2L);
//        System.out.println(order.getItens().size());

        // Planned Query, usada junto com o (fetch = FetchType.LAZY) para consulta no banco apos o entity manager sem fechado
        OrderDAO orderDAO = new OrderDAO(entityManager);
        Order order = orderDAO.findOrderWithClient(1L);
        entityManager.close();
        System.out.println(order.getClient().getName());

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

        Client client = new Client("Page", "17923");
        Client client2 = new Client("Hendrix", "69696969");

        EntityManager entityManager = JPAUtil.getEntityManagerFactory();

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

        clientDAO.register(client2);
        clientDAO.register(client);

        Product product = productDAO.findById(1L);
        Product product1 = productDAO.findById(2L);
        Product product2 = productDAO.findById(3L);

        client = clientDAO.findById(1L);
        client2 = clientDAO.findById(2L);

        Order order = new Order(client);
        order.addItem(new OrderItem(2, order, product));
        order.addItem(new OrderItem(69, order, product1));

        Order order1 = new Order(client2);
        order1.addItem(new OrderItem(4, order, product2));

        OrderDAO orderDAO = new OrderDAO(entityManager);
        orderDAO.register(order);
        orderDAO.register(order1);

        entityManager.getTransaction().commit();
    }
}
