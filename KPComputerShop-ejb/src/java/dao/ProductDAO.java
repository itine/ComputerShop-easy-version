package dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model1.Product;

@Stateless
public class ProductDAO implements ProductDAOLocal {

    @PersistenceContext(unitName = "KPComputerShop-ejbPU")
    private EntityManager em;

    @Override
    public List<Product> getAllProducts() throws Exception {
        Query query = em.createQuery("SELECT p FROM Product p", Product.class);
        return query.getResultList();
    }

    @Override
    public void addProduct(String productName, String category, String description, String manufacturer, int price) throws Exception {
        Product product = new Product();
        product.setProductName(productName);
        product.setCategory(category);
        product.setDescription(description);
        product.setManufacturer(manufacturer);
        product.setPrice(price);
        em.persist(product);
    }

    @Override
    public boolean editProduct(String productName, String category, String description, String manufacturer, int price, int idProduct) {
        Product product = em.getReference(Product.class, idProduct);
        product.setProductName(productName);
        product.setCategory(category);
        product.setDescription(description);
        product.setManufacturer(manufacturer);
        product.setPrice(price);
        em.merge(product);
        return true;
    }

    @Override
    public boolean delete(int productId) throws Exception {
        em.remove(em.getReference(Product.class, productId));
        return true;
    }

    @Override
    public List<Product> searchProducts(String productName) throws Exception {
       List<Product> prodList = new ArrayList<>();
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.productName=?1", Product.class);
        query.setParameter(1, productName);
        List<Product> products = (List<Product>) query.getResultList();
        for (Product prod : products) {
            prodList.add(prod);
        }
        return prodList;
    
    }
    @Override
    public List<Product> getProduct(int productId) throws Exception {
       List<Product> prodList = new ArrayList<>();
        Query query = em.createQuery("SELECT p FROM Product p where p.productId=?1", Product.class);
        query.setParameter(1, productId);
        List<Product> products = (List<Product>) query.getResultList();
        for (Product prod : products) {
            prodList.add(prod);
        }
        return prodList;
    
    }
    
}
