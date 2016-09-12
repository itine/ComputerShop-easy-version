package dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model1.Users;
import model1.Orders;
import model1.Product;

@Stateless
public class OrderDAO implements OrderDAOLocal {

    @PersistenceContext(unitName = "KPComputerShop-ejbPU")
    private EntityManager em;
    
    @Override
    public boolean processOrder(int totalSum, String userId) throws Exception {
        Orders order = new Orders();
        order.setPaymentMethod("не оплачен");
        order.setTotalSum(totalSum);
        Users user = em.getReference(Users.class, userId);
        order.setUserName(user);
        order.setOrderStatus("заказ размещен");
        em.persist(order);
        em.flush();
        return true;
    }

    @Override
    public List<Orders> showOrdersInformation() throws Exception {
        Query query = em.createQuery("SELECT o FROM Orders o", Orders.class);
        return query.getResultList();
    }

    @Override
    public boolean confirmOrder(int orderId) throws Exception {
        Orders order = em.getReference(Orders.class, orderId);
        order.setOrderStatus("заказ подтвержден");
        em.merge(order);
        return true;
    }


    @Override
    public boolean cancelOrder(int orderId) throws Exception {
        Orders order = em.getReference(Orders.class, orderId);
        order.setOrderStatus("заказ отменен");
        em.merge(order);
        return true;
    }
    @Override
    public boolean deleteOrder(int orderId) throws Exception {
        em.remove(em.getReference(Orders.class, orderId));
        return true;
    }
    
    @Override
    public boolean payOrder(int orderId, String paymethod) throws Exception {
        Orders order = em.getReference(Orders.class, orderId);
        order.setOrderStatus("заказ оплачен");
        order.setPaymentMethod(paymethod);
        em.merge(order);
        return true;
    }

    @Override
    public List<Orders> showOrderInformation(String userName) throws Exception {
        List<Orders> ordList = new ArrayList<>();
        Query query = em.createQuery("SELECT o FROM Orders o WHERE o.user_name=?1", Orders.class);
        query.setParameter(1, userName);
        List<Orders> orders = (List<Orders>) query.getResultList();
        for (Orders ord : orders) {
            ordList.add(ord);
        }
        return ordList;
    }
}
