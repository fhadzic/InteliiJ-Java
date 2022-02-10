package ba.unsa.etf.rpr.tutorijal07;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {

    private KorisniciModel model;

    public ListView listKorisnici;
    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldEmail;
    public TextField fldUser;
    public PasswordField fldPassword;

    public Controller(KorisniciModel model) {
        //Ne raditi nista vezano za GUI, to se radi u Initialize
        this.model = model;
    }

    @FXML
    public void initialize() {
        listKorisnici.setItems(model.getKorisnici());

        fldIme.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                listKorisnici.refresh();
            }
        });

        fldPrezime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                listKorisnici.refresh();
            }
        });

        listKorisnici.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Korisnik>() {
            @Override
            public void changed(ObservableValue<? extends Korisnik> observableValue, Korisnik oldKorisnik, Korisnik newKorisnik) {

                if(oldKorisnik != null){
                    fldIme.textProperty().unbindBidirectional(oldKorisnik.imeProperty());
                    fldPrezime.textProperty().unbindBidirectional(oldKorisnik.prezimeProperty());
                    fldEmail.textProperty().unbindBidirectional(oldKorisnik.emailProperty());
                    fldUser.textProperty().unbindBidirectional(oldKorisnik.userProperty());
                    fldPassword.textProperty().unbindBidirectional(oldKorisnik.passwordProperty());
                }

                model.setTrenutniKorisnik(newKorisnik);

                if(newKorisnik == null){
                    fldIme.setText("");
                    fldPrezime.setText("");
                    fldEmail.setText("");
                    fldUser.setText("");
                    fldPassword.setText("");
                }else{
                    System.out.println("Promijenjen tenutni korisnik: " + newKorisnik.getIme());

                    fldIme.textProperty().bindBidirectional(newKorisnik.imeProperty());
                    fldPrezime.textProperty().bindBidirectional(newKorisnik.prezimeProperty());
                    fldEmail.textProperty().bindBidirectional(newKorisnik.emailProperty());
                    fldUser.textProperty().bindBidirectional(newKorisnik.userProperty());
                    fldPassword.textProperty().bindBidirectional(newKorisnik.passwordProperty());
                }
            }
        });

    }

    public void dodajKorisnika(ActionEvent actionEvent) {
        System.out.println("Dodan prazan korisnik!");

        model.dodaj();
        listKorisnici.getSelectionModel().select(model.getTrenutniKorisnik());
        listKorisnici.refresh();
    }

    public void zavrsiProgram(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public KorisniciModel getModel() {
        return model;
    }
}
