package model2;

import java.io.Serializable;
import model1.Product;

public class Basket implements Serializable{

    Product product;
    int productId;
    int quantity;

    public Basket(Product product) {
        this.product = product;
        this.productId=product.getProductId();
        quantity = 1;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }

    public int getTotal() {
        int amount = 0;
        amount = (this.getQuantity() * product.getPrice());
        return amount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
