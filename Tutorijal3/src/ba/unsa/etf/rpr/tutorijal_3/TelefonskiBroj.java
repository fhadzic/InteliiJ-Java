package ba.unsa.etf.rpr.tutorijal_3;

public abstract class TelefonskiBroj implements Comparable<TelefonskiBroj> {

    public abstract String ispisi();

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(TelefonskiBroj telBroj) {
        return (this.ispisi()).compareTo(telBroj.ispisi());
    }
}
