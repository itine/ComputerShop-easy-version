package dao;

import java.util.List;
import javax.ejb.Local;
import model1.Product;

@Local
public interface ProductDAOLocal {
    public List<Product> getAllProducts() throws Exception;
    public void addProduct(String productName, String category, String description, String manufacturer, int price) throws Exception;
    public boolean editProduct(String productName, String category, String description, String manufacturer, int price, int idProduct);
    public boolean delete(int productId) throws Exception;
    public List<Product> searchProducts(String productName) throws Exception;
    public List<Product> getProduct(int productId) throws Exception;
}
