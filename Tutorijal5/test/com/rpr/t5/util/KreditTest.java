package com.rpr.t5.util;

import com.rpr.t5.Banka;
import com.rpr.t5.Korisnik;
import com.rpr.t5.Racun;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KreditTest {

    @Test
    void proracunKreditneSposobnosti() {
        Banka banka = new Banka();
        Korisnik k1 = banka.kreirajNovogKorisnika("Niko", "Neznanović");
        banka.kreirajRacun(k1);
        k1.getRacun().izvrsiUplatu(5555d);
        KreditnaSposobnost funkcija = (Racun racun) -> {
            double stanje = racun.getStanjeRacuna().doubleValue();
            if (stanje > 1000) {
                return stanje / 2;
            } else {
                return 0d;
            }
        };
        assertEquals(2777.5, Kredit.proracunKreditneSposobnosti(funkcija, k1));
    }


    @Test
    void odobriPrekoracenje() {
        Banka banka = new Banka();
        Korisnik k1 = banka.kreirajNovogKorisnika("Niko", "Neznanović");
        banka.kreirajRacun(k1);
        k1.getRacun().izvrsiUplatu(15555d);
        Korisnik k2 = banka.kreirajNovogKorisnika("Petar", "Peric");
        banka.kreirajRacun(k2);
        k2.getRacun().izvrsiUplatu(13323d);
        Korisnik k3 = banka.kreirajNovogKorisnika("Safet", "Sušić");
        banka.kreirajRacun(k3);
        k3.getRacun().izvrsiUplatu(6555.25d);

        KreditnaSposobnost funkcija = (Racun racun) -> {
            double stanje = racun.getStanjeRacuna().doubleValue();
            if (stanje > 1000) {
                return stanje / 2;
            } else {
                return 0d;
            }
        };
        Kredit.odobriPrekoracenje(banka.getKorisnici());
        int brojKorisnikaBezPrekoracenja = (int)banka.getKorisnici().stream().filter(korisnik -> !korisnik.getRacun().provjeriOdobrenjePrekoracenja()).count();
        assertEquals(1, brojKorisnikaBezPrekoracenja);
    }
}