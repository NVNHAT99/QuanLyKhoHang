/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Nhat
 */
public class DTO_CustomerOrderDetail {

    private int CustomerOrderId;
    private int ProductId;
    private double Quantity;
    private String ProductUnit;
    private double Cost;
    private String Description;

    public DTO_CustomerOrderDetail(int CustomerOrderId, int ProductId, String Unit, double Quantity, double Cost, String Note) {
        this.CustomerOrderId = CustomerOrderId;
        this.ProductId = ProductId;
        this.ProductUnit = Unit;
        this.Quantity = Quantity;
        this.Cost = Cost;
        this.Description = Note;
    }
    public DTO_CustomerOrderDetail() {
        this.ProductId = -1;
        this.ProductUnit = "";
        this.Quantity = -1;
        this.Cost = -1;
        this.Description = "";
    }

    /**
     * @return the CustomerOrderId
     */
    public int getCustomerOrderId() {
        return CustomerOrderId;
    }

    /**
     * @param CustomerOrderId the CustomerOrderId to set
     */
    public void setCustomerOrderId(int CustomerOrderId) {
        this.CustomerOrderId = CustomerOrderId;
    }

    /**
     * @return the ProductId
     */
    public int getProductId() {
        return ProductId;
    }

    /**
     * @param ProductId the ProductId to set
     */
    public void setProductId(int ProductId) {
        this.ProductId = ProductId;
    }

    /**
     * @return the Quantity
     */
    public double getQuantity() {
        return Quantity;
    }

    /**
     * @param Quantity the Quantity to set
     */
    public void setQuantity(double Quantity) {
        this.Quantity = Quantity;
    }

    /**
     * @return the ProductUnit
     */
    public String getProductUnit() {
        return ProductUnit;
    }

    /**
     * @param ProductUnit the ProductUnit to set
     */
    public void setProductUnit(String ProductUnit) {
        this.ProductUnit = ProductUnit;
    }

    /**
     * @return the Cost
     */
    public double getCost() {
        return Cost;
    }

    /**
     * @param Cost the Cost to set
     */
    public void setCost(double Cost) {
        this.Cost = Cost;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final DTO_CustomerOrderDetail other = (DTO_CustomerOrderDetail) obj;
        if ((this.CustomerOrderId != other.getCustomerOrderId()) || (this.Description != other.getDescription())
                || (this.ProductId != other.getProductId())
                || (this.ProductUnit != other.getProductUnit())
                || (this.Quantity != other.getQuantity())
                || (this.Cost != other.getCost())) {
            return false;

        }

        return true;
    }
    
    
}
