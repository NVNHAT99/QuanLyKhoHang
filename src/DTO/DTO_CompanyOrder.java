/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class DTO_CompanyOrder {

    private int Id;
    private int SupplierId;
    private int EmployeeId;
    private Date TimeStamp;
    private float VAT;
    private float CK;
    private double TotalMoney ;
    private double HavePaid ;
    private double StillOwe ;
    private boolean Status ;// 0 la chua thanh toan het, 1 la da thanh toan
    private String Description ;
    private boolean IsDelete ;

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
     * @return the EmployeeId
     */
    public int getEmployeeId() {
        return EmployeeId;
    }

    /**
     * @param EmployeeId the EmployeeId to set
     */
    public void setEmployeeId(int EmployeeId) {
        this.EmployeeId = EmployeeId;
    }

    /**
     * @return the TimeStamp
     */
    public Date getTimeStamp() {
        return TimeStamp;
    }

    /**
     * @param TimeStamp the TimeStamp to set
     */
    public void setTimeStamp(Date TimeStamp) {
        this.TimeStamp = TimeStamp;
    }

    /**
     * @return the VAT
     */
    public float getVAT() {
        return VAT;
    }

    /**
     * @param VAT the VAT to set
     */
    public void setVAT(float VAT) {
        this.VAT = VAT;
    }

    /**
     * @return the CK
     */
    public float getCK() {
        return CK;
    }

    /**
     * @param CK the CK to set
     */
    public void setCK(float CK) {
        this.CK = CK;
    }

    /**
     * @return the TotalMoney
     */
    public double getTotalMoney() {
        return TotalMoney;
    }

    /**
     * @param TotalMoney the TotalMoney to set
     */
    public void setTotalMoney(double TotalMoney) {
        this.TotalMoney = TotalMoney;
    }

    /**
     * @return the HavePaid
     */
    public double getHavePaid() {
        return HavePaid;
    }

    /**
     * @param HavePaid the HavePaid to set
     */
    public void setHavePaid(double HavePaid) {
        this.HavePaid = HavePaid;
    }

    /**
     * @return the StillOwe
     */
    public double getStillOwe() {
        return StillOwe;
    }

    /**
     * @param StillOwe the StillOwe to set
     */
    public void setStillOwe(double StillOwe) {
        this.StillOwe = StillOwe;
    }

    /**
     * @return the Status
     */
    public boolean isStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(boolean Status) {
        this.Status = Status;
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
    public boolean getIsDelete() {
        return IsDelete;
    }

    /**
     * @param IsDelete the IsDelete to set
     */
    public void setIsDelete(boolean IsDelete) {
        this.IsDelete = IsDelete;
    }

    
}
