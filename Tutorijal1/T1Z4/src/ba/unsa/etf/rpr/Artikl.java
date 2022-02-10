package ba.unsa.etf.rpr;

public class Artikl {

    private String naziv;
    private double cijena;
    private String kod;


    public Artikl(String naziv, double cijena, String kod) {
        provjeraValidnostiCijene(cijena);
        this.naziv = naziv;
        this.cijena = cijena;
        this.kod = kod;
    }

    private void provjeraValidnostiCijene(double cijena) {
        if (cijena <= 0) {
            throw new IllegalArgumentException("Cijena mora biti veca od NULE!");
        }
    }

    public String getNaziv() {
        return naziv;
    }


    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        provjeraValidnostiCijene(cijena);
        this.cijena = cijena;
    }

    public String getKod() {
        return kod;
    }


    @Override
    public String toString() {
        String result = null;
        if (cijena - (int) cijena < 0.001) {
            result = String.format(naziv + ", " + "%d KM, " + kod + " kod.", (int) cijena);
        } else {
            result = String.format(naziv + ", " + "%.2f KM, " + kod + " kod.", cijena);
        }
        return result;
    }
}
