package ba.unsa.etf.rpr.tutorijal8;

import java.io.Serializable;

public class Grad implements Serializable {
    private String naziv;
    private int brojStanovnika;
    private double[] temperature;
    private int brojMjerenja;

    public Grad() {
        temperature = new double[1000];
        brojMjerenja = 0;
        brojStanovnika = 0;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public double[] getTemperature() {
        return temperature;
    }

    public void setTemperature(double[] temperature, int brojMjerenja) {
        this.brojMjerenja = brojMjerenja;
        this.temperature = temperature;
    }

    public int getBrojMjerenja() {
        return brojMjerenja;
    }

    public void setBrojMjerenja(int brojMjerenja) {
        this.brojMjerenja = brojMjerenja;
    }

    @Override
    public String toString() {
        String result = getNaziv() + ", broj stanovnika " + getBrojStanovnika() + ", mjerenja: ";
        for(int i=0; i<getBrojMjerenja(); i++){
            result += temperature[i];
            if(i != getBrojMjerenja()-1) result+=", ";
            else result += ".\n";
        }
        return result ;
    }
}
