package ba.unsa.etf.rpr.zadaca2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Korisnik {
    private SimpleStringProperty ime, prezime, email, username, password;
    private SimpleIntegerProperty godinaRodjenja;

    public Korisnik(String ime, String prezime, String email, String username, String password) {
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
        this.email = new SimpleStringProperty(email);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.godinaRodjenja = new SimpleIntegerProperty(2000);
    }

    @Override
    public String toString() {
        return prezime.get() + " " + ime.get();
    }

    public String getIme() {
        return ime.get();
    }

    public SimpleStringProperty imeProperty() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime.set(ime);
    }

    public String getPrezime() {
        return prezime.get();
    }

    public SimpleStringProperty prezimeProperty() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime.set(prezime);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public int getGodinaRodjenja() {
        return godinaRodjenja.get();
    }

    public SimpleIntegerProperty godinaRodjenjaProperty() {
        return godinaRodjenja;
    }

    public void setGodinaRodjenja(int godinaRodjenja) {
        this.godinaRodjenja.set(godinaRodjenja);
    }

    public boolean checkPassword() {
        if (password.get().length() < 3 /*|| !password.get().equals(passswordRepeat.get())*/) return false;
        boolean postojiVelikoSlovo = false, postojiBroj = false, postojiMaloSlovo = false;

        for (int i = 0; i < password.get().length(); i++) {
            char ch = password.get().charAt(i);
            if (Character.isLowerCase(ch)) {
                postojiMaloSlovo = true;
            }
            if (Character.isUpperCase(ch)) {
                postojiVelikoSlovo = true;
            }
            if (Character.isDigit(ch)) {
                postojiBroj = true;
            }

            if (postojiVelikoSlovo && postojiMaloSlovo && postojiBroj) return true;
        }
        return false;
    }

    private char convertCharacter(char c) {
        switch (c) {
            case 'ž':
                return 'z';
            case 'ć':
                return 'c';
            case 'č':
                return 'c';
            case 'đ':
                return 'd';
            case 'š':
                return 's';
        }
        return c;
    }

    public void generisiUserName() {
        String user = "", lastName = prezime.get().toLowerCase();
        if (ime.get().length() > 0) {
            user += convertCharacter(ime.get().toLowerCase().charAt(0));
        }
        for (int i = 0; i < lastName.length(); i++) {
            user += convertCharacter(lastName.charAt(i));
        }
        username.set(user);
    }


    public void generisiPassword() {
        String lozinka = getAlphaNumericString(8);

        setPassword(lozinka);
    }

    protected String getAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            if(i==1){
                String NumericString = "0123456789";
                int index = (int) (NumericString.length() * Math.random());
                sb.append( NumericString.charAt(index) );
                continue;
            }else if((n/2) == i){
                String AlphaUpperString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                int index = (int) (AlphaUpperString.length() * Math.random());
                sb.append(AlphaUpperString.charAt(index));
                continue;
            }else if((n-1) == i){
                String AlphaLowerString = "abcdefghijklmnopqrstuvxyz";
                int index = (int) (AlphaLowerString.length() * Math.random());
                sb.append(AlphaLowerString.charAt(index));
                break;
            }
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

}
