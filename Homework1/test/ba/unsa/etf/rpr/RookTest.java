package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

    @Test
    void constructor() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Rook("A0", ChessPiece.Color.WHITE)
        );
    }

    @Test
    void getPosition() {
        Rook r = new Rook("A1", ChessPiece.Color.WHITE);
        assertEquals("A1", r.getPosition());
    }

    @Test
    void getColor() {
        Rook r = new Rook("H1", ChessPiece.Color.WHITE);
        assertEquals(ChessPiece.Color.WHITE, r.getColor());
    }

    @Test
    void move() {
        Rook r1 = new Rook("H1", ChessPiece.Color.WHITE);
        Rook r2 = new Rook("A5", ChessPiece.Color.BLACK);
        assertDoesNotThrow(() -> {
            r1.move("H5");
            r2.move("H5");
        });
    }

    @Test
    void moveBack() {
        Rook r = new Rook("a5", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> r.move("a1")
        );
    }

    @Test
    void moveTop() {
        Rook r = new Rook("G6", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> r.move("G1")
        );
    }

    @Test
    void moveLeft() {
        Rook r = new Rook("H1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> r.move("E1")
        );
    }

    @Test
    void moveRight() {
        Rook r = new Rook("A1", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> r.move("E1")
        );
    }

    @Test
    void moveIllegal() {
        Rook r = new Rook("H8", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    r.move("f7");
                });
    }

    @Test
    void moveIllegalDiagonalRight() {
        Rook r = new Rook("H8", ChessPiece.Color.BLACK);
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    r.move("G7");
                });
    }

    @Test
    void moveIllegalDiagonalLeft() {
        Rook r = new Rook("H1", ChessPiece.Color.WHITE);
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    r.move("E4");
                });
    }

    @Test
    void notToMove() {
        Rook r = new Rook("H1", ChessPiece.Color.WHITE);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    r.move("H1");
                });
    }
}