package ba.unsa.etf.rpr;

public class Queen extends ChessPiece {

    private String position;
    private Color color;

    public Queen(String position, Color color) {
        position = position.toUpperCase();
        correctnessOfPositions(position);
        this.position = position;
        this.color = color;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void move(String position) throws IllegalChessMoveException {
        position = position.toUpperCase();
        correctnessOfPositions(position);

        if (this.position.equals(position)) throw new IllegalArgumentException("Pozicija ne promijenjena!");

        char currentPosition0 = this.position.charAt(0);
        char currentPosition1 = this.position.charAt(1);
        char position0 = position.charAt(0);
        char position1 = position.charAt(1);


        if (position0 == currentPosition0) {
            this.position = position;
            return;
        } else if (position1 == currentPosition1) {
            this.position = position;
            return;
        } else if (Math.abs(currentPosition0 - position0) == Math.abs(currentPosition1 - position1)) {
            this.position = position;
        } else {
            throw new IllegalChessMoveException("Pozicija za figuru Queen nije u ispravnom formatu!");
        }
    }
}
