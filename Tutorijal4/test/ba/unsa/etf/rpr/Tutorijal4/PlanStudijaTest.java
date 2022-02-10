package ba.unsa.etf.rpr.Tutorijal4;

import ba.unsa.etf.rpr.Tutorijal4.PlanStudija;
import ba.unsa.etf.rpr.Tutorijal4.Predmet;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PlanStudijaTest {

    @Test
    void dodajSemestarLegall() {
        PlanStudija ps = new PlanStudija();
        Set<Predmet> predmeti = new HashSet<>();
        predmeti.add( new Predmet("RPR", 5, true));
        predmeti.add( new Predmet("ASP", 5, true));
        predmeti.add( new Predmet("DM", 5, true));
        predmeti.add( new Predmet("OBP", 5, true));
        predmeti.add( new Predmet("LD", 5, true));
        predmeti.add( new Predmet("SP", 5, false));
        predmeti.add( new Predmet("NA", 5, false));

        assertDoesNotThrow(()->{
            ps.dodajSemestar(3, predmeti);
        });
    }

    @Test
    void dodajSemestarIllegal() {
        PlanStudija ps = new PlanStudija();
        Set<Predmet> predmeti = new HashSet<>();
        predmeti.add( new Predmet("RPR", 5, true));
        predmeti.add( new Predmet("ASP", 5, true));
        predmeti.add( new Predmet("DM", 5, true));
        predmeti.add( new Predmet("OBP", 5, true));
        predmeti.add( new Predmet("LD", 5, true));

        assertThrows(IllegalArgumentException.class,
                ()->ps.dodajSemestar(3, predmeti)
                );
    }

}