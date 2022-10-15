package edu.greenriver.edu.saasproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Raag
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID raagID;

    private String raagName;
    private String thaat;
    private String time;
    private String aroh;
    private String avroh;

    public Raag(String raagName, String thaat, String time,
                String aroh, String avroh)
    {
        raagID = UUID.randomUUID();
        this.raagName = raagName;
        this.thaat = thaat;
        this.time = time;
        this.aroh = aroh;
        this.avroh = avroh;
    }

    public UUID getRaagID() {
        return raagID;
    }

    public String getRaagName() {
        return raagName;
    }

    public void setRaagName(String raagName) {
        this.raagName = raagName;
    }

    public String getThaat() {
        return thaat;
    }

    public void setThaat(String thaat) {
        this.thaat = thaat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAroh() {
        return aroh;
    }

    public void setAroh(String aroh) {
        this.aroh = aroh;
    }

    public String getAvroh() {
        return avroh;
    }

    public void setAvroh(String avroh) {
        this.avroh = avroh;
    }

    @Override
    public String toString() {
        return "Raag{" +
                "name='" + raagName + '\'' +
                ", thaat='" + thaat + '\'' +
                ", time='" + time + '\'' +
                ", aroh='" + aroh + '\'' +
                ", avroh='" + avroh + '\'' +
                '}';
    }
}
