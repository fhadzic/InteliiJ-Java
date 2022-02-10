package ba.unsa.etf.rpr.tutorijal07;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KorisniciModel {
    private ObservableList<Korisnik> korisnici;
    private ObjectProperty<Korisnik> trenutniKorisnik;

    public KorisniciModel() {
        this.korisnici = FXCollections.observableArrayList();
        this.trenutniKorisnik = new SimpleObjectProperty<>();
    }

    public ObservableList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ObservableList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public Korisnik getTrenutniKorisnik() {
        return trenutniKorisnik.get();
    }

    public ObjectProperty<Korisnik> trenutniKorisnikProperty() {
        return trenutniKorisnik;
    }

    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
        this.trenutniKorisnik.set(trenutniKorisnik);
    }


    void napuni() {
        korisnici.add(new Korisnik("Vedran", "Ljubovic", "vljubovic@etf.unsa.ba", "vljubovic", "12345"));
        korisnici.add(new Korisnik("Amra", "Delić", "adelic@etf.unsa.ba", "adelic", "54321"));
        korisnici.add(new Korisnik("Tarik", "Sijerčić", "tsijercic@etf.unsa.ba", "tsijercic", "12543"));
        korisnici.add(new Korisnik("Rijad", "Fejzić", "vljubovic@etf.unsa.ba", "rfejzic", "54123"));
        trenutniKorisnik.setValue(null);
    }

    protected void dodaj() {
        Korisnik emptyKorisnik = new Korisnik();
        korisnici.add(emptyKorisnik);
        trenutniKorisnik.setValue(emptyKorisnik);
    }

}
