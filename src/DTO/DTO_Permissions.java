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
public class DTO_Permissions{
    
    private int RoleId;
    private int ModelId;
    private boolean AllowInsert;
    private boolean AllowSelect;
    private boolean AllowUpdate;
    private boolean AllowDelete;

    /**
     * @return the RoleId
     */
    public int getRoleId() {
        return RoleId;
    }

    /**
     * @param RoleId the RoleId to set
     */
    public void setRoleId(int RoleId) {
        this.RoleId = RoleId;
    }

    /**
     * @return the ModelId
     */
    public int getModelId() {
        return ModelId;
    }

    /**
     * @param ModelId the ModelId to set
     */
    public void setModelId(int ModelId) {
        this.ModelId = ModelId;
    }

    /**
     * @return the AllowInsert
     */
    public boolean isAllowInsert() {
        return AllowInsert;
    }

    /**
     * @param AllowInsert the AllowInsert to set
     */
    public void setAllowInsert(boolean AllowInsert) {
        this.AllowInsert = AllowInsert;
    }

    /**
     * @return the AllowSelect
     */
    public boolean isAllowSelect() {
        return AllowSelect;
    }

    /**
     * @param AllowSelect the AllowSelect to set
     */
    public void setAllowSelect(boolean AllowSelect) {
        this.AllowSelect = AllowSelect;
    }

    /**
     * @return the AllowUpdate
     */
    public boolean isAllowUpdate() {
        return AllowUpdate;
    }

    /**
     * @param AllowUpdate the AllowUpdate to set
     */
    public void setAllowUpdate(boolean AllowUpdate) {
        this.AllowUpdate = AllowUpdate;
    }

    /**
     * @return the AllowDelete
     */
    public boolean isAllowDelete() {
        return AllowDelete;
    }

    /**
     * @param AllowDelete the AllowDelete to set
     */
    public void setAllowDelete(boolean AllowDelete) {
        this.AllowDelete = AllowDelete;
    }
    
}
