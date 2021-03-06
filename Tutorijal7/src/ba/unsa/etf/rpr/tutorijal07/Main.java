package ba.unsa.etf.rpr.tutorijal07;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        KorisniciModel model = new KorisniciModel();
        model.napuni();
        Controller controller = new Controller(model);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/korisnici.fxml"));
        loader.setController(controller);

        Parent root = loader.load();

        primaryStage.setTitle("Korisnici");
        primaryStage.setScene(new Scene(root, 450, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
