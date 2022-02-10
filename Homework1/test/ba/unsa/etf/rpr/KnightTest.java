package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    void move() {
        Knight k = new Knight("B1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> k.move("C3")
        );

    }

    @Test
    void getColorAndPosition() {
        Knight k = new Knight("G1", ChessPiece.Color.WHITE);
        assertAll("geteri", () -> {
            assertEquals("G1", k.getPosition());
        }, () -> {
            assertEquals(ChessPiece.Color.WHITE, k.getColor());
        });
    }

    @Test
    void moveTwoRight() {
        Knight k = new Knight("C2", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> k.move("E2")
        );
    }

    @Test
    void moveTwoBack() {
        Knight k = new Knight("C4", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> k.move("C2")
        );
    }

    @Test
    void moveDiagonalLeft1() {
        Knight k = new Knight("C3", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> k.move("A5")
        );
    }

    @Test
    void moveDiagonalLeft2() {
        Knight k = new Knight("C3", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> k.move("A1")
        );
    }

    @Test
    void moveIllegal1() {
        Knight k = new Knight("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> k.move("C0")
        );
    }

    @Test
    void moveIllegal2() {
        Knight k = new Knight("H1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> k.move("I4")
        );
    }

    @Test
    void moveIllegal3() {
        Knight k = new Knight("C1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalArgumentException.class,
                () -> k.move("")
        );
    }

    @Test
    void moveIllegal4() {
        Knight k = new Knight("D4", ChessPiece.Color.WHITE);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    k.move("D4");
                });
    }



    @Test
    void moveLegally1() {
        Knight k = new Knight("E3", ChessPiece.Color.BLACK);
        assertDoesNotThrow(() -> {
            k.move("F1");
        });

    }

    @Test
    void moveLegally2() {
        Knight k = new Knight("E3", ChessPiece.Color.BLACK);
        assertDoesNotThrow(() -> {
            k.move("D1");
        });

    }

    @Test
    void moveLegally3() {
        Knight k = new Knight("E3", ChessPiece.Color.BLACK);
        assertDoesNotThrow(() -> {
            k.move("F5");
        });

    }

    @Test
    void moveLegally4() {
        Knight k = new Knight("E3", ChessPiece.Color.BLACK);
        assertDoesNotThrow(() -> {
            k.move("D5");
        });

    }

    @Test
    void moveLegally5() {
        Knight k = new Knight("B3", ChessPiece.Color.BLACK);
        assertDoesNotThrow(() -> {
            k.move("D2");
        });

    }

    @Test
    void moveLegally6() {
        Knight k = new Knight("B3", ChessPiece.Color.BLACK);
        assertDoesNotThrow(() -> {
            k.move("D4");
        });

    }


    @Test
    void moveLegally7() {
        Knight k = new Knight("h4", ChessPiece.Color.BLACK);
        assertDoesNotThrow(() -> {
            k.move("f3");
        });

    }

    @Test
    void moveLegally8() {
        Knight k = new Knight("h4", ChessPiece.Color.BLACK);
        assertDoesNotThrow(() -> {
            k.move("f5");
        });

    }


}