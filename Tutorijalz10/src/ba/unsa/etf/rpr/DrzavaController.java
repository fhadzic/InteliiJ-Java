package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DrzavaController {

    public TextField fieldNaziv;
    public ChoiceBox<Grad> choiceGrad;

    public ObservableList<Grad> listaGradova;
    private Drzava drzava;

    public DrzavaController(Drzava drzava, ArrayList<Grad> gradovi){
        this.drzava = drzava;
        listaGradova = FXCollections.observableArrayList(gradovi);
    }

    public Drzava getDrzava() {
        return drzava;
    }

    @FXML
    public void initialize(){
        choiceGrad.setItems(listaGradova);
        choiceGrad.getSelectionModel().selectFirst();
    }

    public void actionCancel(ActionEvent actionEvent){
        drzava = null;
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }

    public void actionOk(ActionEvent actionEvent){
        boolean validaijaIspravna = true;

        if(fieldNaziv.getText().isEmpty()){
            fieldNaziv.getStyleClass().removeAll("poljeIspravno");
            fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            validaijaIspravna = false;
        }else{
            fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
            fieldNaziv.getStyleClass().add("poljeIspravno");
        }

        if(!validaijaIspravna) return;

        if(drzava == null) drzava = new Drzava();
        drzava.setNaziv(fieldNaziv.getText());
        drzava.setGlavniGrad(choiceGrad.getValue());

        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }

}
