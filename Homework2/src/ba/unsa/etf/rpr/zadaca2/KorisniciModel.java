package ba.unsa.etf.rpr.zadaca2;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KorisniciModel {
    private ObservableList<Korisnik> korisnici = FXCollections.observableArrayList();
    private SimpleObjectProperty<Korisnik> trenutniKorisnik = new SimpleObjectProperty<>();

    public KorisniciModel() {
    }

    public void napuni() {
        korisnici.add(new Korisnik("Vedran", "Ljubović", "vljubovic@etf.unsa.ba", "vedranlj", "test"));
        korisnici.add(new Korisnik("Amra", "Delić", "adelic@etf.unsa.ba", "amrad", "test"));
        korisnici.add(new Korisnik("Tarik", "Sijerčić", "tsijercic1@etf.unsa.ba", "tariks", "test"));
        korisnici.add(new Korisnik("Rijad", "Fejzić", "rfejzic1@etf.unsa.ba", "rijadf", "test"));
        trenutniKorisnik.set(null);
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

    public SimpleObjectProperty<Korisnik> trenutniKorisnikProperty() {
        return trenutniKorisnik;
    }

    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
        this.trenutniKorisnik.set(trenutniKorisnik);
    }

    public void setTrenutniKorisnik(int i) {
        this.trenutniKorisnik.set(korisnici.get(i));
    }

    public static boolean valdacijaImenaIliPrezimena(String naziv){
        if(naziv.length() < 3) return false;
        boolean validno = true;
        for(int i=0; i<naziv.length(); i++){
            if(!(naziv.charAt(i) >= 'a' && naziv.charAt(i) <= 'z')
                    && !(naziv.charAt(i) >= 'A' && naziv.charAt(i) <= 'Z')
                    && (naziv.charAt(i) != ' ') && (naziv.charAt(i) != '-')
                    && (naziv.charAt(i) != 'Č') && (naziv.charAt(i) != 'Ć')
                    && (naziv.charAt(i) != 'č') && (naziv.charAt(i) != 'ć')
                    && (naziv.charAt(i) != 'Đ') && (naziv.charAt(i) != 'đ')
                    && (naziv.charAt(i) != 'Ž') && (naziv.charAt(i) != 'ž')
                    && (naziv.charAt(i) != 'Š') && (naziv.charAt(i) != 'š')){
                validno = false;
                break;
            }
        }

        return validno;
    }

    public static  boolean valdacijaNadimka(String nadimak){
        if(nadimak.length() < 1 || nadimak.length() > 16) return false;
        if(!(nadimak.charAt(0) >= 'a' && nadimak.charAt(0) <= 'z')
                && !(nadimak.charAt(0) >= 'A' && nadimak.charAt(0) <= 'Z')
                && nadimak.charAt(0) != '_' && nadimak.charAt(0) != '$') return false;
        if(nadimak.equals("instanceof")) return false;

        boolean validan = true;

        for(int i=1; i<nadimak.length(); i++){
            if( (nadimak.charAt(i) == '+') || (nadimak.charAt(i) == '-')
                    || (nadimak.charAt(i) == '*') || (nadimak.charAt(i) == '/')
                    || (nadimak.charAt(i) == '%') || (nadimak.charAt(i) == '=')
                    || (nadimak.charAt(i) == '>') || (nadimak.charAt(i) == '<')
                    || (nadimak.charAt(i) == '&') || (nadimak.charAt(i) == '|')
                    || (nadimak.charAt(i) == '^') || (nadimak.charAt(i) == ' ')
                    || (nadimak.charAt(i) == ',') || (nadimak.charAt(i) == '@')
                    || (nadimak.charAt(i) == '?') || (nadimak.charAt(i) == '!')  ){
                validan = false;
                break;
            }
        }
        return validan;
    }

    public static  boolean valdacijaEmaila(String email){
        /*EmailValidator validator = EmailValidator.getInstance();
        validator.isValid(email)*/
        if(email.length() < 3) return false;
        boolean postojiSlovoPrijeEt = false, postojiEt = false, postojiSlovoPoslijeEt = false;
        int i=0;
        for ( i=0; i<email.length(); i++){
            if( (email.charAt(i) >= 'a' && email.charAt(i) <= 'z')
                    || (email.charAt(i) >= 'A' && email.charAt(i) <= 'Z')){
                    postojiSlovoPrijeEt = true;
                    break;
            }
        }

        while ( i<email.length() ){
            if( email.charAt(i) == '@'){
                postojiEt = true;
                break;
            }
            i++;
        }

        while ( i<email.length() ){
            if( (email.charAt(i) >= 'a' && email.charAt(i) <= 'z')
                    || (email.charAt(i) >= 'A' && email.charAt(i) <= 'Z')){
                postojiSlovoPoslijeEt = true;
                break;
            }
            i++;
        }

        return postojiSlovoPrijeEt && postojiEt && postojiSlovoPoslijeEt;
    }

}
