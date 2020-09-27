/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Administrator
 */
public class DTO_CompanyOrderDetail {

    private int CompanyOrderId;
    private int ProductId;
    private double Quantity;
    private String ProductUnit;
    private double Cost;
    private String Description;

    public DTO_CompanyOrderDetail( int ProductId, String Unit, double Quantity, double Cost, String Note) {
        this.ProductId = ProductId;
        this.ProductUnit = Unit;
        this.Quantity = Quantity;
        this.Cost = Cost;
        this.Description = Note;
    }
    public DTO_CompanyOrderDetail() {
        this.ProductId = -1;
        this.ProductUnit = "";
        this.Quantity = -1;
        this.Cost = -1;
        this.Description = "";
    }

    /**
     * @return the CompanyOrderId
     */
    public int getCompanyOrderId() {
        return CompanyOrderId;
    }

    /**
     * @param CompanyOrderId the CompanyOrderId to set
     */
    public void setCompanyOrderId(int CompanyOrderId) {
        this.CompanyOrderId = CompanyOrderId;
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

}
