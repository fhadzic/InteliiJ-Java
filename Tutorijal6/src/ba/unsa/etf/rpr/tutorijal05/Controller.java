package ba.unsa.etf.rpr.tutorijal05;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;

import java.math.BigDecimal;
import java.math.MathContext;


public class Controller {

    private SimpleStringProperty display;
    private boolean commaClick = false;
    private double firstOperand = 0;
    private double secoundOperand = 0;
    private String operator;

    public Controller() {
        display = new SimpleStringProperty("0");
    }


    public String getDisplay() {
        return display.get();
    }

    public SimpleStringProperty displayProperty() {
        return display;
    }

    public void setDisplay(String display) {
        this.display.set(display);
    }


    public void buttonPercentClick(ActionEvent actionEvent) {
        firstOperand = Double.parseDouble(display.get());
        if(firstOperand==0){
            display.set("0");
        }else{
            firstOperand /= 100;
            display.set(String.valueOf(firstOperand));
        }
        commaClick = false;
    }

    public void buttonDivideClick(ActionEvent actionEvent) {
        operatorClick();
        operator = "/";
    }

    public void buttonPlusClick(ActionEvent actionEvent) {
        operatorClick();
        operator = "+";
    }

    public void buttonDecreaseClick(ActionEvent actionEvent) {
        operatorClick();
        operator = "-";
    }

    public void buttonMultipleClick(ActionEvent actionEvent) {
        operatorClick();
        operator = "x";
    }

    private void operatorClick(){
        firstOperand = Double.parseDouble(display.get());
        display.set("0");
        commaClick = false;
    }


    public void buttonEqualsClick(ActionEvent actionEvent) {
        secoundOperand = Double.parseDouble(display.get());
        if(operator.equals("+")){
            firstOperand += secoundOperand;
        }else if(operator.equals("-")){
            firstOperand -= secoundOperand;
        }else if(operator.equals("x")){
            firstOperand *= secoundOperand;
        }else if(operator.equals("/")){
            if(secoundOperand != 0) {
                firstOperand /= secoundOperand;
            }else{
                System.out.println("Nije moguce dijeliti sa nulom!!!");
                firstOperand = 0;
                secoundOperand = 0;
                return;
            }
        }
        display.set(String.valueOf(firstOperand));
        commaClick = false;
    }

    public void buttonZeroClick(ActionEvent actionEvent) {
        if(commaClick) {
            display.set(display.get()+String.valueOf(0));
        }else{
            int number;
            number = numberForDisplay(Integer.parseInt(display.get()), 0);
            display.set(String.valueOf(number));
        }
    }

    public void buttonOneClick(ActionEvent actionEvent) {
        if(commaClick) {
            display.set(display.get()+String.valueOf(1));
        }else{
            int number = numberForDisplay(Integer.parseInt(display.get()), 1);
            display.set(String.valueOf(number));
        }
    }

    public void buttonTwoClick(ActionEvent actionEvent) {
        if(commaClick) {
            display.set(display.get()+String.valueOf(2));
        }else {
            int number = numberForDisplay(Integer.parseInt(display.get()), 2);
            display.set(String.valueOf(number));
        }
    }

    public void buttonThreeClick(ActionEvent actionEvent) {
        if(commaClick) {
            display.set(display.get()+String.valueOf(3));
        }else {
            int number = numberForDisplay(Integer.parseInt(display.get()), 3);
            display.set(String.valueOf(number));
        }
    }

    public void buttonFourClick(ActionEvent actionEvent) {
        if(commaClick) {
            display.set(display.get()+String.valueOf(4));
        }else {
            int number = numberForDisplay(Integer.parseInt(display.get()), 4);
            display.set(String.valueOf(number));
        }
    }

    public void buttonFiveClick(ActionEvent actionEvent) {
        if(commaClick) {
            display.set(display.get()+String.valueOf(5));
        }else {
            int number = numberForDisplay(Integer.parseInt(display.get()), 5);
            display.set(String.valueOf(number));
        }
    }

    public void buttonSixClick(ActionEvent actionEvent) {
        if(commaClick) {
            display.set(display.get()+String.valueOf(6));
        }else {
            int number = numberForDisplay(Integer.parseInt(display.get()), 6);
            display.set(String.valueOf(number));
        }
    }


    public void buttonSevenClick(ActionEvent actionEvent) {
        if(commaClick) {
            display.set(display.get()+String.valueOf(7));
        }else {
            double number = numberForDisplay(Integer.parseInt(display.get()), 7);
            display.set(String.valueOf(number));
        }
    }

    public void buttonEightClick(ActionEvent actionEvent) {
        if(commaClick) {
            display.set(display.get()+String.valueOf(8));
        }else {
            int number = numberForDisplay(Integer.parseInt(display.get()), 8);
            display.set(String.valueOf(number));
        }
    }

    public void buttonElevenClick(ActionEvent actionEvent) {
        if(commaClick) {
            display.set(display.get()+String.valueOf(9));
        }else {
            int number = numberForDisplay(Integer.parseInt(display.get()), 9);
            display.set(String.valueOf(number));
        }
    }

    public void buttonCommaClick(ActionEvent actionEvent) {
        commaClick = true;
        display.set(String.format("%d.", Integer.parseInt(display.get())));
    }

    int numberForDisplay(int numberFromDisplay, int btnClick){
        if(numberFromDisplay != 0){
            numberFromDisplay = numberFromDisplay * 10;
            numberFromDisplay += btnClick;
        }else {
            numberFromDisplay = btnClick;
        }

        return numberFromDisplay;
    }

}
