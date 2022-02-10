package ba.unsa.etf.rpr.Tutorijal4;

import java.util.List;
import java.util.Set;

public class Student {
    String ime;
    String prezime;
    String index;
    Set<Predmet> odslusaniPredmeti;
    Set<Predmet> aktivniPredmeti;

    public Student(String ime, String prezime, String index){

    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getIndex() {
        return index;
    }

    public Set<Predmet> getOdslusaniPredmeti() {
        return odslusaniPredmeti;
    }

    public Set<Predmet> getAktivniPredmeti() {
        return aktivniPredmeti;
    }


    public String spisakAktivnihPredmeta(){
        return null;
    }

    public String spisakOdslusanihPredmeta(){
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
