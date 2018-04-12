package model;

/**
 *
 * @author micka
 */
public class PurchaseEntity {
    // TODO : ajouter les autres propriétés

    private int orderNum;
    private int customerId;
    private int productId;
    private int quantity;
    private float shippingCost;
    private String salesDate;
    private String shippingDate;
    private String freightCompany;

    public PurchaseEntity() {

    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setShippingCost(float shippingCost) {
        this.shippingCost = shippingCost;
    }

    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public void setFreightCompany(String freightCompany) {
        this.freightCompany = freightCompany;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getShippingCost() {
        return shippingCost;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public String getFreightCompany() {
        return freightCompany;
    }
}
