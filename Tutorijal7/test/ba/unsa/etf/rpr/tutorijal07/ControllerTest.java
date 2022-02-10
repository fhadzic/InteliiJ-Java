package ba.unsa.etf.rpr.tutorijal07;

import static org.junit.jupiter.api.Assertions.*;

import com.sun.media.jfxmediaimpl.platform.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.awt.*;

@ExtendWith(ApplicationExtension.class)
class ControllerTest {

    KorisniciModel model = new KorisniciModel();

    @Start
    public void start(Stage stage) throws Exception{
        model.napuni();
        Controller controller = new Controller(model);

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/korisnici.fxml"));
        loader.setController(controller);

        Parent root = loader.load();

        stage.setTitle("Korisnici");
        stage.setScene(new Scene(root, 450, 275));
        stage.show();
        stage.toFront();

    }


    @Test
    void testDodaj(FxRobot robot){
        Button btnDodaj = robot.lookup("#btnDodaj").queryAs(Button.class);
        TextField fldIme = robot.lookup("#fldIme").queryAs(TextField.class);
        TextField fldPrezime = robot.lookup("#fldPrezime").queryAs(TextField.class);

        assertNotNull(btnDodaj);
        assertNotNull(fldIme);
        assertNotNull(fldPrezime);

        robot.clickOn(fldIme);
        robot.write("Ferid");
        robot.clickOn(fldPrezime);
        robot.write("Hadžić");
        robot.clickOn(btnDodaj);

        assertAll(()->{
            assertEquals("", fldIme.getText());
            assertEquals("", fldPrezime.getText());
            assertEquals("", model.getKorisnici().get(model.getKorisnici().size()-1).getIme());
            assertEquals("", model.getKorisnici().get(model.getKorisnici().size()-1).getPrezime());
        });
    }

    @Test
    void testKraj(FxRobot robot){
        Button btnKraj = robot.lookup("#btnKraj").queryAs(Button.class);
        assertNotNull(btnKraj);
//        robot.clickOn(btnKraj);
//        assertEquals(0, robot.listWindows().size());
    }


}