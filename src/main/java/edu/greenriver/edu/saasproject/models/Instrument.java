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
    private String name;
    private String type;
    private String material;
    private boolean carryBag;

    public Instrument(String name, String type, String material, boolean carryBag) {
        instrumentID = UUID.randomUUID();
        this.name = name;
        this.type = type;
        this.material = material;
        this.carryBag = carryBag;
    }

    public UUID getInstrumentID() {
        return instrumentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", material='" + material + '\'' +
                ", carryBag=" + carryBag +
                '}';
    }
}
