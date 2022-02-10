package ba.unsa.etf.rpr.tutorijal_3;

public class FiksniBroj extends TelefonskiBroj {

    private Grad grad;
    private String broj;


    public FiksniBroj(Grad grad, String broj) {
        this.grad = grad;
        this.broj = broj;
    }

    public Grad getGrad() {
        return grad;
    }

    public String getBroj() {
        return broj;
    }

    @Override
    public String ispisi() {
        return String.format("%s/%s", Grad.pozivniGrada(grad), broj);
    }


}
