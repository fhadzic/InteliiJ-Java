package ba.unsa.etf.rpr.tutorijal_3;

public enum Grad {
    SARAJEVO,
    MOSTAR,
    ZENICA,
    TUZLA,
    TRAVNIK,
    ODZAK,
    LIVNO,
    BIHAC,
    GORAZDE,
    SIROKI_BRIJEG,
    BRCKO;


    public static String pozivniGrada(Grad g) {

        switch (g) {
            case SARAJEVO:
                return "033";
            case TRAVNIK:
                return "030";
            case ODZAK:
                return "031";
            case ZENICA:
                return "032";
            case LIVNO:
                return "034";
            case TUZLA:
                return "035";
            case MOSTAR:
                return "036";
            case BIHAC:
                return "037";
            case GORAZDE:
                return "038";
            case SIROKI_BRIJEG:
                return "039";
            case BRCKO:
                return "049";

            default:
                throw new IllegalArgumentException("Ne postoji grad!");
        }

    }
}
