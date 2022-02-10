package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    void constructorIllegal() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Bishop("C0", ChessPiece.Color.WHITE);
                });
    }

    @Test
    void getColorAndPosition() {
        Bishop b = new Bishop("f8", ChessPiece.Color.BLACK);
        assertAll("geteri", () -> {
            assertEquals("F8", b.getPosition());
        }, () -> {
            assertEquals(ChessPiece.Color.BLACK, b.getColor());
        });
    }

    @Test
    void move() {
        Bishop b1 = new Bishop("F1", ChessPiece.Color.WHITE);
        Bishop b2 = new Bishop("C8", ChessPiece.Color.BLACK);
        assertDoesNotThrow(() -> {
            b1.move("A6");
            b2.move("A6");
        });
    }

    @Test
    void moveWhite1() {
        Bishop b = new Bishop("F1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(() -> {
            b.move("B5");
        });
    }

    @Test
    void moveWhite2() {
        Bishop b = new Bishop("F1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(() -> {
            b.move("H3");
        });
    }

    @Test
    void moveBlack1() {
        Bishop b = new Bishop("C8", ChessPiece.Color.BLACK);
        assertDoesNotThrow(() -> {
            b.move("A6");
        });
    }

    @Test
    void moveBlack2() {
        Bishop b = new Bishop("C8", ChessPiece.Color.BLACK);
        assertDoesNotThrow(() -> {
            b.move("H3");
        });
    }

    @Test
    void moveIlegal1() {
        Bishop b = new Bishop("F1", ChessPiece.Color.WHITE);
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("C6");
                });
    }

    @Test
    void moveIlegal2() {
        Bishop b = new Bishop("F8", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("B5");
                });
    }

    @Test
    void moveIlegal3() {
        Bishop b = new Bishop("F8", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("a2");
                });
    }

    @Test
    void moveIlegal4() {
        Bishop b = new Bishop("C1", ChessPiece.Color.WHITE);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    b.move("I7");
                });
    }

    @Test
    void notToMove() {
        Bishop b = new Bishop("C1", ChessPiece.Color.WHITE);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    b.move("C1");
                });
    }

}