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
    @GeneratedValue
    private UUID raagID;

    private String raagName;
    private String thaat;
    private String raagTime;
    private String vaadi;


    public Raag(String raagName, String thaat, String raagTime, String vaadi)
    {
        raagID = UUID.randomUUID();
        this.raagName = raagName;
        this.thaat = thaat;
        this.raagTime = raagTime;
        this.vaadi = vaadi;

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

    public String getRaagTime() {
        return raagTime;
    }

    public void setRaagTime(String raagTime) {
        this.raagTime = raagTime;
    }

    public String getVaadi() {
        return vaadi;
    }

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
