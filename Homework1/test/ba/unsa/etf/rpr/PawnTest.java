package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @org.junit.jupiter.api.Test
    void move1() {
        Pawn p = new Pawn("E2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> p.move("E4")
        );
    }

    @Test
    void move2() {
        Pawn p = new Pawn("A7", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> p.move("A5")
        );
    }

    @Test
    void getColorAndPosition() {
        Pawn p = new Pawn("C7", ChessPiece.Color.BLACK);
        assertAll("geteri", () -> {
            assertEquals("C7", p.getPosition());
        }, () -> {
            assertEquals(ChessPiece.Color.BLACK, p.getColor());
        });
    }


    @Test
    void moveIllegalBackBlack() {
        Pawn p = new Pawn("C2", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalChessMoveException.class,
                () -> p.move("C3")
        );

    }

    @Test
    void moveIllegalBackWhite() {
        Pawn p = new Pawn("E4", ChessPiece.Color.WHITE);
        assertThrows(
                IllegalChessMoveException.class,
                () -> p.move("E3")
        );
    }

    @Test
    void moveLegallyBlack() {
        Pawn p = new Pawn("D5", ChessPiece.Color.BLACK);
        assertDoesNotThrow(() -> {
            p.move("D4");
        });

    }

    @Test
    void moveLegallyWhite() {
        Pawn p = new Pawn("D5", ChessPiece.Color.WHITE);
        assertDoesNotThrow(() -> {
            p.move("D6");
        });
    }


    @Test
    void moveAndeEatRightWhite() {
        Pawn p = new Pawn("D5", ChessPiece.Color.WHITE);
        assertDoesNotThrow(() -> {
            p.move("E6");
        });
    }

    @Test
    void moveAndeEatLeftWhite() {
        Pawn p = new Pawn("D5", ChessPiece.Color.WHITE);
        assertDoesNotThrow(() -> {
            p.move("C6");
        });
    }

    @Test
    void moveAndeEatLeftBlack() {
        Pawn p = new Pawn("H5", ChessPiece.Color.BLACK);
        assertDoesNotThrow(() -> {
            p.move("G4");
        });
    }

    @Test
    void moveAndeEatRightBlack() {
        Pawn p = new Pawn("G5", ChessPiece.Color.BLACK);
        assertDoesNotThrow(() -> {
            p.move("H4");
        });
    }

    @Test
    void moveIllegalEatWhite1() {
        Pawn p = new Pawn("G6", ChessPiece.Color.WHITE);
        assertThrows(
                IllegalChessMoveException.class,
                () -> p.move("F5")
        );
    }

    @Test
    void moveIllegalEatWhite2() {
        Pawn p = new Pawn("G6", ChessPiece.Color.WHITE);
        assertThrows(
                IllegalChessMoveException.class,
                () -> p.move("H5")
        );
    }

    @Test
    void moveIllegalEatBlack1() {
        Pawn p = new Pawn("G6", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalChessMoveException.class,
                () -> p.move("F7")
        );
    }

    @Test
    void moveIllegalEatBlack2() {
        Pawn p = new Pawn("G6", ChessPiece.Color.BLACK);
        assertThrows(
                IllegalChessMoveException.class,
                () -> p.move("H7")
        );
    }

}