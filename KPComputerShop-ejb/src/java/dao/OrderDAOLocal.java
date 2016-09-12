package dao;

import java.util.List;
import javax.ejb.Local;
import model1.Orders;

@Local
public interface OrderDAOLocal {

    public boolean processOrder(int totalSum, String userId) throws Exception;

    public List<Orders> showOrdersInformation() throws Exception;

    public boolean confirmOrder(int orderId) throws Exception;

    public boolean cancelOrder(int orderId) throws Exception;

    public boolean payOrder(int orderId, String paymethod) throws Exception;

    public boolean deleteOrder(int orderId) throws Exception;
    
    public List<Orders> showOrderInformation(String userName) throws Exception;
}
