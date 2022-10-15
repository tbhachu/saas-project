package edu.greenriver.edu.saasproject.models;

public class StringInstrument
{
    private String name;
    private String material;
    private boolean instrumentCase;
    private boolean extraBow;
    private boolean rosin;
    private boolean extraStrings;

    public StringInstrument(String name, String material,
                            boolean instrumentCase, boolean extraBow,
                            boolean rosin, boolean extraStrings) {
        this.name = name;
        this.material = material;
        this.instrumentCase = instrumentCase;
        this.extraBow = extraBow;
        this.rosin = rosin;
        this.extraStrings = extraStrings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isInstrumentCase() {
        return instrumentCase;
    }

    public void setInstrumentCase(boolean instrumentCase) {
        this.instrumentCase = instrumentCase;
    }

    public boolean isExtraBow() {
        return extraBow;
    }

    public void setExtraBow(boolean extraBow) {
        this.extraBow = extraBow;
    }

    public boolean isRosin() {
        return rosin;
    }

    public void setRosin(boolean rosin) {
        this.rosin = rosin;
    }

    public boolean isExtraStrings() {
        return extraStrings;
    }

    public void setExtraStrings(boolean extraStrings) {
        this.extraStrings = extraStrings;
    }

    @Override
    public String toString() {
        return "StringInstrument{" +
                "name='" + name + '\'' +
                ", material='" + material + '\'' +
                ", instrumentCase=" + instrumentCase +
                ", extraBow=" + extraBow +
                ", rosin=" + rosin +
                ", extraStrings=" + extraStrings +
                '}';
    }
}
