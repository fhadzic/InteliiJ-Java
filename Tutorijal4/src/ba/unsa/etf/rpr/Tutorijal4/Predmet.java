package ba.unsa.etf.rpr.Tutorijal4;


import java.util.Set;

public class Predmet {

    String naziv;
    int ECTS;
    int ocjena;
    boolean obavezni;
    Set<Student> aktivniStudenti;
    Set<Student> bivsiSrudenti;

    public Predmet(String naziv, int ECTS, boolean obavezni) {
        this.naziv = naziv;
        this.ECTS = ECTS;
        this.obavezni = obavezni;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getECTS() {
        return ECTS;
    }

    public boolean isObavezni() {
        return obavezni;
    }

    public String spisakAktivnihStudenata(){
        return null;
    }

    public String spisakBivsihStudenata(){
        return null;
    }

    public void upisiOcjenu(int ocjena, String index){

    }


    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
