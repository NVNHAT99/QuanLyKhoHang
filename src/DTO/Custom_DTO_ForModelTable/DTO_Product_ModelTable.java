/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.Custom_DTO_ForModelTable;

import ultils.CustomCombo;

/**
 *
 * @author Administrator
 */
public class DTO_Product_ModelTable {

    private int Id;
    private String Name;
    private float Price;
    private CustomCombo supplier;
    private CustomCombo category;
    private String Unit;
    private int UnitsInStock;
    private String ImagePath;

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
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Price
     */
    public float getPrice() {
        return Price;
    }

    /**
     * @param Price the Price to set
     */
    public void setPrice(float Price) {
        this.Price = Price;
    }

    /**
     * @return the supplier
     */
    public CustomCombo getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(CustomCombo supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the category
     */
    public CustomCombo getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(CustomCombo category) {
        this.category = category;
    }

    /**
     * @return the Unit
     */
    public String getUnit() {
        return Unit;
    }

    /**
     * @param Unit the Unit to set
     */
    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    /**
     * @return the UnitsInStock
     */
    public int getUnitsInStock() {
        return UnitsInStock;
    }

    /**
     * @param UnitsInStock the UnitsInStock to set
     */
    public void setUnitsInStock(int UnitsInStock) {
        this.UnitsInStock = UnitsInStock;
    }

    /**
     * @return the ImagePath
     */
    public String getImagePath() {
        return ImagePath;
    }

    /**
     * @param ImagePath the ImagePath to set
     */
    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }

}
