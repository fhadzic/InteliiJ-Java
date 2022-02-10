package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double pocetak;
    private double kraj;
    private boolean daLiPripadaPocetak;
    private boolean daLiPripadaKraj;


    public Interval(double pocetak, double kraj, boolean daLiPripadaPocetak, boolean daLiPripadaKraj) {

        if (pocetak > kraj) throw new IllegalArgumentException("Početna tačka, veća od krajnje!");

        this.pocetak = pocetak;
        this.kraj = kraj;
        this.daLiPripadaPocetak = daLiPripadaPocetak;
        this.daLiPripadaKraj = daLiPripadaKraj;

    }

    public Interval() {
        this.pocetak = 0;
        this.kraj = 0;
        this.daLiPripadaPocetak = false;
        this.daLiPripadaKraj = false;
    }

    public boolean isNull() {
        return (pocetak == 0 && kraj == 0 && !daLiPripadaPocetak && !daLiPripadaKraj);
    }


    public boolean isIn(double v) {
        return (v - pocetak > 0.000001 || (v - pocetak == 0 && daLiPripadaPocetak)) && (kraj - v > 0.000001 || (kraj - v == 0 && daLiPripadaKraj));
    }


    public Interval intersect(Interval i2) {

        if (pocetak < i2.pocetak && kraj < i2.kraj) {
            return new Interval(i2.pocetak, kraj, i2.daLiPripadaPocetak, daLiPripadaKraj);
        } else if (pocetak > i2.pocetak && kraj > i2.kraj) {
            return new Interval(pocetak, i2.kraj, daLiPripadaPocetak, i2.daLiPripadaKraj);
        } else if (pocetak < i2.pocetak && kraj > i2.kraj) {
            return new Interval(i2.pocetak, i2.kraj, i2.daLiPripadaPocetak, i2.daLiPripadaKraj);
        } else if (pocetak > i2.pocetak && kraj < i2.kraj) {
            return new Interval(pocetak, kraj, daLiPripadaPocetak, daLiPripadaKraj);
        }
        return null;
    }

    public static Interval intersect(Interval i, Interval i2) {
        if (i.pocetak < i2.pocetak && i.kraj < i2.kraj) {
            return new Interval(i2.pocetak, i.kraj, i2.daLiPripadaPocetak, i.daLiPripadaKraj);
        } else if (i.pocetak > i2.pocetak && i.kraj > i2.kraj) {
            return new Interval(i.pocetak, i2.kraj, i.daLiPripadaPocetak, i2.daLiPripadaKraj);
        } else if (i.pocetak < i2.pocetak && i.kraj > i2.kraj) {
            return new Interval(i2.pocetak, i2.kraj, i2.daLiPripadaPocetak, i2.daLiPripadaKraj);
        } else if (i.pocetak > i2.pocetak && i.kraj < i2.kraj) {
            return new Interval(i.pocetak, i.kraj, i.daLiPripadaPocetak, i.daLiPripadaKraj);
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        Interval i2 = (Interval) obj;
        return (pocetak - i2.pocetak < 0.000001 && kraj - i2.kraj < 0.0000001 && daLiPripadaPocetak == i2.daLiPripadaPocetak && daLiPripadaKraj == i2.daLiPripadaKraj);
    }

    @Override
    public String toString() {
        if (daLiPripadaPocetak && daLiPripadaKraj) {
            return String.format("[%.1f,%.1f]", pocetak, kraj);
        } else if (daLiPripadaPocetak) {
            return String.format("[%.1f,%.1f)", pocetak, kraj);
        } else if (daLiPripadaKraj) {
            return String.format("(%.1f,%.1f]", pocetak, kraj);
        } else {
            if (pocetak != 0 || kraj != 0)
                return String.format("(%.1f,%.1f)", pocetak, kraj);
        }
        return "()";
    }
}
