/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author micka
 */
public class ProductEntity
{

    private int productId;
    private int manufacturerId;
    private String productCode;
    private float purchaseCost;
    private int quantity;
    private float markup;
    private String available;
    private String description;

    public ProductEntity()
    {

    }

    public void setAvailable(String available)
    {
        this.available = available;
    }

    public String getAvailable()
    {
        return available;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public void setManufacturerId(int manufacturerId)
    {
        this.manufacturerId = manufacturerId;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public void setPurchaseCost(float purchaseCost)
    {
        this.purchaseCost = purchaseCost;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public void setMarkup(float markup)
    {
        this.markup = markup;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getProductId()
    {
        return productId;
    }

    public int getManufacturerId()
    {
        return manufacturerId;
    }

    public String getProductCode()
    {
        return productCode;
    }

    public float getPurchaseCost()
    {
        return purchaseCost;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public float getMarkup()
    {
        return markup;
    }

    public String getDescription()
    {
        return description;
    }

}
