package beans;

import dao.OrderDAOLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model1.Orders;

@Named(value = "orderBean")
@SessionScoped
public class OrderBean implements Serializable {

    @EJB
    private OrderDAOLocal orderDAOLocal;

    private int orderId;
    private String userName;
    private int userId;
    private String paymethod = "не выбран";
    private Orders order;
    private int totalSum = 0;

    @PostConstruct
    public void initialize() {
        order = new Orders();
    }

    public String orderConfirm(String userName, int totalSum) throws Exception {
        this.orderDAOLocal.processOrder(totalSum, userName);
        return "/clientOrders.xhtml";
        
    }

    public String exit() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/products.xhtml";
    }


    public List<Orders> getOrdersInformation() throws Exception {
        return orderDAOLocal.showOrdersInformation();
    }

    public List<Orders> getOrderInformation(String userId) throws Exception {
        return orderDAOLocal.showOrderInformation(userId);
    }

    public String confirm(int orderID) throws Exception{
        boolean flag = orderDAOLocal.confirmOrder(orderID);
        if (flag == true) {
            return "/allOrders.xhtml";
        } else {
            return "/error.xhtml";
        }
    }


    public String delete(int orderID) throws Exception {
        boolean flag = orderDAOLocal.cancelOrder(orderID);
        if (flag == true) {
            return "/allOrders.xhtml";
        } else {
            return "/error.xhtml";
        }
    }

    public String payd(int orderID, String paymethod) throws Exception {

        boolean flag = orderDAOLocal.payOrder(orderID, this.paymethod);
        if (flag == true) {
            return "/thanks.xhtml";
        } else {
            return "/error.xhtml";
        }
    }
    public String deleteOrder(int orderID) throws Exception {
        boolean flag = orderDAOLocal.deleteOrder(orderID);
        if (flag == true) {
            return "/products.xhtml";
        } else {
            return "/error.xhtml";
        }
    }
    public Orders getOrders() {
        return order;
    }

    public void setOrders(Orders order) {
        this.order = order;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }


    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

}
