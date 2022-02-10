package ba.unsa.etf.rpr.tutorijal_3;

public class MobilniBroj extends MedunarodniBroj {

    private int mobilnaMreza;
    private String broj;

    public MobilniBroj(int mobilnaMreza, String broj) {
        super(String.format("0%d", mobilnaMreza), broj);
        this.mobilnaMreza = mobilnaMreza;
        this.broj = broj;
    }

}
