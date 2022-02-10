package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;


import java.io.File;
import java.io.IOException;
import java.util.*;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class GlavnaController {

    public TableColumn colGradId;
    public TableColumn colGradNaziv;
    public TableColumn colGradStanovnika;
    public TableColumn colGradDrzava;
    public TableView tableViewGradovi;
    public List<String> choices;
    public ChoiceDialog<String> dialogJezika;

    private GeografijaDAO dao;
    public ObservableList<Grad> listaGradova;


    public GlavnaController() {
        dao = GeografijaDAO.getInstance();
        listaGradova = FXCollections.observableArrayList(dao.gradovi());
        choices = new ArrayList<>();
        choices.add("Bosanski");
        choices.add("English");
        choices.add("German");
        choices.add("Frances");
        dialogJezika = new ChoiceDialog<>("Bosnian", choices);
    }

    @FXML
    public void initialize() {
        tableViewGradovi.setItems(listaGradova);
        colGradId.setCellValueFactory(new PropertyValueFactory("id"));
        colGradNaziv.setCellValueFactory(new PropertyValueFactory("naziv"));
        colGradStanovnika.setCellValueFactory(new PropertyValueFactory("brojStanovnika"));
        colGradDrzava.setCellValueFactory(new PropertyValueFactory("drzava"));
        dialogJezika.setSelectedItem(dialogJezika.getSelectedItem());
    }


    public void actionDodajDrzavu(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/drzava.fxml"), bundle);
            DrzavaController drzavaController = new DrzavaController(null, dao.gradovi());
            loader.setController(drzavaController);
            root = loader.load();
            stage.setTitle("Država");
            stage.setScene(new Scene(root, 300, 150));
            stage.setResizable(false);
            stage.show();

            stage.setOnHiding((event) -> {
                if (drzavaController.getDrzava() != null) {
                    dao.dodajDrzavu(drzavaController.getDrzava());
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionDodajGrad(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation", Locale.getDefault());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"), bundle);
            GradController gradController = new GradController(null, dao.drzave());
            loader.setController(gradController);
            root = loader.load();
            stage.setTitle("Grad");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();

            stage.setOnHiding((event) -> {
                if (gradController.getGrad() != null) {
                    dao.dodajGrad(gradController.getGrad());
                    listaGradova.setAll(dao.gradovi());
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionIzmijeniGrad(ActionEvent actionEvent) {
        Grad grad = (Grad) tableViewGradovi.getSelectionModel().getSelectedItem();
        if (grad == null) return;

        Stage stage = new Stage();
        Parent root = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation", Locale.getDefault());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"), bundle);
            GradController gradController = new GradController(grad, dao.drzave());
            loader.setController(gradController);
            root = loader.load();
            stage.setTitle("Grad");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();

            stage.setOnHiding((event) -> {
                Grad izmjenjeniGrad = gradController.getGrad();
                if (izmjenjeniGrad != null) {
                    dao.izmijeniGrad(izmjenjeniGrad);
                    listaGradova.setAll(dao.gradovi());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionObrisiGrad(ActionEvent actionEvent) {
        Grad grad = (Grad) tableViewGradovi.getSelectionModel().getSelectedItem();
        if (grad == null) return;

        boolean ok = confirmationDialog();
        if (ok) {
            dao.obrisiGrad(grad.getId());
            listaGradova.setAll(dao.gradovi());
        }
    }

    public boolean confirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("RAZMILITE!");
        alert.setContentText("Jeste li sigurni da želite obrisati odabrani grad?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public void actionStampa(ActionEvent actionEvent) {
        try {
            new GradoviReport().showReport(dao.getConn());
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void actionJezik(ActionEvent actionEvent) {
        choiceDialog();
        String izabraniJezik = dialogJezika.getSelectedItem();
        Locale locale = Locale.getDefault();
        if (izabraniJezik.equals("Bosanski")) {
            locale = new Locale("bs");
        } else if (izabraniJezik.equals("English")) {
            locale = new Locale("en");
        } else if (izabraniJezik.equals("German")) {
            locale = new Locale("de");
        } else if (izabraniJezik.equals("Frances")) {
            locale = new Locale("fr");
        }
        Stage stage = (Stage) tableViewGradovi.getScene().getWindow();
        otvoriScene(locale, stage);
    }

    public void choiceDialog() {
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        dialogJezika.setTitle("Choice Dialog");
        dialogJezika.setHeaderText(bundle.getString("language_selection"));
        dialogJezika.setContentText(bundle.getString("selected_language"));

        Optional<String> result = dialogJezika.showAndWait();
        if (result.isPresent()) {
            System.out.println("Odabrani jezik: " + result.get());
        }
    }

    public void otvoriScene(Locale locale, Stage stage) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation", locale);
            Locale.setDefault(locale);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/glavna.fxml"), bundle);
            loader.setController(this);
            Parent root = loader.load();
            stage.setScene(new Scene(root, 500, 300));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetujBazu() {
        GeografijaDAO.removeInstance();
        File dbfile = new File("baza.db");
        dbfile.delete();
        dao = GeografijaDAO.getInstance();
    }
}
