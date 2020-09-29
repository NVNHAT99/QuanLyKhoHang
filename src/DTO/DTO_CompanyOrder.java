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
    private double VAT;
    private double CK;
    private double TotalMoney;
    private double HavePaid;
    private double StillOwe;
    private boolean Status;// flase is this buid Still Owe
    private String Description;
    private boolean IsDelete;

    public DTO_CompanyOrder(int SupplierId, int EmployeeId, String DateCreate, double VAT, double CK, double TotalMoney, String Description) {
        this.Id = -1;
        this.SupplierId = SupplierId;
        this.EmployeeId = EmployeeId;
        this.TimeStamp = Date.valueOf(DateCreate);
        this.VAT = VAT;
        this.CK = CK;
        this.TotalMoney = TotalMoney;
        this.HavePaid = 0;
        this.StillOwe = TotalMoney;
        this.Status = false;
        this.Description = Description;
        this.IsDelete = false;
    }

    public DTO_CompanyOrder() {
        this.Id = -1;
        this.SupplierId = -1;
        this.EmployeeId = -1;
        this.TimeStamp = null;
        this.VAT = 0;
        this.TotalMoney = 0;
        this.HavePaid = 0;
        this.StillOwe = 0;
        this.Status = false;
        this.Description = "";
        this.IsDelete = false;
    }

    public DTO_CompanyOrder(int SupplierId, int EmployeeId, String TimeStamp, double VAT, double CK, double TotalMoney, double havePaid, String Description) {
        this.Id = -1;
        this.SupplierId = SupplierId;
        this.EmployeeId = EmployeeId;
        this.TimeStamp = Date.valueOf(TimeStamp);
        this.VAT = VAT;
        this.CK = CK;
        this.TotalMoney = TotalMoney;
        this.HavePaid = havePaid;
        this.StillOwe = TotalMoney;
        this.Status = false;
        this.Description = Description;
        this.IsDelete = false;
    }

    public DTO_CompanyOrder(int CompanyOrderId, int SupplierId, int EmployeeId, String DateCreate, double VAT, double CK, double TotalMoney, double HavePaid, String Description) {
        this.Id = CompanyOrderId;
        this.SupplierId = SupplierId;
        this.EmployeeId = EmployeeId;
        this.TimeStamp = Date.valueOf(DateCreate);
        this.VAT = VAT;
        this.CK = CK;
        this.TotalMoney = TotalMoney;
        this.HavePaid = HavePaid;
        this.StillOwe = TotalMoney;
        this.Status = false;
        this.Description = Description;
        this.IsDelete = false;
    }

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
    public double getVAT() {
        return VAT;
    }

    /**
     * @param VAT the VAT to set
     */
    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    /**
     * @return the CK
     */
    public double getCK() {
        return CK;
    }

    /**
     * @param CK the CK to set
     */
    public void setCK(double CK) {
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
