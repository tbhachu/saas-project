package edu.greenriver.edu.saasproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * This class builds a Raag object to store into a list.
 *
 * @author Tarsem Bhachu
 * @version 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Raag
{
    @Id
    @GeneratedValue
    private UUID raagID;

    private String raagName;
    private String thaat;
    private String raagTime;
    private String vaadi;


    /**
     * Constructor for initializing Raag object
     *
     * @param raagName Name of the Raag
     * @param thaat Name of the thaat
     * @param raagTime Time that Raag is sung
     * @param vaadi Raag vaadi
     */
    public Raag(String raagName, String thaat, String raagTime, String vaadi)
    {
        raagID = UUID.randomUUID();
        this.raagName = raagName;
        this.thaat = thaat;
        this.raagTime = raagTime;
        this.vaadi = vaadi;

    }

    /**
     * @return Returns a unique ID for a Raag
     */
    public UUID getRaagID() {
        return raagID;
    }

    /**
     * @return Returns the Raag name
     */
    public String getRaagName() {
        return raagName;
    }

    /**
     * @param raagName Accepts the Raag name to set
     */
    public void setRaagName(String raagName) {
        this.raagName = raagName;
    }

    /**
     * @return Returns the thaat
     */
    public String getThaat() {
        return thaat;
    }

    /**
     * @param thaat Accepts the name of the thaat
     */
    public void setThaat(String thaat) {
        this.thaat = thaat;
    }

    /**
     * @return Returns a string showing the time that a Raag is to be sung
     */
    public String getRaagTime() {
        return raagTime;
    }

    /**
     * @param raagTime Accepts the time Raag is sung as a String
     */
    public void setRaagTime(String raagTime) {
        this.raagTime = raagTime;
    }

    /**
     * @return Returns the name of the vaadi in String text
     */
    public String getVaadi() {
        return vaadi;
    }

    /**
     * @param vaadi Accepts the vaadi in String text
     */
    public void setVaadi(String vaadi) {
        this.vaadi = vaadi;
    }

    @Override
    public String toString() {
        return "Raag{" +
                "raagID=" + raagID +
                ", raagName='" + raagName + '\'' +
                ", thaat='" + thaat + '\'' +
                ", time='" + getRaagTime() + '\'' +
                ", vaadi='" + vaadi + '\'' +
                '}';
    }
}
