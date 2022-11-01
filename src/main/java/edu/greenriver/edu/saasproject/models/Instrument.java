package edu.greenriver.edu.saasproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

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

    public Instrument(String instrumentName, String instrumentType, String material, boolean carryBag) {
        instrumentID = UUID.randomUUID();
        this.instrumentName = instrumentName;
        this.instrumentType = instrumentType;
        this.material = material;
        this.carryBag = carryBag;
    }

    public UUID getInstrumentID() {
        return instrumentID;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isCarryBag() {
        return carryBag;
    }

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
