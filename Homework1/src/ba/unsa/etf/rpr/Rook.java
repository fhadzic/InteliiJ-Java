package ba.unsa.etf.rpr;

public class Rook extends ChessPiece{

    private String position;
    private Color color;

    public Rook(String position, Color color) {
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
        char currentPositon1 = this.position.charAt(1);
        char position0 = position.charAt(0);
        char position1 = position.charAt(1);


        if (position0 == currentPosition0) {
            this.position = position;
            return;
        } else if (position1 == currentPositon1) {
            this.position = position;
            return;
        } else {
            throw new IllegalChessMoveException("Pozicija za figuru Rook nije u ispravnom formatu!");
        }
    }
}
