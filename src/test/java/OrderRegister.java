import com.github.bragagustavo.dao.CategoryDAO;
import com.github.bragagustavo.dao.ClientDAO;
import com.github.bragagustavo.dao.OrderDAO;
import com.github.bragagustavo.dao.ProductDAO;
import com.github.bragagustavo.shop.model.*;
import com.github.bragagustavo.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class OrderRegister {
    public static void main(String[] args) {

        dbPopulate();
        EntityManager entityManager = JPAUtil.getEntityManagerFactory();
        ProductDAO productDAO = new ProductDAO(entityManager);
        ClientDAO clientDAO = new ClientDAO(entityManager);

        Product product = productDAO.findById(1L);
        Client client = clientDAO.findById(1l);

        entityManager.getTransaction().begin();

        Order order = new Order(client);
        order.addItem(new OrderItem(2, order, product));

        OrderDAO orderDAO = new OrderDAO(entityManager);
        orderDAO.register(order);

        BigDecimal totalSoldValue = orderDAO.totalSoldValue();
        System.out.println("PEDIDO TOTAL VENDIDO FOI?: " + totalSoldValue);

        entityManager.getTransaction().commit();
    }

    private static void dbPopulate() {
        Category informatics = new Category("INFORMATICS");
        Product notebook = new Product("Macbook Pro 16", "Caro demais", new BigDecimal("30000"),
                informatics);
        Client client = new Client("Eusclepio", "17923");


        EntityManager entityManager = JPAUtil.getEntityManagerFactory();
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);
        ProductDAO productDAO = new ProductDAO(entityManager);
        ClientDAO clientDAO = new ClientDAO(entityManager);

        entityManager.getTransaction().begin(); // Dispara a transação para fazer o insert no banco

        categoryDAO.register(informatics);
        productDAO.register(notebook);
        clientDAO.register(client);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
