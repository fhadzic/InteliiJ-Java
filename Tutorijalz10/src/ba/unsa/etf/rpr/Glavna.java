package ba.unsa.etf.rpr;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;


public class Glavna extends Application {

    public static String ispisiGradove() {
        GeografijaDAO dao = GeografijaDAO.getInstance();
        ArrayList<Grad> gradovi = dao.gradovi();

        String result = "" ;
        for(Grad grad : gradovi){
            if(grad != null)
                result += (grad.getNaziv() + " (" + grad.getDrzava().getNaziv() + ") - " + grad.getBrojStanovnika() + "\n");
        }
        return result;
    }

    public static void glavniGrad() {
        System.out.println("Unesite naziv države: ");
        Scanner ulaz = new Scanner(System.in);
        String drzava = ulaz.nextLine();
        GeografijaDAO dao = GeografijaDAO.getInstance();
        Grad grad = dao.glavniGrad(drzava);
        if(grad != null) {
            System.out.println("Glavni grad države " + grad.getDrzava().getNaziv() + " je " + grad.getNaziv());
        }else{
            System.out.println("Nepostojeća država");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        GlavnaController controller = new GlavnaController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/glavna.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        primaryStage.setTitle("Gradovi svijeta");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.setResizable(true);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }

}
