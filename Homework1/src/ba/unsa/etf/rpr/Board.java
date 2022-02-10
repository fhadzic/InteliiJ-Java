package ba.unsa.etf.rpr;


import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<ChessPiece> activeFigureWhite;
    private List<ChessPiece> activeFigureBlack;


    public Board() {
        activeFigureWhite = new ArrayList<>(16);
        activeFigureBlack = new ArrayList<>(16);

        //WHITE NOT PAWN
        activeFigureWhite.add(new Rook("A1", ChessPiece.Color.WHITE));
        activeFigureWhite.add(new Rook("H1", ChessPiece.Color.WHITE));
        activeFigureWhite.add(new Knight("B1", ChessPiece.Color.WHITE));
        activeFigureWhite.add(new Knight("G1", ChessPiece.Color.WHITE));
        activeFigureWhite.add(new Bishop("C1", ChessPiece.Color.WHITE));
        activeFigureWhite.add(new Bishop("F1", ChessPiece.Color.WHITE));
        activeFigureWhite.add(new Queen("D1", ChessPiece.Color.WHITE));
        activeFigureWhite.add(new King("E1", ChessPiece.Color.WHITE));


        //BLACK NOT PAWN
        activeFigureBlack.add(new Rook("A8", ChessPiece.Color.BLACK));
        activeFigureBlack.add(new Rook("H8", ChessPiece.Color.BLACK));
        activeFigureBlack.add(new Knight("B8", ChessPiece.Color.BLACK));
        activeFigureBlack.add(new Knight("G8", ChessPiece.Color.BLACK));
        activeFigureBlack.add(new Bishop("C8", ChessPiece.Color.BLACK));
        activeFigureBlack.add(new Bishop("F8", ChessPiece.Color.BLACK));
        activeFigureBlack.add(new Queen("D8", ChessPiece.Color.BLACK));
        activeFigureBlack.add(new King("E8", ChessPiece.Color.BLACK));

        //BLACK AND WHITE PAWN
        for (int j = 0; j < 8; j++) {
            activeFigureWhite.add(new Pawn(convertPositionToString(1, j), ChessPiece.Color.WHITE));
            activeFigureBlack.add(new Pawn(convertPositionToString(6, j), ChessPiece.Color.BLACK));
        }

    }


    public boolean isCheck(ChessPiece.Color color) {

        if (color == ChessPiece.Color.WHITE) {

            for (int i = 0; i < activeFigureWhite.size(); i++) {
                if (activeFigureWhite.get(i) instanceof King) {
                    String positionKingWhite = activeFigureWhite.get(i).getPosition();

                    for (int j = 0; j < activeFigureBlack.size(); j++) {

                        if (activeFigureBlack.get(j) instanceof Queen) {
                            if (isCheckOfQueenBlack(positionKingWhite, j)) {
                                return true;
                            }
                        } else if (activeFigureBlack.get(j) instanceof Knight) {
                            if (isCheckOfKnightOrKing(activeFigureBlack, positionKingWhite, j)) {
                                return true;
                            }
                        } else if (activeFigureBlack.get(j) instanceof Bishop) {
                            if (isCheckOfBishopBlack(positionKingWhite, j)) {
                                return true;
                            }
                        } else if (activeFigureBlack.get(j) instanceof Rook) {
                            if (isCheckOfRookBlack(positionKingWhite, j)) {
                                return true;
                            }

                        } else if (activeFigureBlack.get(j) instanceof Pawn) {
                            Boolean b = isCheckOfPawnBlack(positionKingWhite, j);
                            if (isCheckOfPawnBlack(positionKingWhite, j)) {
                                return true;
                            }
                        } else if (activeFigureBlack.get(j) instanceof King) {
                            if (isCheckOfKnightOrKing(activeFigureBlack, positionKingWhite, j)) {
                                return true;
                            }
                        }

                    }

                    break;
                }

            }


        } else {

            for (int i = 0; i < activeFigureBlack.size(); i++) {
                if (activeFigureBlack.get(i) instanceof King) {
                    String positionKingBlack = activeFigureBlack.get(i).getPosition();

                    for (int j = 0; j < activeFigureWhite.size(); j++) {

                        if (activeFigureWhite.get(j) instanceof Queen) {
                            if (isCheckOfQueenWhite(positionKingBlack, j)) {
                                return true;
                            }
                        } else if (activeFigureWhite.get(j) instanceof Knight) {
                            if (isCheckOfKnightOrKing(activeFigureWhite, positionKingBlack, j)) {
                                return true;
                            }
                        } else if (activeFigureWhite.get(j) instanceof Bishop) {
                            if (isCheckOfBishopWhite(positionKingBlack, j)) {
                                return true;
                            }
                        } else if (activeFigureWhite.get(j) instanceof Rook) {
                            if (isCheckOfRookWhite(positionKingBlack, j)) {
                                return true;
                            }
                        } else if (activeFigureWhite.get(j) instanceof Pawn) {
                            if (isCheckOfPawnWhite(positionKingBlack, j)) {
                                return true;
                            }
                        } else if (activeFigureWhite.get(j) instanceof King) {
                            if (isCheckOfKnightOrKing(activeFigureWhite, positionKingBlack, j)) {
                                return true;
                            }
                        }

                    }

                }

            }

        }

        return false;
    }


    public void move(Class<? extends ChessPiece> type, ChessPiece.Color color, String position) throws IllegalChessMoveException {

        if (color == ChessPiece.Color.WHITE) {

            int ofTheSameFigures = 0;
            int i;
            for (i = 0; i < activeFigureWhite.size(); i++) {
                if (activeFigureWhite.get(i).getClass() == type) {

                    if ((activeFigureWhite.get(i) instanceof Knight) || (activeFigureWhite.get(i) instanceof King)) {

                        if (isKnightOrKingMoveLegally(position, i, ChessPiece.Color.WHITE)) {
                            return;
                        } else {
                            if (activeFigureWhite.get(i) instanceof King) {
                                throw new IllegalChessMoveException("Ne postoji figura za koju je potez LEGALAN!");
                            } else {
                                ofTheSameFigures++;
                                if (ofTheSameFigures == 2) {
                                    throw new IllegalChessMoveException("Ne postoji figura za koju je potez LEGALAN!");
                                }
                            }
                        }

                    } else if (activeFigureWhite.get(i) instanceof Pawn) {

                        if (isPawnMoveLegally(position, i, ChessPiece.Color.WHITE)) {
                            return;
                        } else {
                            ofTheSameFigures++;
                            if (ofTheSameFigures == 8) {
                                throw new IllegalChessMoveException("Ne postoji figura za koju je potez LEGALAN!");
                            }
                        }

                    } else if (activeFigureWhite.get(i) instanceof Bishop) {

                        if (isBishopWhiteMoveLegally(position, i)) {
                            return;
                        } else {
                            ofTheSameFigures++;
                            if (ofTheSameFigures == 2) {
                                throw new IllegalChessMoveException("Ne postoji figura za koju je potez LEGALAN!");
                            }
                        }

                    } else if (activeFigureWhite.get(i) instanceof Rook) {

                        if (isRookWhiteMoveLegally(position, i)) {
                            return;
                        } else {
                            ofTheSameFigures++;
                            if (ofTheSameFigures == 2) {
                                throw new IllegalChessMoveException("Ne postoji figura za koju je potez LEGALAN!");
                            }
                        }

                    } else if (activeFigureWhite.get(i) instanceof Queen) {
                        if (isQueenWhiteMoveLegally(position, i)) {
                            return;
                        } else {
                            throw new IllegalChessMoveException("Ne postoji figura za koju je potez LEGALAN!");
                        }
                    }
                }
            }

            if (i == activeFigureWhite.size()) {
                throw new IllegalChessMoveException("Ne postoji aktivna figura za koju je potez LEGALAN!");
            }

        } else {

            int ofTheSameFigures = 0, i;
            for (i = 0; i < activeFigureBlack.size(); i++) {
                if (activeFigureBlack.get(i).getClass() == type) {

                    if ((activeFigureBlack.get(i) instanceof Knight) || (activeFigureBlack.get(i) instanceof King)) {


                        if (isKnightOrKingMoveLegally(position, i, ChessPiece.Color.BLACK)) {
                            return;
                        } else {
                            if (activeFigureWhite.get(i) instanceof King) {
                                throw new IllegalChessMoveException("Ne postoji figura za koju je potez LEGALAN!");
                            } else {
                                ofTheSameFigures++;
                                if (ofTheSameFigures == 2) {
                                    throw new IllegalChessMoveException("Ne postoji figura za koju je potez LEGALAN!");
                                }
                            }
                        }


                    } else if (activeFigureBlack.get(i) instanceof Pawn) {


                        if (isPawnMoveLegally(position, i, ChessPiece.Color.BLACK)) {
                            return;
                        } else {
                            ofTheSameFigures++;
                            if (ofTheSameFigures == 8) {
                                throw new IllegalChessMoveException("Ne postoji figura za koju je potez LEGALAN!");
                            }
                        }


                    } else if (activeFigureBlack.get(i) instanceof Bishop) {


                        if (isBishopBlackMoveLegally(position, i)) {
                            return;
                        } else {
                            ofTheSameFigures++;
                            if (ofTheSameFigures == 2) {
                                throw new IllegalChessMoveException("Ne postoji figura za koju je potez LEGALAN!");
                            }
                        }


                    } else if (activeFigureBlack.get(i) instanceof Rook) {

                        if (isRookBlackMoveLegally(position, i)) {
                            return;
                        } else {
                            ofTheSameFigures++;
                            if (ofTheSameFigures == 2) {
                                throw new IllegalChessMoveException("Ne postoji figura za koju je potez LEGALAN!");
                            }
                        }

                    } else if (activeFigureBlack.get(i) instanceof Queen) {

                        if (isQueenBlackMoveLegally(position, i)) {
                            return;
                        } else {
                            throw new IllegalChessMoveException("Ne postoji figura za koju je potez LEGALAN!");
                        }

                    }

                }
            }

            if (i == activeFigureBlack.size()) {
                throw new IllegalChessMoveException("Ne postoji aktivna figura za koju je potez LEGALAN!");
            }

        }

    }


    public void move(String oldPosition, String newPosition) throws IllegalChessMoveException {
        oldPosition = oldPosition.toUpperCase();
        int i;
        for (i = 0; i < activeFigureWhite.size(); i++) {

            if (activeFigureWhite.get(i).getPosition().equals(oldPosition)) {

                if (activeFigureWhite.get(i) instanceof Pawn) {
                    if (isPawnLegally(newPosition, i, activeFigureWhite, ChessPiece.Color.WHITE)) {
                        return;
                    } else {
                        throw new IllegalChessMoveException("Za WHITE figuru Pawn, na poziciji " + activeFigureWhite.get(i).getPosition() + ", potez nije legalan!");
                    }
                }else if(activeFigureWhite.get(i) instanceof Rook){
                    if (isRookLegally(newPosition, i, activeFigureWhite, ChessPiece.Color.WHITE)) {
                        return;
                    } else {
                        throw new IllegalChessMoveException("Za WHITE figuru Rook, na poziciji " + activeFigureWhite.get(i).getPosition() + ", potez nije legalan!");
                    }
                }else if(activeFigureWhite.get(i) instanceof Bishop){
                    if (isBishopLegally(newPosition, i, activeFigureWhite, ChessPiece.Color.WHITE)) {
                        return;
                    } else {
                        throw new IllegalChessMoveException("Za WHITE figuru Bishop, na poziciji " + activeFigureWhite.get(i).getPosition() + ", potez nije legalan!");
                    }
                }else if(activeFigureWhite.get(i) instanceof Knight){
                    if (isKnightLegally(newPosition, i, activeFigureWhite, ChessPiece.Color.WHITE)) {
                        return;
                    } else {
                        throw new IllegalChessMoveException("Za WHITE figuru Knight, na poziciji " + activeFigureWhite.get(i).getPosition() + ", potez nije legalan!");
                    }
                }else if(activeFigureWhite.get(i) instanceof Queen){
                    if (isQueenLegally(newPosition, i, activeFigureWhite, ChessPiece.Color.WHITE)) {
                        return;
                    } else {
                        throw new IllegalChessMoveException("Za WHITE figuru Queen, na poziciji " + activeFigureWhite.get(i).getPosition() + ", potez nije legalan!");
                    }
                }else if(activeFigureWhite.get(i) instanceof King){
                    if (isKingLegally(newPosition, i, activeFigureWhite, ChessPiece.Color.WHITE)) {
                        return;
                    } else {
                        throw new IllegalChessMoveException("Za WHITE figuru King, na poziciji " + activeFigureWhite.get(i).getPosition() + ", potez nije legalan!");
                    }
                }
            }

        }

        int j;
        for (j = 0; j < activeFigureBlack.size(); j++) {

            if (activeFigureBlack.get(j).getPosition().equals(oldPosition)) {

                if (activeFigureBlack.get(j) instanceof Pawn) {

                    if (isPawnLegally(newPosition, j, activeFigureBlack, ChessPiece.Color.BLACK)) {
                        return;
                    } else {
                        throw new IllegalChessMoveException("Za BLACK figuru Pawn, na poziciji " + activeFigureWhite.get(j).getPosition() + ", potez nije legalan!");
                    }

                }else if(activeFigureBlack.get(j) instanceof Rook){
                    if (isRookLegally(newPosition, j, activeFigureBlack, ChessPiece.Color.BLACK)) {
                        return;
                    } else {
                        throw new IllegalChessMoveException("Za BLACK figuru Rook, na poziciji " + activeFigureWhite.get(j).getPosition() + ", potez nije legalan!");
                    }
                }else if(activeFigureBlack.get(j) instanceof Bishop){
                    if (isBishopLegally(newPosition, j, activeFigureBlack, ChessPiece.Color.BLACK)) {
                        return;
                    } else {
                        throw new IllegalChessMoveException("Za BLACK figuru Bishop, na poziciji " + activeFigureWhite.get(j).getPosition() + ", potez nije legalan!");
                    }
                }else if(activeFigureBlack.get(j) instanceof Knight){
                    if (isKnightLegally(newPosition, j, activeFigureBlack, ChessPiece.Color.BLACK)) {
                        return;
                    } else {
                        throw new IllegalChessMoveException("Za BLACK figuru Knight, na poziciji " + activeFigureWhite.get(j).getPosition() + ", potez nije legalan!");
                    }
                }else if(activeFigureBlack.get(j) instanceof Queen){
                    if (isQueenLegally(newPosition, j, activeFigureBlack, ChessPiece.Color.BLACK)) {
                        return;
                    } else {
                        throw new IllegalChessMoveException("Za BLACK figuru Queen, na poziciji " + activeFigureWhite.get(j).getPosition() + ", potez nije legalan!");
                    }
                }else if(activeFigureBlack.get(j) instanceof King){
                    if (isKingLegally(newPosition, j, activeFigureBlack, ChessPiece.Color.BLACK)) {
                        return;
                    } else {
                        throw new IllegalChessMoveException("Za BLACK figuru King, na poziciji " + activeFigureWhite.get(j).getPosition() + ", potez nije legalan!");
                    }
                }
            }

        }

        if (i == activeFigureWhite.size() && j == activeFigureBlack.size()) {
            throw new IllegalChessMoveException("Na poziciji " + oldPosition + ", NE nalazi se figura!");
        }

    }


    //MOVE WITH 2 VARIABLES
    //PAWN
    private boolean isPawnLegally(String newPosition, int i, List<ChessPiece> listFigures, ChessPiece.Color color) throws IllegalChessMoveException {
        try {
            char beforePosition0 = listFigures.get(i).getPosition().charAt(0);
            char beforePosition1 = listFigures.get(i).getPosition().charAt(1);

            listFigures.get(i).move(newPosition);

            returnPawnOnBeforePosition(i, beforePosition0, beforePosition1, color);

            if (color == ChessPiece.Color.WHITE) {
                if (isPawnMoveLegally(newPosition, i, ChessPiece.Color.WHITE)) {
                    return true;
                }
            } else {
                if (isPawnMoveLegally(newPosition, i, ChessPiece.Color.BLACK)) {
                    return true;
                }
            }
        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Pawn nije ispravna!")) {
                if (color == ChessPiece.Color.WHITE) {
                    throw new IllegalChessMoveException("Za WHITE figuru Pawn, na poziciji " + activeFigureWhite.get(i).getPosition() + ", potez nije legalan!");
                } else {
                    throw new IllegalChessMoveException("Za BLACK figuru Pawn, na poziciji " + activeFigureBlack.get(i).getPosition() + ", potez nije legalan!");
                }
            } else {
                throw new IllegalChessMoveException("Na poziciji " + newPosition + ", nalazi se figura iste boje!");
            }
        }
        return false;
    }


    private boolean isRookLegally(String newPosition, int i, List<ChessPiece> listFigures, ChessPiece.Color color) throws IllegalChessMoveException {
        try {
            String beforePosition = listFigures.get(i).getPosition();

            listFigures.get(i).move(newPosition);

            listFigures.get(i).move(beforePosition);

            if (color == ChessPiece.Color.WHITE) {
                if (isRookWhiteMoveLegally(newPosition, i)) {
                    return true;
                }
            } else {
                if (isRookBlackMoveLegally(newPosition, i)) {
                    return true;
                }
            }
        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Rook nije u ispravnom formatu!")) {
                if (color == ChessPiece.Color.WHITE) {
                    throw new IllegalChessMoveException("Za WHITE figuru Rook, na poziciji " + activeFigureWhite.get(i).getPosition() + ", potez nije legalan!");
                } else {
                    throw new IllegalChessMoveException("Za BLACK figuru Rook, na poziciji " + activeFigureBlack.get(i).getPosition() + ", potez nije legalan!");
                }
            } else {
                throw new IllegalChessMoveException("Na poziciji " + newPosition + ", nalazi se figura iste boje!");
            }
        }
        return false;
    }


    private boolean isBishopLegally(String newPosition, int i, List<ChessPiece> listFigures, ChessPiece.Color color) throws IllegalChessMoveException {
        try {
            String beforePosition = listFigures.get(i).getPosition();

            listFigures.get(i).move(newPosition);

            listFigures.get(i).move(beforePosition);

            if (color == ChessPiece.Color.WHITE) {
                if (isBishopWhiteMoveLegally(newPosition, i)) {
                    return true;
                }
            } else {
                if (isBishopBlackMoveLegally(newPosition, i)) {
                    return true;
                }
            }
        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Bishop nije ispravna!")) {
                if (color == ChessPiece.Color.WHITE) {
                    throw new IllegalChessMoveException("Za WHITE figuru Bishop, na poziciji " + activeFigureWhite.get(i).getPosition() + ", potez nije legalan!");
                } else {
                    throw new IllegalChessMoveException("Za BLACK figuru Bishop, na poziciji " + activeFigureBlack.get(i).getPosition() + ", potez nije legalan!");
                }
            } else {
                throw new IllegalChessMoveException("Na poziciji " + newPosition + ", nalazi se figura iste boje!");
            }
        }
        return false;
    }



    private boolean isKnightLegally(String newPosition, int i, List<ChessPiece> listFigures, ChessPiece.Color color) throws IllegalChessMoveException {
        try {
            String beforePosition = listFigures.get(i).getPosition();

            listFigures.get(i).move(newPosition);

            listFigures.get(i).move(beforePosition);

            if (color == ChessPiece.Color.WHITE) {
                if (isKnightOrKingMoveLegally(newPosition, i, ChessPiece.Color.WHITE)) {
                    return true;
                }
            } else {
                if (isKnightOrKingMoveLegally(newPosition, i, ChessPiece.Color.BLACK)) {
                    return true;
                }
            }
        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Knight nije ispravna!")) {
                if (color == ChessPiece.Color.WHITE) {
                    throw new IllegalChessMoveException("Za WHITE figuru Knight, na poziciji " + activeFigureWhite.get(i).getPosition() + ", potez nije legalan!");
                } else {
                    throw new IllegalChessMoveException("Za BLACK figuru Knight, na poziciji " + activeFigureBlack.get(i).getPosition() + ", potez nije legalan!");
                }
            } else {
                throw new IllegalChessMoveException("Na poziciji " + newPosition + ", nalazi se figura iste boje!");
            }
        }
        return false;
    }


    private boolean isQueenLegally(String newPosition, int i, List<ChessPiece> listFigures, ChessPiece.Color color) throws IllegalChessMoveException {
        try {
            String beforePosition = listFigures.get(i).getPosition();

            listFigures.get(i).move(newPosition);

            listFigures.get(i).move(beforePosition);

            if (color == ChessPiece.Color.WHITE) {
                if (isQueenWhiteMoveLegally(newPosition, i)) {
                    return true;
                }
            } else {
                if (isQueenBlackMoveLegally(newPosition, i)) {
                    return true;
                }
            }
        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Queen nije u ispravnom formatu!")) {
                if (color == ChessPiece.Color.WHITE) {
                    throw new IllegalChessMoveException("Za WHITE figuru Queen, na poziciji " + activeFigureWhite.get(i).getPosition() + ", potez nije legalan!");
                } else {
                    throw new IllegalChessMoveException("Za BLACK figuru Queen, na poziciji " + activeFigureBlack.get(i).getPosition() + ", potez nije legalan!");
                }
            } else {
                throw new IllegalChessMoveException("Na poziciji " + newPosition + ", nalazi se figura iste boje!");
            }
        }
        return false;
    }



    private boolean isKingLegally(String newPosition, int i, List<ChessPiece> listFigures, ChessPiece.Color color) throws IllegalChessMoveException {
        try {
            String beforePosition = listFigures.get(i).getPosition();

            listFigures.get(i).move(newPosition);

            listFigures.get(i).move(beforePosition);

            if (color == ChessPiece.Color.WHITE) {
                if (isKnightOrKingMoveLegally(newPosition, i, ChessPiece.Color.WHITE)) {
                    return true;
                }
            } else {
                if (isKnightOrKingMoveLegally(newPosition, i, ChessPiece.Color.BLACK)) {
                    return true;
                }
            }
        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru King nije ispravna!")) {
                if (color == ChessPiece.Color.WHITE) {
                    throw new IllegalChessMoveException("Za WHITE figuru King, na poziciji " + activeFigureWhite.get(i).getPosition() + ", potez nije legalan!");
                } else {
                    throw new IllegalChessMoveException("Za BLACK figuru King, na poziciji " + activeFigureBlack.get(i).getPosition() + ", potez nije legalan!");
                }
            } else {
                throw new IllegalChessMoveException("Na poziciji " + newPosition + ", nalazi se figura iste boje!");
            }
        }
        return false;
    }


    //isCHECK
    //QUEEN
    private boolean isCheckOfQueenBlack(String positionKingWhite, int j) {
        try {
            String beforePosition = activeFigureBlack.get(j).getPosition();

            activeFigureBlack.get(j).move(positionKingWhite);

            if (!isLegallyPathForRook(positionKingWhite, beforePosition, j, ChessPiece.Color.BLACK)) {
                return false;
            }
            if (!isLegallyPathForBishop(positionKingWhite, beforePosition, j, ChessPiece.Color.BLACK)) {
                return false;
            }

            activeFigureBlack.get(j).move(beforePosition);

        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Queen nije u ispravnom formatu!")) return false;
        }
        return true;
    }


    private boolean isCheckOfQueenWhite(String positionKingBlack, int j) {
        try {
            String beforePosition = activeFigureWhite.get(j).getPosition();

            activeFigureWhite.get(j).move(positionKingBlack);

            if (!isLegallyPathForRook(positionKingBlack, beforePosition, j, ChessPiece.Color.WHITE)) {
                return false;
            }
            if (!isLegallyPathForBishop(positionKingBlack, beforePosition, j, ChessPiece.Color.WHITE)) {
                return false;
            }

            activeFigureWhite.get(j).move(beforePosition);

        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Queen nije u ispravnom formatu!")) return false;
        }
        return true;
    }


    //BISHOP
    private boolean isCheckOfBishopBlack(String positionKingWhite, int j) {
        try {
            String beforePosition = activeFigureBlack.get(j).getPosition();

            activeFigureBlack.get(j).move(positionKingWhite);

            if(!isLegallyPathForBishop(positionKingWhite, beforePosition, j, ChessPiece.Color.BLACK)){
                return false;
            }

            activeFigureBlack.get(j).move(beforePosition);

        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Bishop nije ispravna!")) {
                return false;
            }
        }
        return true;
    }


    private boolean isCheckOfBishopWhite(String positionKingBlack, int j) {
        try {
            String beforePosition = activeFigureWhite.get(j).getPosition();

            activeFigureWhite.get(j).move(positionKingBlack);

            if(!isLegallyPathForBishop(positionKingBlack, beforePosition, j, ChessPiece.Color.WHITE)){
                return false;
            }

            activeFigureWhite.get(j).move(beforePosition);

        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Bishop nije ispravna!")) {
                return false;
            }
        }
        return true;
    }


    //ROOK
    private boolean isCheckOfRookBlack(String positionKingWhite, int j) {
        try {
            String beforePosition = activeFigureBlack.get(j).getPosition();

            activeFigureBlack.get(j).move(positionKingWhite);

            if (!isLegallyPathForRook(positionKingWhite, beforePosition, j, ChessPiece.Color.BLACK)) {
                return false;
            }

            activeFigureBlack.get(j).move(beforePosition);

        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Rook nije u ispravnom formatu!")) {
                return false;
            }
        }
        return true;
    }


    private boolean isCheckOfRookWhite(String positionKingBlack, int j) {
        try {
            String beforePosition = activeFigureWhite.get(j).getPosition();

            activeFigureWhite.get(j).move(positionKingBlack);

            if (!isLegallyPathForRook(positionKingBlack, beforePosition, j, ChessPiece.Color.WHITE)) {
                return false;
            }

            activeFigureWhite.get(j).move(beforePosition);

        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Rook nije u ispravnom formatu!")) {
                return false;
            }
        }
        return true;
    }


    //PAWN
    private boolean isCheckOfPawnBlack(String positionKingWhite, int i) {
        try {

            char beforePosition0 = activeFigureBlack.get(i).getPosition().charAt(0);
            char beforePosition1 = activeFigureBlack.get(i).getPosition().charAt(1);
            positionKingWhite = positionKingWhite.toUpperCase();
            char position0 = positionKingWhite.charAt(0);
            char position1 = positionKingWhite.charAt(1);

            activeFigureBlack.get(i).move(positionKingWhite);

            boolean isCheck = (((beforePosition0 == position0 + 1 || beforePosition0 == position0 - 1) && (position1 == beforePosition1 - 1)));

            if (isCheck) {
                returnPawnOnBeforePosition(i, beforePosition0, beforePosition1, ChessPiece.Color.BLACK);
                return true;
            }

            returnPawnOnBeforePosition(i, beforePosition0, beforePosition1, ChessPiece.Color.BLACK);

        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Pawn nije ispravna!")) {
                return false;
            }
        }
        return false;
    }


    private boolean isCheckOfPawnWhite(String positionKingBlack, int j) {
        try {

            char beforePosition0 = activeFigureWhite.get(j).getPosition().charAt(0);
            char beforePosition1 = activeFigureWhite.get(j).getPosition().charAt(1);
            positionKingBlack = positionKingBlack.toUpperCase();
            char position0 = positionKingBlack.charAt(0);
            char position1 = positionKingBlack.charAt(1);

            activeFigureWhite.get(j).move(positionKingBlack);

            boolean isCheck = (((beforePosition0 == position0 + 1 || beforePosition0 == position0 - 1) && (position1 == beforePosition1 + 1)));

            if (isCheck) {
                returnPawnOnBeforePosition(j, beforePosition0, beforePosition1, ChessPiece.Color.WHITE);
                return true;
            }

            returnPawnOnBeforePosition(j, beforePosition0, beforePosition1, ChessPiece.Color.WHITE);


        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Pawn nije ispravna!")) {
                return false;
            }
        }
        return false;
    }


    private boolean isCheckOfKnightOrKing(List<ChessPiece> listFigures, String positionKing, int j) {
        try {

            positionKing = positionKing.toUpperCase();

            String beforePosition = listFigures.get(j).getPosition();
            listFigures.get(j).move(positionKing);
            listFigures.get(j).move(beforePosition);


        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Knight nije ispravna!") || e.getMessage().equals("Pozicija za figuru King nije ispravna!")) {
                return false;
            }
        }
        return true;
    }


    //MOVE WITH 3 VARIABLES
    //KNIGHT OR KING
    private boolean isKnightOrKingMoveLegally(String position, int i, ChessPiece.Color color) throws IllegalChessMoveException {
        try {
            position = position.toUpperCase();
            List<ChessPiece> attackingFigures;
            List<ChessPiece> defenseFigures;

            if (color == ChessPiece.Color.WHITE) {
                attackingFigures = activeFigureWhite;
                defenseFigures = activeFigureBlack;
            } else {
                attackingFigures = activeFigureBlack;
                defenseFigures = activeFigureWhite;
            }

            String beforePosition = attackingFigures.get(i).getPosition();


            attackingFigures.get(i).move(position);

            if ((attackingFigures.get(i)).equalsPositionAndColor(attackingFigures)) {
                attackingFigures.get(i).move(beforePosition);
                throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
            } else {
                int indexElementToEat = attackingFigures.get(i).equalsPositionDifferentColor(defenseFigures);
                if (indexElementToEat != -1) {
                    defenseFigures.remove(indexElementToEat);
                }
            }

        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Knight nije ispravna!") || e.getMessage().equals("Pozicija za figuru King nije ispravna!")) {
                return false;
            } else {
                throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
            }
        }
        return true;
    }


    //PAWN
    private boolean isPawnMoveLegally(String position, int i, ChessPiece.Color color) throws IllegalChessMoveException {

        try {
            position = position.toUpperCase();
            String beforePosition;

            if(color == ChessPiece.Color.WHITE) {
                beforePosition = activeFigureWhite.get(i).getPosition();
                activeFigureWhite.get(i).move(position);
            }else{
                beforePosition = activeFigureBlack.get(i).getPosition();
                activeFigureBlack.get(i).move(position);
            }

            if (!isLegallyPathForPawn(position, beforePosition, i, color)) {
                return false;
            }

        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Pawn nije ispravna!")) {
                return false;
            } else {
                throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
            }
        }

        return true;
    }


    private boolean  isLegallyPathForPawn(String position, String beforePosition, int i, ChessPiece.Color colorAttack) throws IllegalChessMoveException {
        List<ChessPiece> attackingFigures;
        List<ChessPiece> defenseFigures;
        int signPath;

        if (colorAttack == ChessPiece.Color.WHITE) {
            signPath = 1;
            attackingFigures = activeFigureWhite;
            defenseFigures = activeFigureBlack;
        } else {
            signPath = -1;
            attackingFigures = activeFigureBlack;
            defenseFigures = activeFigureWhite;
        }

        char beforePosition0 = beforePosition.charAt(0);
        char beforePosition1 = beforePosition.charAt(1);
        char position0 = position.charAt(0);
        char position1 = position.charAt(1);

        int indexElementDifferentColor = attackingFigures.get(i).equalsPositionDifferentColor(defenseFigures);
        boolean positionIsDiagonal = (((beforePosition0 == position0 + 1 || beforePosition0 == position0 - 1) && (position1 == beforePosition1 + signPath)));

        if ((indexElementDifferentColor == -1) && positionIsDiagonal) {
            returnPawnOnBeforePosition(i, beforePosition0, beforePosition1, colorAttack);
            return false;

        } else if (indexElementDifferentColor != -1 && positionIsDiagonal) {
            defenseFigures.remove(indexElementDifferentColor);
            return true;
        } else if (indexElementDifferentColor != -1) {
            returnPawnOnBeforePosition(i, beforePosition0, beforePosition1, colorAttack);
            return false;

        }

        if (colorAttack == ChessPiece.Color.WHITE) {

            if (beforePosition1 == '2' &&
                    position1 != '3' &&
                    ChessPiece.isFigureOnPosition(activeFigureWhite, activeFigureBlack, String.format("%c%c", beforePosition0, (char) (beforePosition1 + 1)))) {

                returnPawnOnBeforePosition(i, beforePosition0, beforePosition1, ChessPiece.Color.WHITE);
                return false;

            } else {
                if ((activeFigureWhite.get(i)).equalsPositionAndColor(activeFigureWhite)) {
                    returnPawnOnBeforePosition(i, beforePosition0, beforePosition1, ChessPiece.Color.WHITE);
                    throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
                }
            }

        } else {
            if (beforePosition1 == '7' &&
                    position1 != '6' &&
                    ChessPiece.isFigureOnPosition(activeFigureWhite, activeFigureBlack, String.format("%c%c", beforePosition0, (char) (beforePosition1 - 1)))) {

                returnPawnOnBeforePosition(i, beforePosition0, beforePosition1, ChessPiece.Color.BLACK);
                return false;

            } else {
                if ((activeFigureBlack.get(i)).equalsPositionAndColor(activeFigureBlack)) {
                    returnPawnOnBeforePosition(i, beforePosition0, beforePosition1, ChessPiece.Color.BLACK);
                    throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
                }
            }
        }

        return true;
    }



    //BISHOP
    private boolean isBishopWhiteMoveLegally(String position, int i) throws IllegalChessMoveException {

        try {
            position = position.toUpperCase();
            String beforePosition = activeFigureWhite.get(i).getPosition();

            activeFigureWhite.get(i).move(position);

            if ((activeFigureWhite.get(i)).equalsPositionAndColor(activeFigureWhite)) {
                throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
            } else {
                if (!isLegallyPathForBishop(position, beforePosition, i, ChessPiece.Color.WHITE)) {
                    return false;
                }

                int indexElementToEat = activeFigureWhite.get(i).equalsPositionDifferentColor(activeFigureBlack);
                if (indexElementToEat != -1) {
                    activeFigureBlack.remove(activeFigureBlack.get(indexElementToEat));
                    return true;
                }
            }

        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Bishop nije ispravna!")) {
                return false;
            } else {
                throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
            }
        }
        return true;
    }


    private boolean isBishopBlackMoveLegally(String position, int i) throws IllegalChessMoveException {

        try {
            position = position.toUpperCase();
            String beforePosition = activeFigureBlack.get(i).getPosition();

            activeFigureBlack.get(i).move(position);

            if ((activeFigureBlack.get(i)).equalsPositionAndColor(activeFigureBlack)) {
                throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
            } else {

                if (!isLegallyPathForBishop(position, beforePosition, i, ChessPiece.Color.BLACK)) {
                    return false;
                }

                int indexElementToEat = activeFigureBlack.get(i).equalsPositionDifferentColor(activeFigureWhite);
                if (indexElementToEat != -1) {
                    activeFigureWhite.remove(activeFigureWhite.get(indexElementToEat));
                    return true;
                }
            }

        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Bishop nije ispravna!")) {
                return false;
            } else {
                throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
            }
        }
        return true;
    }


    private boolean isLegallyPathForBishop(String position, String beforePosition, int i, ChessPiece.Color colorAttack) throws IllegalChessMoveException {

        List<ChessPiece> attackingFigures;
        List<ChessPiece> defenseFigures;

        if (colorAttack == ChessPiece.Color.WHITE) {
            attackingFigures = activeFigureWhite;
            defenseFigures = activeFigureBlack;
        } else {
            attackingFigures = activeFigureBlack;
            defenseFigures = activeFigureWhite;
        }

        char beforePosition0 = beforePosition.charAt(0);
        char beforePosition1 = beforePosition.charAt(1);
        char position0 = position.charAt(0);
        char position1 = position.charAt(1);

        while ((position0 != beforePosition0) && (position1 != beforePosition1)) {
            //Nova pozicija gore desno, od pozicije prije. Else - dolje desno, Else - gore lijevo, Else - dolje lijevo, respektivno.
            if (position0 > beforePosition0 && position1 > beforePosition1) {
                position0--;
                position1--;
                if (ChessPiece.isFigureOnPosition(attackingFigures, defenseFigures, String.format("%c%c", position0, position1))) {
                    attackingFigures.get(i).move(String.format("%c%c", beforePosition0, beforePosition1));
                    return false;
                }
            } else if (position0 > beforePosition0) {
                position0--;
                position1++;
                if (ChessPiece.isFigureOnPosition(attackingFigures, defenseFigures, String.format("%c%c", position0, position1))) {
                    attackingFigures.get(i).move(String.format("%c%c", beforePosition0, beforePosition1));
                    return false;
                }
            } else if (position1 > beforePosition1) {
                position0++;
                position1--;
                if (ChessPiece.isFigureOnPosition(attackingFigures, defenseFigures, String.format("%c%c", position0, position1))) {
                    attackingFigures.get(i).move(String.format("%c%c", beforePosition0, beforePosition1));
                    return false;
                }
            } else {
                position0++;
                position1++;
                if (ChessPiece.isFigureOnPosition(attackingFigures, defenseFigures, String.format("%c%c", position0, position1))) {
                    attackingFigures.get(i).move(String.format("%c%c", beforePosition0, beforePosition1));
                    return false;
                }
            }
        }

        return true;
    }


    //ROOK
    private boolean isRookWhiteMoveLegally(String position, int i) throws IllegalChessMoveException {

        try {
            position = position.toUpperCase();
            String beforePosition = activeFigureWhite.get(i).getPosition();

            activeFigureWhite.get(i).move(position);

            if ((activeFigureWhite.get(i)).equalsPositionAndColor(activeFigureWhite)) {
                throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
            } else {
                if (!isLegallyPathForRook(position, beforePosition, i, ChessPiece.Color.WHITE)) {
                    return false;
                }

                int indexElementToEat = activeFigureWhite.get(i).equalsPositionDifferentColor(activeFigureBlack);
                if (indexElementToEat != -1) {
                    activeFigureBlack.remove(activeFigureBlack.get(indexElementToEat));
                    return true;
                }
            }

        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Rook nije u ispravnom formatu!")) {
                return false;
            } else {
                throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
            }
        }

        return true;
    }


    private boolean isRookBlackMoveLegally(String position, int i) throws IllegalChessMoveException {

        try {
            String beforePosition = activeFigureBlack.get(i).getPosition();
            position = position.toUpperCase();

            activeFigureBlack.get(i).move(position);

            if ((activeFigureBlack.get(i)).equalsPositionAndColor(activeFigureBlack)) {
                throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
            } else {
                if (!isLegallyPathForRook(position, beforePosition, i, ChessPiece.Color.BLACK)) {
                    return false;
                }

                int indexElementToEat = activeFigureBlack.get(i).equalsPositionDifferentColor(activeFigureWhite);
                if (indexElementToEat != -1) {
                    activeFigureWhite.remove(activeFigureWhite.get(indexElementToEat));
                    return true;
                }
            }

        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Rook nije u ispravnom formatu!")) {
                return false;
            } else {
                throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
            }
        }

        return true;
    }


    private boolean isLegallyPathForRook(String position, String beforePosition, int i, ChessPiece.Color colorAtteck) throws IllegalChessMoveException {

        List<ChessPiece> attackingFigures;
        List<ChessPiece> defenseFigures;

        if (colorAtteck == ChessPiece.Color.WHITE) {
            attackingFigures = activeFigureWhite;
            defenseFigures = activeFigureBlack;
        } else {
            attackingFigures = activeFigureBlack;
            defenseFigures = activeFigureWhite;
        }

        char beforePosition0 = beforePosition.charAt(0);
        char beforePosition1 = beforePosition.charAt(1);
        char position0 = position.charAt(0);
        char position1 = position.charAt(1);

        if (position0 == beforePosition0) {

            while (position1 != beforePosition1) {
                //if - Nova pozicija poviš prethodne, else - ispod
                if (position1 > beforePosition1) {
                    position1--;
                    if (ChessPiece.isFigureOnPosition(attackingFigures, defenseFigures, String.format("%c%c", position0, position1))) {
                        attackingFigures.get(i).move(String.format("%c%c", beforePosition0, beforePosition1));
                        return false;
                    }
                } else {
                    position1++;
                    if (ChessPiece.isFigureOnPosition(attackingFigures, defenseFigures, String.format("%c%c", position0, position1))) {
                        attackingFigures.get(i).move(String.format("%c%c", beforePosition0, beforePosition1));
                        return false;
                    }
                }
            }

        } else if (beforePosition1 == position1) {

            while (position0 != beforePosition0) {
                //if - Nova pozicija desno od prethodne, else - lijevo
                if (position0 > beforePosition0) {
                    position0--;
                    if (ChessPiece.isFigureOnPosition(attackingFigures, defenseFigures, String.format("%c%c", position0, position1))) {
                        attackingFigures.get(i).move(String.format("%c%c", beforePosition0, beforePosition1));
                        return false;
                    }
                } else {
                    position0++;
                    if (ChessPiece.isFigureOnPosition(attackingFigures, defenseFigures, String.format("%c%c", position0, position1))) {
                        attackingFigures.get(i).move(String.format("%c%c", beforePosition0, beforePosition1));
                        return false;
                    }
                }
            }

        }
        return true;
    }


    //QUEEN
    private boolean isQueenWhiteMoveLegally(String position, int i) throws IllegalChessMoveException {

        try {
            String beforePosition = activeFigureWhite.get(i).getPosition();
            position = position.toUpperCase();

            activeFigureWhite.get(i).move(position);

            if ((activeFigureWhite.get(i)).equalsPositionAndColor(activeFigureWhite)) {
                throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
            } else {

                if (!isLegallyPathForRook(position, beforePosition, i, ChessPiece.Color.WHITE)) {
                    return false;
                }

                if (!isLegallyPathForBishop(position, beforePosition, i, ChessPiece.Color.WHITE)) {
                    return false;
                }

                int indexElementToEat = activeFigureWhite.get(i).equalsPositionDifferentColor(activeFigureBlack);
                if (indexElementToEat != -1) {
                    activeFigureBlack.remove(indexElementToEat);
                    return true;
                }

            }
        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Queen nije u ispravnom formatu!")) {
                return false;
            } else {
                throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
            }
        }
        return true;
    }


    private boolean isQueenBlackMoveLegally(String position, int i) throws IllegalChessMoveException {
        try {
            String beforePosition = activeFigureBlack.get(i).getPosition();
            position = position.toUpperCase();

            activeFigureBlack.get(i).move(position);

            if ((activeFigureBlack.get(i)).equalsPositionAndColor(activeFigureBlack)) {
                throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
            } else {
                if (!isLegallyPathForRook(position, beforePosition, i, ChessPiece.Color.BLACK)) {
                    return false;
                }
                if (!isLegallyPathForBishop(position, beforePosition, i, ChessPiece.Color.BLACK)) {
                    return false;
                }

                int indexElementToEat = activeFigureBlack.get(i).equalsPositionDifferentColor(activeFigureWhite);
                if (indexElementToEat != -1) {
                    activeFigureWhite.remove(activeFigureWhite.get(indexElementToEat));
                    return true;
                }
            }

        } catch (IllegalChessMoveException e) {
            if (e.getMessage().equals("Pozicija za figuru Queen nije u ispravnom formatu!")) {
                return false;
            } else {
                throw new IllegalChessMoveException("Na poziciji " + position + ", nalazi se figura iste boje!");
            }
        }
        return true;
    }


    private void returnPawnOnBeforePosition(int i, char beforePosition0, char beforePosition1, ChessPiece.Color color) {
        if (color == ChessPiece.Color.WHITE) {
            activeFigureWhite.remove(i);
            activeFigureWhite.add(i, new Pawn(String.format("%c%c", beforePosition0, beforePosition1), color));
        } else {
            activeFigureBlack.remove(i);
            activeFigureBlack.add(i, new Pawn(String.format("%c%c", beforePosition0, beforePosition1), color));
        }
    }


    public static String convertPositionToString(int i, int j) {
        return String.format("%c%d", (char) (j + 65), (i + 1));
    }


    protected int getNumberOfActiveWhiteFigures() {
        return activeFigureWhite.size();
    }

    protected int getNumberOfActiveBlackFigures() {
        return activeFigureBlack.size();
    }

}