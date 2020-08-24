/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultils;

/**
 *
 * @author Nhat
 */
public class CustomCombo {

    int ID;
    String label;

    public CustomCombo(int id, String label) {
        this.ID = id;
        this.label = label;
    }


    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
    @Override
    public String toString() {
        return label;
    }
}
