package ba.unsa.etf.rpr.Tutorijal4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PredmetTest {

    @Test
    void spisakAktivnihStudenata() {
    }

    @Test
    void spisakBivsihStudenata() {
    }

    @Test
    void upisiOcjenu() {
    }

    @Test
    void testToString() {
        Predmet p = new Predmet("RPR", 5, true);

        assertEquals("Predmet RPR, ima 5 ECTS kredinat, i bovezni je predmet.", p.toString());
    }
}