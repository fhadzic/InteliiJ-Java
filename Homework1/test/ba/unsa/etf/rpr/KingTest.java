package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @org.junit.jupiter.api.Test
    void moveDiagonal() {
        King k = new King("E1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> k.move("D2")
        );
    }

    @org.junit.jupiter.api.Test
    void moveTwo() {
        King k = new King("C2", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> k.move("E2")
        );
    }

    @org.junit.jupiter.api.Test
    void moveBack() {
        King k = new King("E3", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> k.move("E2")
        );
    }

    @org.junit.jupiter.api.Test
    void constructor1() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new King("I2", ChessPiece.Color.WHITE)
        );
    }

    @org.junit.jupiter.api.Test
    void constructor2() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new King("B9", ChessPiece.Color.WHITE)
        );
    }

    @org.junit.jupiter.api.Test
    void constructor3() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new King("", ChessPiece.Color.WHITE)
        );
    }

    @org.junit.jupiter.api.Test
    void getColorAndPosition() {
        King k = new King("C2", ChessPiece.Color.BLACK);
        assertAll("geteri", () -> {
            assertEquals("C2", k.getPosition());
        }, () -> {
            assertEquals(ChessPiece.Color.BLACK, k.getColor());
        });
    }


    @org.junit.jupiter.api.Test
    void moveIllegal1() {
        King k = new King("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> k.move("C0")
        );
    }

    @org.junit.jupiter.api.Test
    void moveIllegal2() {
        King k = new King("H1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> k.move("I1")
        );
    }

    @org.junit.jupiter.api.Test
    void moveIllegal3() {
        King k = new King("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> k.move("")
        );
    }

    @Test
    void moveIllegal4() {
        King k = new King("e2", ChessPiece.Color.WHITE);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    k.move("e2");
                });
    }

    @Test
    void moveLegally1() {
        King k = new King("e2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(() -> {
            k.move("f2");
        });
    }

    @Test
    void moveLegally2() {
        King k = new King("e2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(() -> {
            k.move("d2");
        });
    }

    @Test
    void moveLegally3() {
        King k = new King("e2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(() -> {
            k.move("e3");
        });
    }

    @Test
    void moveLegally4() {
        King k = new King("e2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(() -> {
            k.move("e1");
        });
    }

    @Test
    void moveLegallyDiaogonal1() {
        King k = new King("e2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(() -> {
            k.move("f3");
        });
    }

    @Test
    void moveLegallyDiaogonal2() {
        King k = new King("e2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(() -> {
            k.move("d1");
        });
    }

    @Test
    void moveLegallyDiaogonal3() {
        King k = new King("e2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(() -> {
            k.move("d3");
        });
    }

    @Test
    void moveLegallyDiaogonal4() {
        King k = new King("e2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(() -> {
            k.move("f1");
        });
    }


}