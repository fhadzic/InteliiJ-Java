package com.rpr.t5.util;

import com.rpr.t5.Korisnik;

import java.util.List;

public class Kredit {

    public static Double proracunKreditneSposobnosti(KreditnaSposobnost funkcija, Korisnik k1) {
        return funkcija.provjeri(k1.getRacun());
    }

    public static void ispisiSveKorisnikeBezPrekoracenja(List<Korisnik> korisnici) {
        System.out.println("\nKorisnici koji nemaju pravo na prekoracenje: ");
        korisnici.forEach(k -> {
            if (!k.getRacun().provjeriOdobrenjePrekoracenja()) {
                System.out.println(k.toString());
            }
        });
    }


    public static void bezPrekoracenja(List<Korisnik> korisnici) {
        System.out.println("\nKorisnici nisu u prekoracenju(stanje racuna nije manje od nule): ");
        korisnici.stream().filter(korisnik -> {
            return (korisnik.getRacun().getStanjeRacuna().doubleValue() >= 0);
        }).forEach(System.out::println);
    }


    public static void odobriPrekoracenje(List<Korisnik> korisnici) {
        korisnici.stream().filter( korisnik -> korisnik.getRacun().getStanjeRacuna().doubleValue() > 10000).forEach(
                korisnik -> {korisnik.getRacun().odobriPrekoracenje();}
        );
    }


}
