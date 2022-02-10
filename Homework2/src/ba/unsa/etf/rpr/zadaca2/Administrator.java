package ba.unsa.etf.rpr.zadaca2;

public class Administrator extends Korisnik {

    public Administrator(String ime, String prezime, String email, String username, String password) {
        super(ime, prezime, email, username, password);
    }

    @Override
    public boolean checkPassword() {
        if(!super.checkPassword()) return false;

        boolean specialSymbol = false;
        for (int i=0; i<getPassword().length(); i++){
            if(!(getPassword().charAt(i) >= 'a' && getPassword().charAt(i) <= 'z')
                    && !(getPassword().charAt(i) >= 'A' && getPassword().charAt(i) <= 'Z')
                    && !(getPassword().charAt(i) >= '0' && getPassword().charAt(i) <= '9') ){
                specialSymbol=true;
                break;
            }
        }

        return specialSymbol;
    }

    @Override
    public void generisiPassword() {
        String lozinka = "";
        lozinka += getAlphaNumericString(7);
        lozinka += getSpecialChar();

        setPassword(lozinka);
    }

    private char getSpecialChar() {
        String specialCh = "!?=+-/*@{[()]}#$%^&";
        int index = (int) (specialCh.length() * Math.random());

        return  specialCh.charAt(index);
    }
}
