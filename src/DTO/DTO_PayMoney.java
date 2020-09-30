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
public class DTO_PayMoney {
    
    private int Id;
    private int CompanyOrderId;
    private int EmployeerId ;
    private int TimeStamp;
    private int SupplierId;
    private int PayMoney;
    private String Description;
    private String IsDelete;

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
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
     * @return the EmployeerId
     */
    public int getEmployeerId() {
        return EmployeerId;
    }

    /**
     * @param EmployeerId the EmployeerId to set
     */
    public void setEmployeerId(int EmployeerId) {
        this.EmployeerId = EmployeerId;
    }

    /**
     * @return the TimeStamp
     */
    public int getTimeStamp() {
        return TimeStamp;
    }

    /**
     * @param TimeStamp the TimeStamp to set
     */
    public void setTimeStamp(int TimeStamp) {
        this.TimeStamp = TimeStamp;
    }

    /**
     * @return the SupplierId
     */
    public int getSupplierId() {
        return SupplierId;
    }

    /**
     * @param SupplierId the SupplierId to set
     */
    public void setSupplierId(int SupplierId) {
        this.SupplierId = SupplierId;
    }

    /**
     * @return the PayMoney
     */
    public int getPayMoney() {
        return PayMoney;
    }

    /**
     * @param PayMoney the PayMoney to set
     */
    public void setPayMoney(int PayMoney) {
        this.PayMoney = PayMoney;
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

    /**
     * @return the IsDelete
     */
    public String getIsDelete() {
        return IsDelete;
    }

    /**
     * @param IsDelete the IsDelete to set
     */
    public void setIsDelete(String IsDelete) {
        this.IsDelete = IsDelete;
    }
}
