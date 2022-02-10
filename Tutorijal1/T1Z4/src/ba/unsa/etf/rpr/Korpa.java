package ba.unsa.etf.rpr;

public class Korpa {

    private Artikl[] artikli;
    private int brojArtikala;

    public Korpa() {
        this.artikli = new Artikl[50];
        brojArtikala = 0;
    }

    public Artikl[] getArtikli() {
        return artikli;
    }

    public int getBrojArtikala() {
        return brojArtikala;
    }

    public boolean dodajArtikl(Artikl a) {
        if (brojArtikala == artikli.length) {
            return false;
        } else {
            artikli[brojArtikala] = a;
            brojArtikala++;
        }
        return true;
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
                throw new IllegalArgumentException("Prazana korpa!");
            } else {
                throw new IllegalArgumentException("Artikl s unesenim kodom ne postoji u KORPI!");
            }
        }

        return a;
    }

    public int dajUkupnuCijenuArtikala() {
        int ukupnaCijena = 0;
        for (int i = 0; i < brojArtikala; i++) {
            ukupnaCijena += artikli[i].getCijena();
        }
        return ukupnaCijena;
    }


}
