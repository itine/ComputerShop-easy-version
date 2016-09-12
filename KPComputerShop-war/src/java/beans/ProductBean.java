
package beans;


import dao.ProductDAOLocal; 
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model1.Product;

@Named(value = "productBean")
@SessionScoped
public class ProductBean implements Serializable{

    @EJB
    ProductDAOLocal productDAOLocal;
    
    private int productId;
    private String productName;
    private String category;
    private String description;
    private String manufacturer;
    private int price;
    
    private int localID;
    
    public List<Product> getProducts() throws Exception {
        return productDAOLocal.getAllProducts();

    }
    public List<Product> search() throws Exception {
        return productDAOLocal.searchProducts("sfsdf");
    }
    public String toInfoPage(int productId){
        setLocalID(productId);
        return "/productDetails.xhtml";
    }
    public List<Product> getProductDetails() throws Exception {
        return productDAOLocal.getProduct(localID);

    }
    public String orderTheProduct() throws Exception {
        productDAOLocal.addProduct(productName, category,description,manufacturer,price);
        
        return "/products.xhtml";
    }
    public String delete(int productId) throws Exception{
        productDAOLocal.delete(productId);
        return "/products.xhtml";
    }
    public String toEditPage(int productId){
        setLocalID(productId);
        return "/editProduct.xhtml";
    }
    public String edit() throws Exception{
        productDAOLocal.editProduct(productName, category, description, manufacturer, price, localID);
        return "/products.xhtml";
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price ;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the localID
     */
    public int getLocalID() {
        return localID;
    }

    /**
     * @param localID the localID to set
     */
    public void setLocalID(int localID) {
        this.localID = localID;
    }

  
}
