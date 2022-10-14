package edu.greenriver.edu.saasproject.models;

public class Raag
{
    private String name;
    private String thaat;
    private String time;
    private String aroh;
    private String avroh;

    public Raag(String name, String thaat, String time,
                String aroh, String avroh) {
        this.name = name;
        this.thaat = thaat;
        this.time = time;
        this.aroh = aroh;
        this.avroh = avroh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                ", thaat='" + thaat + '\'' +
                ", time='" + time + '\'' +
                ", aroh='" + aroh + '\'' +
                ", avroh='" + avroh + '\'' +
                '}';
    }
}
