package ba.unsa.etf.rpr;

import java.util.List;

public abstract class ChessPiece {

    public static enum Color {
        BLACK,
        WHITE;
    }

    public abstract String getPosition();

    public abstract Color getColor();

    public abstract void move(String position) throws IllegalChessMoveException;

    protected void correctnessOfPositions(String position) {
        if (position.length() != 2 ||
                !(position.charAt(0) >= 'A' && position.charAt(0) <= 'H') ||
                !(position.charAt(1) >= '1' && position.charAt(1) <= '8')) {
            throw new IllegalArgumentException("Pozicija van table, ili nije u ispravnom formatu!");
        }
    }


    protected boolean equalsPositionAndColor(List<ChessPiece> figure) {

        for (int i = 0; i < figure.size(); i++) {
            if ((figure.get(i).getPosition().equals(this.getPosition())) && (this.hashCode() != figure.get(i).hashCode())) {
                return true;
            }
        }
        return false;
    }

    protected int equalsPositionDifferentColor(List<ChessPiece> figure) {
        for (int i = 0; i < figure.size(); i++) {
            if (figure.get(i).getPosition().equals(this.getPosition())) {
                return i;
            }
        }
        return -1;
    }

    protected static boolean isFigureOnPosition(List<ChessPiece> figuresWhite, List<ChessPiece> figuresBlack, String position) {
        for (int i = 0; i < figuresWhite.size(); i++) {
            if (figuresWhite.get(i).getPosition().equals(position)) {
                return true;
            }
        }
        for (int i = 0; i < figuresBlack.size(); i++) {
            if (figuresBlack.get(i).getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

}
