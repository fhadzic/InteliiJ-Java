package ba.unsa.etf.rpr.zadaca2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class KorisnikController {

    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldEmail;
    public TextField fldUsername;
    public ListView<Korisnik> listKorisnici;
    public PasswordField fldPassword;
    public PasswordField fldPasswordRepeat;
    public Slider sliderGodinaRodjenja;
    public CheckBox cbAdmin;

    private KorisniciModel model;

    public KorisnikController(KorisniciModel model) {
        this.model = model;
    }

    @FXML
    public void initialize() {
        listKorisnici.setItems(model.getKorisnici());
        listKorisnici.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            model.setTrenutniKorisnik(newKorisnik);
            fldPasswordRepeat.setText(model.getTrenutniKorisnik().getPassword());
            listKorisnici.refresh();
        });

        model.trenutniKorisnikProperty().addListener((obs, oldKorisnik, newKorisnik) -> {

            if (oldKorisnik != null) {
                fldIme.textProperty().unbindBidirectional(oldKorisnik.imeProperty());
                fldPrezime.textProperty().unbindBidirectional(oldKorisnik.prezimeProperty());
                fldEmail.textProperty().unbindBidirectional(oldKorisnik.emailProperty());
                sliderGodinaRodjenja.valueProperty().unbindBidirectional(oldKorisnik.godinaRodjenjaProperty());
                fldUsername.textProperty().unbindBidirectional(oldKorisnik.usernameProperty());
                fldPassword.textProperty().unbindBidirectional(oldKorisnik.passwordProperty());
            }
            if (newKorisnik == null) {
                cbAdmin.setSelected(false);
                fldIme.setText("");
                fldPrezime.setText("");
                fldEmail.setText("");
                sliderGodinaRodjenja.setValue(2000);
                fldUsername.setText("");
                fldPassword.setText("");
                fldPasswordRepeat.setText("");
            } else {
                fldIme.textProperty().bindBidirectional(newKorisnik.imeProperty());
                fldPrezime.textProperty().bindBidirectional(newKorisnik.prezimeProperty());
                fldEmail.textProperty().bindBidirectional(newKorisnik.emailProperty());
                sliderGodinaRodjenja.valueProperty().bindBidirectional(newKorisnik.godinaRodjenjaProperty());
                fldUsername.textProperty().bindBidirectional(newKorisnik.usernameProperty());
                fldPassword.textProperty().bindBidirectional(newKorisnik.passwordProperty());
                if (!(model.getTrenutniKorisnik() instanceof Administrator)) {
                    cbAdmin.setSelected(false);
                } else {
                    cbAdmin.setSelected(true);
                }
            }
        });

        cbAdmin.selectedProperty().addListener((observableValue, isOldAdmin, isNewAdmin) -> {
            if (model.getTrenutniKorisnik() != null) {
                if (isNewAdmin && !(model.getTrenutniKorisnik() instanceof Administrator)) {
                    Administrator admin = new Administrator(model.getTrenutniKorisnik().getIme(), model.getTrenutniKorisnik().getPrezime(),
                            model.getTrenutniKorisnik().getEmail(), model.getTrenutniKorisnik().getUsername(),
                            model.getTrenutniKorisnik().getPassword());
                    admin.setGodinaRodjenja(model.getTrenutniKorisnik().getGodinaRodjenja());

                    int index = model.getKorisnici().lastIndexOf(model.getTrenutniKorisnik());
                    model.getKorisnici().remove(index);
                    model.getKorisnici().add(index, admin);
                    model.setTrenutniKorisnik(admin);
                    listKorisnici.getSelectionModel().select(model.getTrenutniKorisnik());
                } else if (!isNewAdmin && model.getTrenutniKorisnik() instanceof Administrator) {
                    Korisnik korisnik = new Korisnik(model.getTrenutniKorisnik().getIme(), model.getTrenutniKorisnik().getPrezime(),
                            model.getTrenutniKorisnik().getEmail(), model.getTrenutniKorisnik().getUsername(),
                            model.getTrenutniKorisnik().getPassword());
                    korisnik.setGodinaRodjenja(model.getTrenutniKorisnik().getGodinaRodjenja());

                    int index = model.getKorisnici().lastIndexOf(model.getTrenutniKorisnik());
                    model.getKorisnici().remove(index);
                    model.getKorisnici().add(index, korisnik);
                    model.setTrenutniKorisnik(korisnik);
                    listKorisnici.getSelectionModel().select(model.getTrenutniKorisnik());
                }
            }
        });


        fldIme.textProperty().addListener((obs, oldIme, newIme) -> {
            listKorisnici.refresh();
            boolean validno = KorisniciModel.valdacijaImenaIliPrezimena(newIme);

            if (validno) {
                fldIme.getStyleClass().removeAll("poljeNijeIspravno");
                fldIme.getStyleClass().add("poljeIspravno");
            } else {
                fldIme.getStyleClass().removeAll("poljeIspravno");
                fldIme.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldPrezime.textProperty().addListener((obs, oldPrezime, newPrezime) -> {
            listKorisnici.refresh();
            boolean validno = KorisniciModel.valdacijaImenaIliPrezimena(newPrezime);

            if (validno) {
                fldPrezime.getStyleClass().removeAll("poljeNijeIspravno");
                fldPrezime.getStyleClass().add("poljeIspravno");
            } else {
                fldPrezime.getStyleClass().removeAll("poljeIspravno");
                fldPrezime.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldEmail.textProperty().addListener((obs, oldEmail, newEmail) -> {
            boolean validan = KorisniciModel.valdacijaEmaila(newEmail);

            if (validan) {
                fldEmail.getStyleClass().removeAll("poljeNijeIspravno");
                fldEmail.getStyleClass().add("poljeIspravno");
            } else {
                fldEmail.getStyleClass().removeAll("poljeIspravno");
                fldEmail.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldUsername.textProperty().addListener((obs, oldNadimak, newNadimak) -> {
            boolean validan = KorisniciModel.valdacijaNadimka(newNadimak);

            if (validan) {
                fldUsername.getStyleClass().removeAll("poljeNijeIspravno");
                fldUsername.getStyleClass().add("poljeIspravno");
            } else {
                fldUsername.getStyleClass().removeAll("poljeIspravno");
                fldUsername.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldPassword.textProperty().addListener((obs, oldPassword, newPassword) -> {
            if (model.getTrenutniKorisnik() != null) {
                model.getTrenutniKorisnik().setPassword(newPassword);

                if (fldPasswordRepeat.getText().equals(newPassword) && model.getTrenutniKorisnik().checkPassword()) {
                    fldPassword.getStyleClass().removeAll("poljeNijeIspravno");
                    fldPassword.getStyleClass().add("poljeIspravno");
                    fldPasswordRepeat.getStyleClass().removeAll("poljeNijeIspravno");
                    fldPasswordRepeat.getStyleClass().add("poljeIspravno");
                } else {
                    fldPassword.getStyleClass().removeAll("poljeIspravno");
                    fldPassword.getStyleClass().add("poljeNijeIspravno");
                    fldPasswordRepeat.getStyleClass().removeAll("poljeIspravno");
                    fldPasswordRepeat.getStyleClass().add("poljeNijeIspravno");
                }
            }
        });

        fldPasswordRepeat.textProperty().addListener((obs, oldPassswordRepeat, newPassswordRepeat) -> {
            if (model.getTrenutniKorisnik() != null) {
                if (model.getTrenutniKorisnik().getPassword().equals(newPassswordRepeat) && model.getTrenutniKorisnik().checkPassword()) {
                    fldPasswordRepeat.getStyleClass().removeAll("poljeNijeIspravno");
                    fldPasswordRepeat.getStyleClass().add("poljeIspravno");
                    fldPassword.getStyleClass().removeAll("poljeNijeIspravno");
                    fldPassword.getStyleClass().add("poljeIspravno");
                } else {
                    fldPasswordRepeat.getStyleClass().removeAll("poljeIspravno");
                    fldPasswordRepeat.getStyleClass().add("poljeNijeIspravno");
                    fldPassword.getStyleClass().removeAll("poljeIspravno");
                    fldPassword.getStyleClass().add("poljeNijeIspravno");
                }
            }
        });
    }

    public void obrisiAction(ActionEvent actionEvent) {
        if (model.getTrenutniKorisnik() != null) {
            model.getKorisnici().remove(model.getTrenutniKorisnik());
        }
    }

    public void generisiAction(ActionEvent actionEvent) {
        if (model.getTrenutniKorisnik() != null) {
            model.getTrenutniKorisnik().generisiUserName();
            model.getTrenutniKorisnik().generisiPassword();
            fldPasswordRepeat.setText(model.getTrenutniKorisnik().getPassword());
            obavijestOPasswordu();
        }
    }

    public void dodajAction(ActionEvent actionEvent) {
        Korisnik k = new Korisnik("", "", "", "", "");
        model.getKorisnici().add(k);
        model.setTrenutniKorisnik(k);
        listKorisnici.getSelectionModel().selectLast();
    }

    public void krajAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void obavijestOPasswordu() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Password");
        a.setHeaderText(model.getTrenutniKorisnik().getPassword());
        a.setResizable(true);
        String content = "Zapamtite vašu novu šifru!\nAko ste zapamtili, zatvorite prozor(Klikom na dugme OK).";
        a.setContentText(content);
        a.showAndWait();
    }

}
