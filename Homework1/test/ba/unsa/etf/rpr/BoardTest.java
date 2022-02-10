package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
        // Is the board usable after isCheck
    void someLegalMoves() {
        Board b = new Board();
        boolean no = b.isCheck(ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
                    b.move(Bishop.class, ChessPiece.Color.WHITE, "A6");
                    b.move(Knight.class, ChessPiece.Color.WHITE, "C3");
                    b.move(King.class, ChessPiece.Color.WHITE, "E2");
                    b.move(King.class, ChessPiece.Color.WHITE, "E3");
                }
        );
    }

    @Test
        // Pawn eats diagonally
    void pawnDiagonal() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "E5");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "E6");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "C8");
                }
        );
    }

    @Test
        // Check by pawn
    void isCheck() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
        // Will queen be moved by isCheck
    void isCheckUsable() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
            b.move(Queen.class, ChessPiece.Color.WHITE, "E2");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E7");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F8");
        } catch (Exception e) {
            // Do nothing
        }
        b.isCheck(ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> {
                    b.move(Queen.class, ChessPiece.Color.WHITE, "D3");
                }
        );
    }

    @Test
        // No check
    void isCheck2() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "C8");
            b.move(Queen.class, ChessPiece.Color.WHITE, "E2");
        } catch (Exception e) {
            // Do nothing
        }
        assertFalse(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
        // Check by queen
    void isCheck3() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "C8");
            b.move(Queen.class, ChessPiece.Color.WHITE, "E2");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E7");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "F8");
        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
        // Queen, bishop and rook can't jump pieces
    void jumpPiece() {
        Board b = new Board();
        assertAll(
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Rook.class, ChessPiece.Color.BLACK, "H6")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Bishop.class, ChessPiece.Color.BLACK, "H6")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move(Queen.class, ChessPiece.Color.BLACK, "A5")
                )
        );
    }


    // Same test with other move method

    @Test
        // Is the board usable after isCheck
    void someLegalMoves1() {
        Board b = new Board();
        boolean no = b.isCheck(ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> {
                    b.move("E2", "E4");
                    b.move("F1", "A6");
                    b.move("B1", "C3");
                    b.move("E1", "E2");
                    b.move("E2", "E3");
                }
        );
    }

    @Test
        // Pawn eats diagonally, check by queen
    void pawnDiagonal1() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("E2", "E4");
                /*    b.move("E4", "E5");
                    b.move("E5", "E6");
                    b.move("E6", "D7");
                    b.move("D7", "C8");*/
                }
        );
    }

    @Test
        // Check by pawn
    void isCheck1() {
        Board b = new Board();
        try {
            b.move("E2", "E4");
            b.move("E4", "E5");
            b.move("E5", "E6");
            b.move("E6", "D7");
        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
        // No check
    void isCheck12() {
        Board b = new Board();
        try {
            b.move("E2", "E4");
            b.move("E4", "E5");
            b.move("E5", "E6");
            b.move("E6", "D7");
            b.move("D7", "C8");
            b.move("D1", "E2");
        } catch (Exception e) {
            // Do nothing
        }
        assertFalse(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
        // Check by queen
    void isCheck13() {
        Board b = new Board();
        try {
            b.move("E2", "E4");
            b.move("E4", "E5");
            b.move("E5", "E6");
            b.move("E6", "D7");
            b.move("D7", "C8");
            b.move("D1", "E2");
            b.move("F2", "F4");
            b.move("F4", "F5");
            b.move("F5", "F6");
            b.move("F6", "E7");
            b.move("E7", "F8");
        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }

    @Test
        // Queen, bishop and rook can't jump pieces
    void jumpPiece1() {
        Board b = new Board();
        assertAll(
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move("H8", "H6")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move("F8", "H6")
                ),
                () -> assertThrows(
                        IllegalChessMoveException.class,
                        () -> b.move("D8", "A5")
                )
        );
    }

    @Test
        // Check by queen
    void isCheckUsable1() {
        Board b = new Board();
        try {
            b.move("E2", "E4");
            b.move("E4", "E5");
            b.move("E5", "E6");
            b.move("E6", "D7");
            b.move("D7", "C8");
            b.move("D1", "E2");
        } catch (Exception e) {
            // Do nothing
        }
        b.isCheck(ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> {
                    b.move("E2", "D3");
                }
        );
    }







    //MY TESTS
    //MOVE WITH 3 VARIABLES
    @Test
    void convertPositionToString() {
        assertAll("pozicije",
                () -> {
                    assertEquals("A1", Board.convertPositionToString(0, 0));
                },
                () -> {

                    assertEquals("H1", Board.convertPositionToString(0, 7));
                }, () -> {

                    assertEquals("A8", Board.convertPositionToString(7, 0));
                }, () -> {

                    assertEquals("H8", Board.convertPositionToString(7, 7));
                });
    }

    //Knight
    @Test
    void knightMoveLegallyBlack() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move(Knight.class, ChessPiece.Color.BLACK, "F6");
                    b.move(Knight.class, ChessPiece.Color.BLACK, "E4");
                    b.move(Knight.class, ChessPiece.Color.BLACK, "F2");
                    b.move(Knight.class, ChessPiece.Color.BLACK, "D1");
                    b.move(Knight.class, ChessPiece.Color.BLACK, "C3");
                    b.move(Knight.class, ChessPiece.Color.BLACK, "A2");
                    b.move(Knight.class, ChessPiece.Color.BLACK, "C1");
                    b.move(Knight.class, ChessPiece.Color.BLACK, "E2");
                }
        );
    }

    @Test
    void knightMoveLegallyWhite() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move(Knight.class, ChessPiece.Color.WHITE, "C3");
                    b.move(Knight.class, ChessPiece.Color.WHITE, "B5");
                    b.move(Knight.class, ChessPiece.Color.WHITE, "A7");
                    b.move(Knight.class, ChessPiece.Color.WHITE, "C8");
                    b.move(Knight.class, ChessPiece.Color.WHITE, "E7");
                    b.move(Knight.class, ChessPiece.Color.WHITE, "D5");
                    b.move(Knight.class, ChessPiece.Color.WHITE, "C3");
                    b.move(Knight.class, ChessPiece.Color.WHITE, "B1");
                }
        );
    }

    @Test
    void knightMoveIllegalWhite() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Knight.class, ChessPiece.Color.WHITE, "C3");
                    b.move(Knight.class, ChessPiece.Color.WHITE, "D1");
                }
        );
    }

    @Test
    void knightMoveIllegalWhite1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Knight.class, ChessPiece.Color.WHITE, "C6");
                    b.move(Knight.class, ChessPiece.Color.WHITE, "E7");
                }
        );
    }

    @Test
    void knightMoveIllegalBlack() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Knight.class, ChessPiece.Color.BLACK, "F6");
                    b.move(Knight.class, ChessPiece.Color.BLACK, "E8");
                }
        );
    }

    @Test
    void knightMoveIllegalBlack1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Knight.class, ChessPiece.Color.BLACK, "C6");
                    b.move(Knight.class, ChessPiece.Color.BLACK, "D5");
                }
        );
    }

    @Test
    void knightMoveOutBoard() {
        Board b = new Board();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    b.move(Knight.class, ChessPiece.Color.BLACK, "C6");
                    b.move(Knight.class, ChessPiece.Color.BLACK, "D9");
                }
        );
    }

    //KING
    @Test
    void kingtMoveIllegalWhite() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(King.class, ChessPiece.Color.WHITE, "E2");
                }
        );
    }

    @Test
    void kingMoveIllegalBlack() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(King.class, ChessPiece.Color.BLACK, "D8");
                }
        );
    }

    @Test
    void kingMoveTwoPositionIllegalBlack() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "e5");
                    b.move(King.class, ChessPiece.Color.BLACK, "e6");
                }
        );
    }

    @Test
    void kingMoveOutBoard() {
        Board b = new Board();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    b.move(Knight.class, ChessPiece.Color.BLACK, "E0");
                }
        );
    }


    //PAWN
    @Test
    void pawnMoveIllegalWhite() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Knight.class, ChessPiece.Color.WHITE, "C3");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "C3");
                }
        );
    }

    @Test
    void pawnTwoMoveIllegalWhite() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Knight.class, ChessPiece.Color.WHITE, "C3");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "C4");
                }
        );
    }

    @Test
    void pawnTwoMoveIllegalBlack() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Knight.class, ChessPiece.Color.BLACK, "F6");
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "F5");
                }
        );
    }

    @Test
    void pawnMoveIllegalBlack() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Knight.class, ChessPiece.Color.BLACK, "F6");
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "F6");
                }
        );
    }

    @Test
    void pawnMoveIllegalWhiteTop() {
        Board b = new Board();

        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "G4");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "G5");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "G6");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "G7");
                }
        );
    }

    @Test
    void pawnMoveIllegalBlackTop() {
        Board b = new Board();

        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "D5");
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "D4");
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "D3");
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "D2");
                }
        );
    }

    @Test
    void pawnMoveOutBoard() {
        Board b = new Board();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "E9");
                }
        );
    }

    @Test
    void pawnDiagonalMove() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "F4");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "F5");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "F6");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "G7");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "H8");
                }
        );
    }

    @Test
    void pawnDiagonalMove1() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "B5");
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "B4");
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "B3");
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "C2");
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "D1");
                }
        );
    }

    @Test
    void pawnDiagonalEat() throws IllegalChessMoveException {
        Board b = new Board();
        b.move(Pawn.class, ChessPiece.Color.BLACK, "B5");
        b.move(Pawn.class, ChessPiece.Color.BLACK, "B4");
        b.move(Pawn.class, ChessPiece.Color.BLACK, "B3");
        b.move(Pawn.class, ChessPiece.Color.BLACK, "C2");
        b.move(Pawn.class, ChessPiece.Color.BLACK, "D1");
        assertEquals(14, b.getNumberOfActiveWhiteFigures());
    }

    @Test
    void pawnDiagonalEat1() throws IllegalChessMoveException {
        Board b = new Board();
        b.move(Pawn.class, ChessPiece.Color.WHITE, "F4");
        b.move(Pawn.class, ChessPiece.Color.WHITE, "F5");
        b.move(Pawn.class, ChessPiece.Color.WHITE, "F6");
        b.move(Pawn.class, ChessPiece.Color.WHITE, "G7");
        b.move(Pawn.class, ChessPiece.Color.WHITE, "H8");
        assertEquals(14, b.getNumberOfActiveBlackFigures());
    }




    //BISHOP

    @Test
    void bishopMoveIllegalWhite() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Bishop.class, ChessPiece.Color.WHITE, "H3");
                }
        );
    }


    @Test
    void bishopMoveIllegalWhite1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "B4");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "A3");
                    b.move(Bishop.class, ChessPiece.Color.WHITE, "A3");
                }
        );
    }

    @Test
    void bishopMoveIllegalBlack() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "D5");
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "E6");
                    b.move(Bishop.class, ChessPiece.Color.BLACK, "E6");
                }
        );
    }

    @Test
    void bishopMoveIllegalBlack1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "D6");
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "E5");
                    b.move(Knight.class, ChessPiece.Color.BLACK, "h6");
                    b.move(Knight.class, ChessPiece.Color.BLACK, "f5");
                    b.move(Bishop.class, ChessPiece.Color.BLACK, "G4");
                }
        );
    }


    @Test
    void bishopEatWhite() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "G6");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "B3");
                    b.move(Bishop.class, ChessPiece.Color.BLACK, "G7");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "G3");
                    b.move(Bishop.class, ChessPiece.Color.BLACK, "A1");
                    b.move(Knight.class, ChessPiece.Color.WHITE, "C3");
                    b.move(Bishop.class, ChessPiece.Color.BLACK, "C3");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "C3");

                }
        );
    }

    @Test
    void bishopEatBlack() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "E6");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "B3");
                    b.move(Knight.class, ChessPiece.Color.BLACK, "F6");
                    b.move(Bishop.class, ChessPiece.Color.WHITE, "B2");
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "B5");
                    b.move(Bishop.class, ChessPiece.Color.WHITE, "F6");
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "F6");
                }
        );
    }


    @Test
    void bishopEat() throws IllegalChessMoveException {

        Board b = new Board();
        b.move(Pawn.class, ChessPiece.Color.BLACK, "G6");
        b.move(Pawn.class, ChessPiece.Color.WHITE, "B3");
        b.move(Bishop.class, ChessPiece.Color.BLACK, "G7");
        b.move(Pawn.class, ChessPiece.Color.WHITE, "G3");
        b.move(Bishop.class, ChessPiece.Color.BLACK, "A1");
        b.move(Knight.class, ChessPiece.Color.WHITE, "C3");
        b.move(Bishop.class, ChessPiece.Color.BLACK, "C3");
        b.move(Pawn.class, ChessPiece.Color.WHITE, "C3");

        assertAll("Stvarno pojedene",
                () -> {
                    assertEquals(14, b.getNumberOfActiveWhiteFigures());
                },
                ()-> {
                    assertEquals(15, b.getNumberOfActiveBlackFigures());
                }
                );

    }

    @Test
    void bishopEat1() throws IllegalChessMoveException {

        Board b = new Board();
        b.move(Pawn.class, ChessPiece.Color.BLACK, "E6");
        b.move(Pawn.class, ChessPiece.Color.WHITE, "B3");
        b.move(Knight.class, ChessPiece.Color.BLACK, "F6");
        b.move(Bishop.class, ChessPiece.Color.WHITE, "B2");
        b.move(Pawn.class, ChessPiece.Color.BLACK, "B5");
        b.move(Bishop.class, ChessPiece.Color.WHITE, "F6");
        b.move(Pawn.class, ChessPiece.Color.BLACK, "F6");

        assertAll("Stvarno pojedene",
                () -> {
                    assertEquals(15, b.getNumberOfActiveWhiteFigures());
                },
                ()-> {
                    assertEquals(15, b.getNumberOfActiveBlackFigures());
                }
        );
    }




    //ROOK
    @Test
    void rookMoveIllegalWhite() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Rook.class, ChessPiece.Color.WHITE, "H5");
                }
        );
    }


    @Test
    void rookMoveIllegalWhite1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "A4");
                    b.move(Rook.class, ChessPiece.Color.WHITE, "A4");
                }
        );
    }


    @Test
    void rookMoveIllegalBlack() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Rook.class, ChessPiece.Color.BLACK, "H4");
                }
        );
    }

    @Test
    void rookMoveIllegalBlack1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "h3");
                    b.move(Rook.class, ChessPiece.Color.BLACK, "H7");
                }
        );
    }


    @Test
    void rookEatWhite() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "H5");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "B3");
                    b.move(Rook.class, ChessPiece.Color.BLACK, "H6");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "G3");
                    b.move(Rook.class, ChessPiece.Color.BLACK, "A6");
                    b.move(Knight.class, ChessPiece.Color.WHITE, "A3");
                    b.move(Rook.class, ChessPiece.Color.BLACK, "A3");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "C3");
                    b.move(Rook.class, ChessPiece.Color.BLACK, "A2");
                    b.move(Rook.class, ChessPiece.Color.WHITE, "A2");

                }
        );
    }

    @Test
    void rookEatBlack() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "A4");
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "B5");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "B5");
                    b.move(Knight.class, ChessPiece.Color.BLACK, "A6");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "A6");
                    b.move(Bishop.class, ChessPiece.Color.BLACK, "A6");
                    b.move(Rook.class, ChessPiece.Color.WHITE, "A6");
                    b.move(Knight.class, ChessPiece.Color.BLACK, "F6");
                    b.move(Rook.class, ChessPiece.Color.WHITE, "A7");
                    b.move(Rook.class, ChessPiece.Color.BLACK, "A7");
                }
        );
    }




    @Test
    void rookEat() throws IllegalChessMoveException {

        Board b = new Board();
        b.move(Pawn.class, ChessPiece.Color.BLACK, "H5");
        b.move(Pawn.class, ChessPiece.Color.WHITE, "B3");
        b.move(Rook.class, ChessPiece.Color.BLACK, "H6");
        b.move(Pawn.class, ChessPiece.Color.WHITE, "G3");
        b.move(Rook.class, ChessPiece.Color.BLACK, "A6");
        b.move(Knight.class, ChessPiece.Color.WHITE, "A3");
        b.move(Rook.class, ChessPiece.Color.BLACK, "A3");
        b.move(Pawn.class, ChessPiece.Color.WHITE, "C3");
        b.move(Rook.class, ChessPiece.Color.BLACK, "A2");
        b.move(Rook.class, ChessPiece.Color.WHITE, "A2");

        assertAll("Stvarno pojedene",
                () -> {
                    assertEquals(14, b.getNumberOfActiveWhiteFigures());
                },
                ()-> {
                    assertEquals(15, b.getNumberOfActiveBlackFigures());
                }
                );

    }


    @Test
    void rookEat1() throws IllegalChessMoveException {

        Board b = new Board();
        b.move(Pawn.class, ChessPiece.Color.WHITE, "A4");
        b.move(Pawn.class, ChessPiece.Color.BLACK, "B5");
        b.move(Pawn.class, ChessPiece.Color.WHITE, "B5");
        b.move(Knight.class, ChessPiece.Color.BLACK, "A6");
        b.move(Pawn.class, ChessPiece.Color.WHITE, "A6");
        b.move(Bishop.class, ChessPiece.Color.BLACK, "A6");
        b.move(Rook.class, ChessPiece.Color.WHITE, "A6");
        b.move(Knight.class, ChessPiece.Color.BLACK, "F6");
        b.move(Rook.class, ChessPiece.Color.WHITE, "A7");
        b.move(Rook.class, ChessPiece.Color.BLACK, "A7");

        assertAll("Stvarno pojedene",
                () -> {
                    assertEquals(14, b.getNumberOfActiveWhiteFigures());
                },
                ()-> {
                    assertEquals(12, b.getNumberOfActiveBlackFigures());
                }
        );
    }



    //QUEEN
    @Test
    void queenMoveIllegalWhite() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Queen.class, ChessPiece.Color.WHITE, "D5");
                }
        );
    }


    @Test
    void queenMoveIllegalWhite1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "d4");
                    b.move(Queen.class, ChessPiece.Color.WHITE, "d4");
                }
        );
    }


    @Test
    void queenMoveIllegalBlack() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Queen.class, ChessPiece.Color.BLACK, "D5");
                }
        );
    }

    @Test
    void queenMoveIllegalBlack1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "D5");
                    b.move(Queen.class, ChessPiece.Color.BLACK, "A2");
                }
        );
    }



    @Test
    void queenEatWhite() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "E6");
                    b.move(Knight.class, ChessPiece.Color.WHITE, "H3");
                    b.move(Pawn.class, ChessPiece.Color.BLACK, "G6");
                    b.move(Knight.class, ChessPiece.Color.WHITE, "G5");
                    b.move(Queen.class, ChessPiece.Color.BLACK, "G5");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "D4");
                    b.move(Pawn.class, ChessPiece.Color.WHITE, "D5");
                    b.move(Queen.class, ChessPiece.Color.BLACK, "D5");
                    b.move(Queen.class, ChessPiece.Color.WHITE, "d5");
                }
        );
    }



    @Test
    void queenEat1() throws IllegalChessMoveException {

        Board b = new Board();
        b.move(Pawn.class, ChessPiece.Color.BLACK, "E6");
        b.move(Knight.class, ChessPiece.Color.WHITE, "H3");
        b.move(Pawn.class, ChessPiece.Color.BLACK, "G6");
        b.move(Knight.class, ChessPiece.Color.WHITE, "G5");
        b.move(Queen.class, ChessPiece.Color.BLACK, "G5");
        b.move(Pawn.class, ChessPiece.Color.WHITE, "D4");
        b.move(Pawn.class, ChessPiece.Color.WHITE, "D5");
        b.move(Queen.class, ChessPiece.Color.BLACK, "D5");
        b.move(Queen.class, ChessPiece.Color.WHITE, "d5");

        assertAll("Stvarno pojedene",
                () -> {
                    assertEquals(14, b.getNumberOfActiveWhiteFigures());
                },
                ()-> {
                    assertEquals(15, b.getNumberOfActiveBlackFigures());
                }
        );
    }







    //isCHECK
    //QUEEN
    @Test
    void isCheckOfQueenWhite() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
            b.move(Pawn.class, ChessPiece.Color.BLACK, "D5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E7");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D8");
            b.move(Queen.class, ChessPiece.Color.WHITE, "E2");
        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }



    @Test
    void isCheckOfQueenBlack() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "E6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D7");
            b.move(Queen.class, ChessPiece.Color.BLACK, "D7");
            b.move(Queen.class, ChessPiece.Color.BLACK, "E6");
        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.WHITE));
    }




    //BISHOP
    @Test
    void isCheckOfBishopWhite() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "e3");
            b.move(Pawn.class, ChessPiece.Color.BLACK, "D5");
            b.move(Bishop.class, ChessPiece.Color.WHITE, "B5");
        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }



    @Test
    void isCheckOfBishopBlack() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "D3");
            b.move(Pawn.class, ChessPiece.Color.BLACK, "E6");
            b.move(Bishop.class, ChessPiece.Color.BLACK, "B4");

        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.WHITE));
    }




    //ROOK
    @Test
    void isCheckOfRookBlack() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "e4");
            b.move(King.class, ChessPiece.Color.WHITE, "e2");
            b.move(King.class, ChessPiece.Color.WHITE, "e3");
            b.move(Pawn.class, ChessPiece.Color.BLACK, "a5");
            b.move(Pawn.class, ChessPiece.Color.BLACK, "a4");
            b.move(Pawn.class, ChessPiece.Color.BLACK, "a3");
            b.move(Pawn.class, ChessPiece.Color.BLACK, "b2");
            b.move(Rook.class, ChessPiece.Color.BLACK, "a3");
        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.WHITE));
    }



    @Test
    void isCheckOfRookWhite() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "a4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "a5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "a6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "b7");
            b.move(Pawn.class, ChessPiece.Color.BLACK, "e5");
            b.move(King.class, ChessPiece.Color.BLACK, "e7");
            b.move(King.class, ChessPiece.Color.BLACK, "e6");
            b.move(Rook.class, ChessPiece.Color.WHITE, "a6");

        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }


    //PAWN
    @Test
    void isCheckOfPawnBlack() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.BLACK, "e5");
            b.move(Pawn.class, ChessPiece.Color.BLACK, "e4");
            b.move(Pawn.class, ChessPiece.Color.BLACK, "e3");
            b.move(Pawn.class, ChessPiece.Color.BLACK, "d2");
        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.WHITE));
    }



    @Test
    void isCheckOfPawnWhite() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "e4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "e5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "e6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "d7");

        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }


    //KNIGHT
    @Test
    void isCheckOfKnightBlack() {
        Board b = new Board();
        try {
            b.move(Knight.class, ChessPiece.Color.BLACK, "f6");
            b.move(Knight.class, ChessPiece.Color.BLACK, "g4");
            b.move(Knight.class, ChessPiece.Color.BLACK, "f2");
            b.move(Knight.class, ChessPiece.Color.BLACK, "d3");
        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.WHITE));
    }



    @Test
    void isCheckOfKnightWhite() {
        Board b = new Board();
        try {
            b.move(Knight.class, ChessPiece.Color.WHITE, "c3");
            b.move(Knight.class, ChessPiece.Color.WHITE, "d5");
            b.move(Knight.class, ChessPiece.Color.WHITE, "c7");

        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }




    //KING
    @Test
    void isCheckOfKingBlack() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.BLACK, "e5");
            b.move(Pawn.class, ChessPiece.Color.BLACK, "e4");
            b.move(Pawn.class, ChessPiece.Color.BLACK, "e3");
            b.move(Pawn.class, ChessPiece.Color.BLACK, "f2");
            b.move(Pawn.class, ChessPiece.Color.BLACK, "g1");
            b.move(King.class, ChessPiece.Color.BLACK, "e7");
            b.move(King.class, ChessPiece.Color.BLACK, "e6");
            b.move(King.class, ChessPiece.Color.BLACK, "e5");
            b.move(King.class, ChessPiece.Color.BLACK, "e4");
            b.move(King.class, ChessPiece.Color.BLACK, "e3");
            b.move(King.class, ChessPiece.Color.BLACK, "d2");
        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.WHITE));
    }



    @Test
    void isCheckOfKingWhite() {
        Board b = new Board();
        try {
            b.move(Pawn.class, ChessPiece.Color.WHITE, "e4");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "e5");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "e6");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "d7");
            b.move(Pawn.class, ChessPiece.Color.WHITE, "c8");
            b.move(King.class, ChessPiece.Color.WHITE, "e2");
            b.move(King.class, ChessPiece.Color.WHITE, "e3");
            b.move(King.class, ChessPiece.Color.WHITE, "e4");
            b.move(King.class, ChessPiece.Color.WHITE, "e5");
            b.move(King.class, ChessPiece.Color.WHITE, "e6");
            b.move(King.class, ChessPiece.Color.WHITE, "e7");
        } catch (Exception e) {
            // Do nothing
        }
        assertTrue(b.isCheck(ChessPiece.Color.BLACK));
    }





    //MOVE WITH 2 VARIABLES
    //PAWN
     @Test
    void pawnMoveIllegal1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Knight.class, ChessPiece.Color.WHITE, "C3");
                    b.move("C2", "C3");
                }
        );
    }

    @Test
    void pawnMoveIllegal2() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Knight.class, ChessPiece.Color.WHITE, "C3");
                    b.move("C2", "C4");
                }
        );
    }


    @Test
    void pawnTwoMove1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Knight.class, ChessPiece.Color.BLACK, "F6");
                    b.move("f7", "F5");
                }
        );
    }

    @Test
    void pawnMove2() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move(Knight.class, ChessPiece.Color.BLACK, "F6");
                    b.move("f7", "F6");
                }
        );
    }

    @Test
    void pawnTwoMoveIllegalTop() {
        Board b = new Board();

        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("G2", "G4");
                    b.move("G4", "G5");
                    b.move("G5", "G6");
                    b.move("G6", "G7");
                }
        );
    }

    @Test
    void pawnTwoMoveIllegalTop1() {
        Board b = new Board();

        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("d7", "D5");
                    b.move("D5", "D4");
                    b.move("d4", "D3");
                    b.move("d3", "D2");
                }
        );
    }

    @Test
    void pawnTwoMoveOutBoard() {
        Board b = new Board();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    b.move("e7", "E9");
                }
        );
    }

    @Test
    void pawnTwoDiagonalMove() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("F2", "F4");
                    b.move("F4", "F5");
                    b.move("f5", "F6");
                    b.move("f6", "G7");
                    b.move("g7", "H8");
                }
        );
    }

    @Test
    void pawnTwoDiagonalMove1() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("B7", "B5");
                    b.move("B5", "B4");
                    b.move("B4", "B3");
                    b.move("B3", "C2");
                    b.move("C2", "D1");
                }
        );
    }

    @Test
    void pawnTwoDiagonalEat() throws IllegalChessMoveException {
        Board b = new Board();
        b.move("B7", "B5");
        b.move("B5", "B4");
        b.move("b4", "B3");
        b.move("b3", "C2");
        b.move("c2", "D1");
        assertEquals(14, b.getNumberOfActiveWhiteFigures());
    }

    @Test
    void pawnTwoDiagonalEat1() throws IllegalChessMoveException {
        Board b = new Board();
        b.move("F2", "F4");
        b.move("F4", "F5");
        b.move("f5", "F6");
        b.move("f6", "G7");
        b.move("g7", "H8");
        assertEquals(14, b.getNumberOfActiveBlackFigures());
    }


    //ROOK
    @Test
    void rookTwoMoveIllegalWhite() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("h1", "H5");
                }
        );
    }


    @Test
    void rookTwoMoveIllegalWhite1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("a2", "A4");
                    b.move("A1", "A4");
                }
        );
    }


    @Test
    void rookTwoMoveIllegalBlack() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("H8", "h4");
                }
        );
    }

    @Test
    void rookTwoMoveIllegalBlack1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("h7", "h5");
                    b.move("h8", "H2");
                }
        );
    }


    @Test
    void rookTwoEatWhite() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("h7", "H5");
                    b.move("b2", "B3");
                    b.move("h8", "H6");
                    b.move("g2", "G3");
                    b.move("h6", "A6");
                    b.move("b1", "A3");
                    b.move("a6", "A3");
                    b.move("c2", "C3");
                    b.move("a3", "A2");
                    b.move("a1", "A2");

                }
        );
    }

    @Test
    void rookTwoEatBlack() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("a2", "A4");
                    b.move("b7", "B5");
                    b.move("a4", "B5");
                    b.move("b8", "A6");
                    b.move("b5", "A6");
                    b.move("c8", "A6");
                    b.move("a1", "A6");
                    b.move("g8", "F6");
                    b.move("a6", "A7");
                    b.move("a8", "A7");
                }
        );
    }




    @Test
    void rookTwoEat() throws IllegalChessMoveException {

        Board b = new Board();
        b.move("h7", "H5");
        b.move("b2", "B3");
        b.move("h8", "H6");
        b.move("g2", "G3");
        b.move("h6", "A6");
        b.move("b1", "A3");
        b.move("a6", "A3");
        b.move("c2", "C3");
        b.move("a3", "A2");
        b.move("a1", "A2");

        assertAll("Stvarno pojedene",
                () -> {
                    assertEquals(14, b.getNumberOfActiveWhiteFigures());
                },
                ()-> {
                    assertEquals(15, b.getNumberOfActiveBlackFigures());
                }
        );

    }


    @Test
    void rookTwoEat1() throws IllegalChessMoveException {

        Board b = new Board();
        b.move("a2", "A4");
        b.move("b7", "B5");
        b.move("a4", "B5");
        b.move("b8", "A6");
        b.move("b5", "A6");
        b.move("c8", "A6");
        b.move("a1", "A6");
        b.move("g8", "F6");
        b.move("a6", "A7");
        b.move("a8", "A7");

        assertAll("Stvarno pojedene",
                () -> {
                    assertEquals(14, b.getNumberOfActiveWhiteFigures());
                },
                ()-> {
                    assertEquals(12, b.getNumberOfActiveBlackFigures());
                }
        );
    }



    //BISHOP

    @Test
    void bishopTwoMoveIllegalWhite() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("F1", "H3");
                }
        );
    }


    @Test
    void bishopTwoMoveIllegalWhite1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("B2", "B4");
                    b.move("A2", "A3");
                    b.move("B1", "A3");
                }
        );
    }

    @Test
    void bishopTwoMoveIllegalBlack() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("D7", "D5");
                    b.move("E7", "E6");
                    b.move("C8", "E6");
                }
        );
    }

    @Test
    void bishopTwoMoveIllegalBlack1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("D7", "D6");
                    b.move("e7", "E5");
                    b.move("G8", "h6");
                    b.move("h6", "f5");
                    b.move("C8", "G4");
                }
        );
    }


    @Test
    void bishopTwoEatWhite() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("G7", "G6");
                    b.move("B2", "B3");
                    b.move("F8", "G7");
                    b.move("G2", "G3");
                    b.move("G7", "A1");
                    b.move("B1", "C3");
                    b.move("A1", "C3");
                    b.move("D2", "C3");

                }
        );
    }

    @Test
    void bishopTwoEatBlack() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("E7", "E6");
                    b.move("B2", "B3");
                    b.move("G8", "F6");
                    b.move("C1", "B2");
                    b.move("B7", "B5");
                    b.move("B2", "F6");
                    b.move("G7", "F6");
                }
        );
    }


    @Test
    void bishopTwoEat() throws IllegalChessMoveException {

        Board b = new Board();
        b.move("G7", "G6");
        b.move("B2", "B3");
        b.move("F8", "G7");
        b.move("G2", "G3");
        b.move("G7", "A1");
        b.move("B1", "C3");
        b.move("A1", "C3");
        b.move("D2", "C3");

        assertAll("Stvarno pojedene",
                () -> {
                    assertEquals(14, b.getNumberOfActiveWhiteFigures());
                },
                ()-> {
                    assertEquals(15, b.getNumberOfActiveBlackFigures());
                }
                );

    }

    @Test
    void bishopTwoEat1() throws IllegalChessMoveException {

        Board b = new Board();
        b.move("E7", "E6");
        b.move("B2", "B3");
        b.move("G8", "F6");
        b.move("C1", "B2");
        b.move("B7", "B5");
        b.move("B2", "F6");
        b.move("G7", "F6");

        assertAll("Stvarno pojedene",
                () -> {
                    assertEquals(15, b.getNumberOfActiveWhiteFigures());
                },
                ()-> {
                    assertEquals(15, b.getNumberOfActiveBlackFigures());
                }
        );
    }



    //Knight
    @Test
    void knightTwoMoveLegallyBlack() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("g8", "F6");
                    b.move("F6", "E4");
                    b.move("E4", "F2");
                    b.move("F2", "D1");
                    b.move("D1", "C3");
                    b.move("C3", "A2");
                    b.move("A2", "C1");
                    b.move("C1", "E2");
                }
        );
    }

    @Test
    void knightTwoMoveLegallyWhite() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("B1", "C3");
                    b.move("C3", "B5");
                    b.move("B5", "A7");
                    b.move("A7", "C8");
                    b.move("C8", "E7");
                    b.move("E7", "D5");
                    b.move("D5", "C3");
                    b.move("C3", "B1");
                }
        );
    }

    @Test
    void knightTwoMoveIllegalWhite() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("B1", "C3");
                    b.move("C3", "D1");
                }
        );
    }

    @Test
    void knightTwoMoveIllegalWhite1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("b8", "C6");
                    b.move("c6", "E7");
                }
        );
    }

    @Test
    void knightTwoMoveIllegalBlack() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("G8", "F6");
                    b.move("F6", "E8");
                }
        );
    }

    @Test
    void knightTwoMoveIllegalBlack1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("B8", "C6");
                    b.move("C6", "D5");
                }
        );
    }

    @Test
    void knightTwoMoveOutBoard() {
        Board b = new Board();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    b.move("B8", "C6");
                    b.move("C6", "D9");
                }
        );
    }

    //KING
    @Test
    void kingtTwoMoveIllegalWhite() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("E1", "E2");
                }
        );
    }

    @Test
    void kingTwoMoveIllegalBlack() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("e8", "D8");
                }
        );
    }

    @Test
    void kingTwoMoveOutBoard() {
        Board b = new Board();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    b.move("e8", "E0");
                }
        );
    }



    //QUEEN
    @Test
    void queenTwoMoveIllegalWhite() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("D1", "D5");
                }
        );
    }


    @Test
    void queenTwoMoveIllegalWhite1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("d2", "d4");
                    b.move("d1", "d4");
                }
        );
    }


    @Test
    void queenTwoMoveIllegalBlack() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("D8", "D5");
                }
        );
    }

    @Test
    void queenTwoMoveIllegalBlack1() {
        Board b = new Board();
        assertThrows(IllegalChessMoveException.class,
                () -> {
                    b.move("D7", "D5");
                    b.move("d8", "A2");
                }
        );
    }



    @Test
    void queenTwoEatWhite() {
        Board b = new Board();
        assertDoesNotThrow(
                () -> {
                    b.move("E7", "E6");
                    b.move("G1", "H3");
                    b.move("G7", "G6");
                    b.move("H3", "G5");
                    b.move("D8", "G5");
                    b.move("D2", "D4");
                    b.move("D4", "D5");
                    b.move("G5", "D5");
                    b.move("D1", "d5");
                }
        );
    }



    @Test
    void queenTwoEat1() throws IllegalChessMoveException {

        Board b = new Board();
        b.move("E7", "E6");
        b.move("G1", "H3");
        b.move("G7", "G6");
        b.move("H3", "G5");
        b.move("D8", "G5");
        b.move("D2", "D4");
        b.move("D4", "D5");
        b.move("G5", "D5");
        b.move("D1", "d5");

        assertAll("Stvarno pojedene",
                () -> {
                    assertEquals(14, b.getNumberOfActiveWhiteFigures());
                },
                ()-> {
                    assertEquals(15, b.getNumberOfActiveBlackFigures());
                }
        );
    }



}

