package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void constructor() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Queen("I2", ChessPiece.Color.WHITE)
        );
    }

    @Test
    void getPosition() {
        Queen q = new Queen("D1", ChessPiece.Color.WHITE);
        assertEquals("D1", q.getPosition());
    }

    @Test
    void getColor() {
        Queen q = new Queen("D1", ChessPiece.Color.WHITE);
        assertEquals(ChessPiece.Color.WHITE, q.getColor());
    }

    @Test
    void move() {
        Queen q1 = new Queen("D2", ChessPiece.Color.WHITE);
        Queen q2 = new Queen("D8", ChessPiece.Color.BLACK);
        assertDoesNotThrow(() -> {
            q1.move("A5");
            q2.move("A5");
        });
    }

    @Test
    void moveDiagonalRight() {
        Queen q = new Queen("D8", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> q.move("A5")
        );
    }

    @Test
    void moveDiagonalLeft() {
        Queen q = new Queen("D1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> q.move("B3")
        );
    }

    @Test
    void moveBack() {
        Queen q = new Queen("B5", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> q.move("B3")
        );
    }

    @Test
    void moveTop() {
        Queen q = new Queen("G6", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> q.move("G1")
        );
    }

    @Test
    void moveIllegalBlack() {
        Queen q = new Queen("D8", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalChessMoveException.class,
                () -> q.move("C6")
        );
    }

    @Test
    void moveIllegalWhite() {
        Queen q = new Queen("D1", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalChessMoveException.class,
                () -> q.move("G5")
        );
    }

    @Test
    void notToMove() {
        Queen q = new Queen("D1", ChessPiece.Color.WHITE);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    q.move("D1");
                });
    }

}