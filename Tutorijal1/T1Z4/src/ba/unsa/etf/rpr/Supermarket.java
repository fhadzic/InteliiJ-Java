package ba.unsa.etf.rpr;

public class Supermarket {

    private Artikl[] artikli;
    private int brojArtikala;

    public Supermarket() {
        this.artikli = new Artikl[1000];
        brojArtikala = 0;
    }

    public Artikl[] getArtikli() {
        return artikli;
    }

    public int getBrojArtikala() {
        return brojArtikala;
    }

    public void dodajArtikl(Artikl a) throws IllegalAccessException {
        if (brojArtikala == artikli.length) {
            throw new IllegalAccessException("Kapacitet Supermarketa popunjen!");
        } else {
            artikli[brojArtikala] = a;
            brojArtikala++;
        }
    }

    public Artikl izbaciArtiklSaKodom(String kod) {
        int i;
        Artikl a = null;
        for (i = 0; i < brojArtikala; i++) {
            if (artikli[i].getKod().equals(kod)) {
                a = new Artikl(artikli[i].getNaziv(), artikli[i].getCijena(), artikli[i].getKod());
                break;
            }
        }

        if (i != brojArtikala) {
            while (i < brojArtikala - 1) {
                artikli[i] = artikli[i + 1];
                i++;
            }
            artikli[i] = null;
            brojArtikala--;
        } else {
            if (brojArtikala == 0) {
                throw new IllegalArgumentException("Prazan superarket!");
            } else {
                throw new IllegalArgumentException("Artikl s unesenim kodom ne postoji u SUPERTARKETU!");
            }
        }
        return a;
    }


}
