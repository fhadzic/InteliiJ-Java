package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

public class GradController {

    public TextField fieldNaziv;
    public TextField fieldSlika;
    public TextField fieldBrojStanovnika;
    public TextField fieldPost;
    public ImageView imageView;
    public ChoiceBox<Drzava> choiceDrzava;

    public ObservableList<Drzava> listaDrzava;
    private Grad grad;

    public GradController(Grad grad, ArrayList<Drzava> drzave){
        this.grad = grad;
        listaDrzava = FXCollections.observableArrayList(drzave);
    }

    public Grad getGrad() {
        return grad;
    }

    @FXML
    public void initialize(){
        choiceDrzava.setItems(listaDrzava);
        if(grad != null) {
            fieldNaziv.setText(grad.getNaziv());
            fieldBrojStanovnika.setText( String.valueOf(grad.getBrojStanovnika()) );
            fieldSlika.setText(grad.getSlika());
            fieldPost.setText(grad.getPostanskiBroj());
            for (Drzava drzava : listaDrzava){
                if(drzava.getId() == grad.getDrzava().getId()){
                    choiceDrzava.getSelectionModel().select(drzava);
                }
            }
            otvoriSliku(grad.getSlika());
        }else{
            choiceDrzava.getSelectionModel().selectFirst();
        }
    }

    public void otvoriSliku(String slika){
        File file = new File(slika);
        System.out.println( file.toURI().toString() );
        Image image = new Image( file.toURI().toString() );
        imageView.setImage(image);
    }

    public void actionCancel(ActionEvent actionEvent){
        grad = null;
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }

    public void actionOk(ActionEvent actionEvent){
        boolean valdacijaIspravna = true;

        if(fieldNaziv.getText().isEmpty()){
            fieldNaziv.getStyleClass().removeAll("poljeIspravno");
            fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            valdacijaIspravna = false;
        }else{
            fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
            fieldNaziv.getStyleClass().add("poljeIspravno");
        }

        int brojStanovnika = 0;
        try {
            brojStanovnika = Integer.valueOf(fieldBrojStanovnika.getText());
        }catch (NumberFormatException e){
            brojStanovnika = 0;
        }

        if(brojStanovnika <= 0){
            fieldBrojStanovnika.getStyleClass().removeAll("poljeIspravno");
            fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
            valdacijaIspravna = false;
        }else{
            fieldBrojStanovnika.getStyleClass().removeAll("poljeNijeIspravno");
            fieldBrojStanovnika.getStyleClass().add("poljeIspravno");
        }

        if(!valdacijaIspravna) return;

        if(grad == null) grad = new Grad();
        grad.setNaziv(fieldNaziv.getText());
        grad.setBrojStanovnika(brojStanovnika);
        grad.setDrzava(choiceDrzava.getValue());
        grad.setSlika(fieldSlika.getText());
        grad.setPostanskiBroj(fieldPost.getText());

        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }

    public void actionPromijeni(ActionEvent actionEvent){
        otvoriSliku(fieldSlika.getText());
    }

}
