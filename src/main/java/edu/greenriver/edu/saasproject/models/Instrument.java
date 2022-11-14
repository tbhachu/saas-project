package edu.greenriver.edu.saasproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * This class builds an Instrument object to store into a list.
 *
 * @author Tarsem Bhachu
 * @version 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instrument
{
    @Id
    @GeneratedValue
    private UUID instrumentID;
    private String instrumentName;
    private String instrumentType;
    private String material;
    private boolean carryBag;

    /**
     * @param instrumentName Name of the instrument
     * @param instrumentType Type of instrument, such as string, percussion, etc.
     * @param material Material of the instrument
     * @param carryBag Boolean value indicates whether instrument includes carry bag
     */
    public Instrument(String instrumentName, String instrumentType, String material, boolean carryBag) {
        instrumentID = UUID.randomUUID();
        this.instrumentName = instrumentName;
        this.instrumentType = instrumentType;
        this.material = material;
        this.carryBag = carryBag;
    }

    /**
     * Getter method to retrieve the unique ID of the instrument
     *
     * @return Returns a unique ID of the instrument
     */
    public UUID getInstrumentID() {
        return instrumentID;
    }

    /**
     * Getter method to retrieve the name of the instrument
     *
     * @return Returns the name of the instrument
     */
    public String getInstrumentName() {
        return instrumentName;
    }

    /**
     * Setter method to set the name of the instrument
     *
     * @param instrumentName Enters the instrument's name
     */
    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    /**
     * Getter method to acquire the instrument type
     *
     * @return Returns the type of instrument
     */
    public String getInstrumentType() {
        return instrumentType;
    }

    /**
     * Setter method to set the type of instrument
     *
     * @param instrumentType Accepts the type of instrument.
     */
    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    /**
     * Getter method to retrieve the instrument material
     *
     * @return Returns the material of the instrument
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Setter method that sets the instrument's material
     *
     * @param material Accepts the material of the instrument
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * This method checks whether a carry bag is included for the instrument
     *
     * @return Returns boolean value true or false depending on if carry bag is included
     */
    public boolean isCarryBag() {
        return carryBag;
    }

    /**
     * Setter method for configuring whether an instrument has a carry bag
     *
     * @param carryBag Accepts a boolean value true of false
     */
    public void setCarryBag(boolean carryBag) {
        this.carryBag = carryBag;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "instrumentID=" + instrumentID +
                ", name='" + instrumentName + '\'' +
                ", type='" + instrumentType + '\'' +
                ", material='" + material + '\'' +
                ", carryBag=" + carryBag +
                '}';
    }
}
