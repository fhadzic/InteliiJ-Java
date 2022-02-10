package ba.unsa.etf.rpr.Tutorijal4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void spisakAktivnihPredmeta() {

    }

    @Test
    void spisakOdslusanihPredmeta() {
    }

    @Test
    void testToString() {
        Student s = new Student("Ferid", "Hadzic", "17685");
        assertEquals("Student: Hadzic Ferid, broj indexa: 17685.", s.toString());
    }
}