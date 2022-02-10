package ba.unsa.etf.rpr.tutorijal07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KorisniciModelTest {

    @Test
    void dodaj() {
        KorisniciModel km = new KorisniciModel();
        km.napuni();
        Korisnik k = new Korisnik("Ferid", "Hadžić", "fhadzic1@etf.unsa.ba", "fhadzic1", "12345");
        km.dodaj();
        assertEquals(km.getTrenutniKorisnik().getIme(), "");
    }
}