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
public class DTO_CollectMoney {
    private int Id;
    private int CustomerOrderId;
    private int EmployeerId ;
    private int TimeStamp;
    private int CustomerId;
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
     * @return the CustomerId
     */
    public int getCustomerId() {
        return CustomerId;
    }

    /**
     * @param CustomerId the CustomerId to set
     */
    public void setCustomerId(int CustomerId) {
        this.CustomerId = CustomerId;
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
