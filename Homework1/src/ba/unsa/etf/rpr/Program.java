package ba.unsa.etf.rpr;


import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws IllegalChessMoveException {
        System.out.println("Welcome to the game of chess!");
        Board b = new Board();
        Scanner ulaz = new Scanner(System.in);


        boolean whiteOnTheMove = true;


        while (true) {
            if (whiteOnTheMove) {

                System.out.print("White move: ");
                String line = ulaz.nextLine();

                if (line.charAt(0) == 'K' && line.length() == 3) {
                    try {
                        b.move(King.class, ChessPiece.Color.WHITE, line.substring(1));
                        if (b.isCheck(ChessPiece.Color.BLACK)) {
                            System.out.println("Faorbidden, you are in chess here! Repeat the move.");
                            continue;
                        }
                    } catch (IllegalChessMoveException | IllegalArgumentException c) {
                        System.out.println("Illegal move");
                        continue;
                    }
                } else if (line.charAt(0) == 'Q' && line.length() == 3) {
                    try {
                        b.move(Queen.class, ChessPiece.Color.WHITE, line.substring(1));
                        if (b.isCheck(ChessPiece.Color.BLACK)) {
                            System.out.println("CHECK!!");
                        }
                    } catch (IllegalChessMoveException | IllegalArgumentException c) {
                        System.out.println("Illegal move");
                        continue;
                    }
                } else if (line.charAt(0) == 'R' && line.length() == 3) {
                    try {
                        b.move(Rook.class, ChessPiece.Color.WHITE, line.substring(1));
                        if (b.isCheck(ChessPiece.Color.BLACK)) {
                            System.out.println("CHECK!!");
                        }
                    } catch (IllegalChessMoveException | IllegalArgumentException c) {
                        System.out.println("Illegal move");
                        continue;
                    }
                } else if (line.charAt(0) == 'B' && line.length() == 3) {
                    try {
                        b.move(Bishop.class, ChessPiece.Color.WHITE, line.substring(1));
                        if (b.isCheck(ChessPiece.Color.BLACK)) {
                            System.out.println("CHECK!!");
                        }
                    } catch (IllegalChessMoveException | IllegalArgumentException c) {
                        System.out.println("Illegal move");
                        continue;
                    }
                } else if (line.charAt(0) == 'N' && line.length() == 3) {
                    try {
                        b.move(Knight.class, ChessPiece.Color.WHITE, line.substring(1));
                        if (b.isCheck(ChessPiece.Color.BLACK)) {
                            System.out.println("CHECK!!");
                        }
                    } catch (IllegalChessMoveException | IllegalArgumentException c) {
                        System.out.println("Illegal move");
                        continue;
                    }
                } else if (line.length() == 2) {
                    try {
                        b.move(Pawn.class, ChessPiece.Color.WHITE, line);
                        if (b.isCheck(ChessPiece.Color.BLACK)) {
                            System.out.println("CHECK!!");
                        }
                    } catch (IllegalChessMoveException | IllegalArgumentException c) {
                        System.out.println("Illegal move");
                        continue;
                    }
                } else if (line.equals("X")) {
                    System.out.println("Game over, the WINNER is a player with BLACK figures!");
                    break;
                }

                whiteOnTheMove = false;

            } else {
                System.out.print("Black move: ");
                String line = ulaz.nextLine();

                if (line.charAt(0) == 'K' && line.length() == 3) {
                    try {
                        b.move(King.class, ChessPiece.Color.BLACK, line.substring(1));
                        if (b.isCheck(ChessPiece.Color.BLACK)) {
                            System.out.println("Faorbidden, you are in chess here! Repeat the move.");
                            continue;
                        }
                    } catch (IllegalChessMoveException | IllegalArgumentException c) {
                        System.out.println("Illegal move");
                        continue;
                    }
                } else if (line.charAt(0) == 'Q' && line.length() == 3) {
                    try {
                        b.move(Queen.class, ChessPiece.Color.BLACK, line.substring(1));
                        if (b.isCheck(ChessPiece.Color.WHITE)) {
                            System.out.println("CHECK!!");
                        }
                    } catch (IllegalChessMoveException | IllegalArgumentException c) {
                        System.out.println("Illegal move");
                        continue;
                    }
                } else if (line.charAt(0) == 'R' && line.length() == 3) {
                    try {
                        b.move(Rook.class, ChessPiece.Color.BLACK, line.substring(1));
                        if (b.isCheck(ChessPiece.Color.WHITE)) {
                            System.out.println("CHECK!!");
                        }
                    } catch (IllegalChessMoveException | IllegalArgumentException c) {
                        System.out.println("Illegal move");
                        continue;
                    }
                } else if (line.charAt(0) == 'B' && line.length() == 3) {
                    try {
                        b.move(Bishop.class, ChessPiece.Color.BLACK, line.substring(1));
                        if (b.isCheck(ChessPiece.Color.WHITE)) {
                            System.out.println("CHECK!!");
                        }
                    } catch (IllegalChessMoveException | IllegalArgumentException c) {
                        System.out.println("Illegal move");
                        continue;
                    }
                } else if (line.charAt(0) == 'N' && line.length() == 3) {
                    try {
                        b.move(Knight.class, ChessPiece.Color.BLACK, line.substring(1));
                        if (b.isCheck(ChessPiece.Color.WHITE)) {
                            System.out.println("CHECK!!");
                        }
                    } catch (IllegalChessMoveException | IllegalArgumentException c) {
                        System.out.println("Illegal move");
                        continue;
                    }
                } else if (line.length() == 2) {
                    try {
                        b.move(Pawn.class, ChessPiece.Color.BLACK, line);
                        if (b.isCheck(ChessPiece.Color.WHITE)) {
                            System.out.println("CHECK!!");
                        }
                    } catch (IllegalChessMoveException | IllegalArgumentException c) {
                        System.out.println("Illegal move");
                        continue;
                    }
                } else if (line.equals("X")) {
                    System.out.println("Game over, the WINNER is a player with WHITE figures!");
                    break;
                }
                whiteOnTheMove = true;

            }
        }

    }
}
