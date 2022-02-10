package com.rpr.t5;

public class Racun {
    private final Long brojRacuna;
    private final Osoba korisnikRacuna;
    private Double stanjeRacuna;
    private boolean odobrenjePrekoracenja;

    public Racun(Long brojRacuna, Osoba korisnikRacuna) {
        this.brojRacuna = brojRacuna;
        this.korisnikRacuna = korisnikRacuna;
        this.stanjeRacuna = 0d;
        this.odobrenjePrekoracenja = false;
    }


    public boolean izvrsiUplatu(Double uplata){
        if(uplata <= 0) return false;

        stanjeRacuna += uplata;
        return true;
    }

    public boolean izvrsiIsplatu(Double isplata){
        if(stanjeRacuna < isplata && !odobrenjePrekoracenja){
            return false;
        }
        stanjeRacuna -= isplata;
        return true;
    }

    public boolean provjeriOdobrenjePrekoracenja(){
        return  odobrenjePrekoracenja;
    }

    public void odobriPrekoracenje(){
        odobrenjePrekoracenja = true;
    }

    public String getBrojRacuna() {
        return String.valueOf(brojRacuna);
    }

    public Number getStanjeRacuna() {
        return stanjeRacuna;
    }
}
