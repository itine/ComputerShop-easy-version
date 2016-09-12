package beans;

import dao.UserDAOLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import model1.Product;
import model2.Basket;


@Named(value = "basketBean")
@SessionScoped
public class BasketBean implements Serializable{

    @EJB
    private UserDAOLocal userDAOLocal;
    
    List<Basket> items;
    int numberOfItems;
    int total;

    @PostConstruct
    public void initialize(){
        items = new ArrayList<Basket>();
        numberOfItems = 0;
        total = 0;
    }
    public void addItem(Product product) throws Exception {

        boolean newItem = true;

        for (Basket basketItem : items) {

            if (basketItem.getProductId() == product.getProductId()) {
                newItem = false;
                basketItem.incrementQuantity();

            }
        }
        if (newItem) {
            Basket scItem = new Basket(product);
            items.add(scItem);
        }
    }

    public void deleteItem(Product product) throws Exception {

        for (Basket basketItem : items) {
            if (basketItem.getProduct().getProductId() == product.getProductId()) {

                basketItem.decrementQuantity();
            }
        }
    }

    public List<Basket> getItems() {
        return items;
    }

    public int getNumberOfItems() {

        numberOfItems = 0;

        for (Basket scItem : items) {

            numberOfItems += scItem.getQuantity();
        }

        return numberOfItems;
    }

    public int getSubtotal() {

        int amount = 0;

        for (Basket scItem : items) {

            Product product = (Product) scItem.getProduct();
            amount += (scItem.getQuantity() * product.getPrice());
        }

        return amount;
    }

    public void calculateTotal(String surcharge) {

        int amount = 0;
        int s = Integer.parseInt(surcharge);

        amount = this.getSubtotal();
        amount += s;

        total = amount;
    }

    public int getTotal() {
        return total;
    }

    public String exit() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
}