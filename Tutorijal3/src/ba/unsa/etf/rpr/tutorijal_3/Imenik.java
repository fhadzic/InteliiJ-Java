package ba.unsa.etf.rpr.tutorijal_3;

import java.util.*;

public class Imenik {

    private Map<TelefonskiBroj, String> kontaktiIme = new HashMap<>();

    public void dodaj(String ime, TelefonskiBroj broj) {
        kontaktiIme.put(broj, ime);
    }

    public String dajBroj(String ime) {
        if (kontaktiIme.containsValue(ime)) {
            for (Map.Entry<TelefonskiBroj, String> par : kontaktiIme.entrySet()) {
                if (par.getValue().equals(ime)) {
                    return par.getKey().ispisi();
                }
            }
        }
        return null;
    }

    public String dajIme(TelefonskiBroj broj) {
        return kontaktiIme.getOrDefault(broj, null);
    }

    public String naSlovo(char s) {

        String result = "";
        int i = 1;
        for (Map.Entry<TelefonskiBroj, String> par : kontaktiIme.entrySet()) {
            if (par.getValue().startsWith(String.valueOf(s))) {
                result += String.format("%d. %s - %s\n", i, par.getValue(), par.getKey().ispisi());
                i++;
            }
        }
        return result;
    }

    public Set<String> izGrada(Grad g) {
        Set<String> izGrada = new TreeSet<>();
        for (Map.Entry<TelefonskiBroj, String> par : kontaktiIme.entrySet()) {
            if (par.getKey() instanceof FiksniBroj && ((FiksniBroj) par.getKey()).getGrad() == g) {
                izGrada.add(par.getValue());
            }
        }
        return izGrada;
    }

    public Set<TelefonskiBroj> izGradaBrojevi(Grad g) {
        Set<TelefonskiBroj> brojevi = new TreeSet<>();

        for (Map.Entry<TelefonskiBroj, String> par : kontaktiIme.entrySet()) {
            if (par.getKey() instanceof FiksniBroj && ((FiksniBroj) par.getKey()).getGrad() == g) {
                brojevi.add(par.getKey());
            }
        }

        return brojevi;
    }


    public int duzinaImenika(){
        return kontaktiIme.size();
    }

}
