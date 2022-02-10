package ba.unsa.etf.rpr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GeografijaDAO {

    private static GeografijaDAO instance = null;
    private PreparedStatement dajGradoveUpit, dajDrzaveUpit, dajGradUpit, dajDrzavuUpit, glavniGradUpit, dajDrzavuSaNazivomUpit, dajGradSaNazivomUpit,
            dajGradSaFkDrzavaUpit, obrisiDrzavuSaIdUpit, obrisiGradSaIdUpit,obrisiGradoveSaFkDrzavaUpit, dodajGradUpit, dodajDrzavuUpit,
            dajNoviIdGrad, dajNoviIdDrzava, azurirajGrad;
    private Connection conn;

    private GeografijaDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            glavniGradUpit = conn.prepareStatement("SELECT grad.id, grad.naziv, grad.broj_stanovnika, grad.drzava FROM grad, drzava WHERE grad.drzava=drzava.id AND drzava.glavni_grad = ?");
        } catch (SQLException e) {
            ponovoGenerisiBazu();
            try {
                glavniGradUpit = conn.prepareStatement("SELECT grad.id, grad.naziv, grad.broj_stanovnika, grad.drzava FROM grad, drzava WHERE grad.drzava=drzava.id AND drzava.glavni_grad = ?");
            } catch (SQLException ex) {
                //Nesto nije uredu ili sa diskom ili sa driverom za bazu
                ex.printStackTrace();
            }
        }

        try {
            dajGradoveUpit = conn.prepareStatement("SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM grad g ORDER BY g.broj_stanovnika DESC;");
            dajDrzaveUpit = conn.prepareStatement("Select d.id, d.naziv, d.glavni_grad FROM drzava d;");
            dajGradUpit = dajGradSaFkDrzavaUpit = conn.prepareStatement("Select g.id, g.naziv, g.broj_stanovnika, g.drzava From grad g Where g.id=?;");
            dajDrzavuUpit = conn.prepareStatement("Select d.id, d.naziv, d.glavni_grad FROM drzava d WHERE d.id=?;");
            dajDrzavuSaNazivomUpit = conn.prepareStatement("Select * From drzava Where naziv=?;");
            dajGradSaNazivomUpit = conn.prepareStatement("Select * From grad Where naziv=?;");
            dajGradSaFkDrzavaUpit = conn.prepareStatement("Select g.id, g.naziv, g.broj_stanovnika, g.drzava From grad g Where g.drzava=?;");
            obrisiDrzavuSaIdUpit = conn.prepareStatement("Delete From drzava Where id=?;");
            obrisiGradSaIdUpit = conn.prepareStatement("Delete From grad Where id=?;");
            obrisiGradoveSaFkDrzavaUpit = conn.prepareStatement("Delete From grad Where drzava=?;");
            dajNoviIdGrad = conn.prepareStatement("Select Max(id)+1 From grad;");
            dajNoviIdDrzava = conn.prepareStatement("Select Max(id)+1 From drzava;");
            dodajGradUpit = conn.prepareStatement("Insert INTO grad VALUES(?,?,?,?);");
            dodajDrzavuUpit = conn.prepareStatement("Insert INTO drzava VALUES(?,?,?);");
            azurirajGrad = conn.prepareStatement("Update grad Set naziv=?, broj_stanovnika=?, drzava=? Where id=?;");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void ponovoGenerisiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("baza.db.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                String line = ulaz.nextLine();
                sqlUpit += line;
                if (sqlUpit.charAt(sqlUpit.length() - 1) == ';') {
                    try {
                        java.sql.Statement statement = conn.createStatement();
                        statement.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static GeografijaDAO getInstance() {
        if (instance == null) {
            instance = new GeografijaDAO();
        }
        return instance;
    }

    public static void removeInstance() {
        if (instance != null) {
            try {
                instance.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        instance = null;
    }

    public ArrayList<Grad> gradovi() {
        ArrayList<Grad> result = new ArrayList<>();
        try {
            ResultSet rs = dajGradoveUpit.executeQuery();
            while (rs.next()) {
                Grad grad = dajGradIzResultSeta(rs);
                result.add(grad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Drzava> drzave(){
        ArrayList<Drzava> result = new ArrayList<>();

        try {
            ResultSet rs = dajDrzaveUpit.executeQuery();
            while(rs.next()){
                dajGradUpit.setInt(1, rs.getInt(3));
                ResultSet rsGrad = dajGradUpit.executeQuery();
                result.add(dajDrzavuIzResultSeta(rs, dajGradIzResultSeta(rsGrad)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Grad dajGradIzResultSeta(ResultSet rs) throws SQLException {
        Grad grad = new Grad(rs.getInt(1), rs.getString(2), rs.getInt(3), null);
        grad.setDrzava( dajDrzavu(rs.getInt(4), grad) );
        return grad;
    }

    private Drzava dajDrzavu(int id, Grad grad) {
        try {
            dajDrzavuUpit.setInt(1, id);
            ResultSet rs = dajDrzavuUpit.executeQuery();
            if (!rs.next()) return null;
            return dajDrzavuIzResultSeta(rs, grad);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Drzava dajDrzavuIzResultSeta(ResultSet rs, Grad glavni) throws SQLException {
        return new Drzava(rs.getInt(1), rs.getString(2), glavni);
    }

    public Grad glavniGrad(String drzava) {
        try {
            dajDrzavuSaNazivomUpit.setString(1, drzava);
            ResultSet rs = dajDrzavuSaNazivomUpit.executeQuery();
            if (!rs.next()) return null;
            int idDrzava = rs.getInt(1);
            dajGradSaFkDrzavaUpit.setInt(1, idDrzava);
            rs = dajGradSaFkDrzavaUpit.executeQuery();
            return dajGradIzResultSeta(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void obrisiDrzavu(String drzava) {
        try {
            dajDrzavuSaNazivomUpit.setString(1, drzava);
            ResultSet rs = dajDrzavuSaNazivomUpit.executeQuery();
            if (!rs.next()) return;
            int idDrzava = rs.getInt(1);

            obrisiGradoveSaFkDrzavaUpit.setInt(1, idDrzava);
            obrisiGradoveSaFkDrzavaUpit.executeUpdate();
            obrisiDrzavuSaIdUpit.setInt(1, idDrzava);
            obrisiDrzavuSaIdUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void obrisiGrad(int id) {
        try {
            obrisiGradSaIdUpit.setInt(1, id);
            obrisiGradSaIdUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Drzava nadjiDrzavu(String drzava) {
        try {
            dajDrzavuSaNazivomUpit.setString(1, drzava);
            ResultSet rs = dajDrzavuSaNazivomUpit.executeQuery();
            if (!rs.next()) return null;

            Grad glavniGrad = glavniGrad(drzava);
            return dajDrzavuIzResultSeta(rs, glavniGrad);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Grad nadjiGrad(String grad) {
        try {
            dajGradSaNazivomUpit.setString(1, grad);
            ResultSet rs = dajGradSaNazivomUpit.executeQuery();
            if (!rs.next()) return null;

            return dajGradIzResultSeta(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void dodajGrad(Grad grad) {
        try {
            ResultSet rs = dajNoviIdGrad.executeQuery();
            int id = 1;
            if (rs.next()) id = rs.getInt(1);
            grad.setId(id);

            dodajGradUpit.setInt(1, grad.getId());
            dodajGradUpit.setString(2, grad.getNaziv());
            dodajGradUpit.setInt(3, grad.getBrojStanovnika());
            dodajGradUpit.setInt(4, grad.getDrzava().getId());
            dodajGradUpit.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajDrzavu(Drzava drzava) {
        try {
            ResultSet rs = dajNoviIdDrzava.executeQuery();
            int id = 1;
            if (rs.next()) id = rs.getInt(1);
            drzava.setId(id);

            dodajDrzavuUpit.setInt(1, drzava.getId());
            dodajDrzavuUpit.setString(2, drzava.getNaziv());
            dodajDrzavuUpit.setInt(3, drzava.getGlavniGrad().getId());
            dodajDrzavuUpit.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void izmijeniGrad(Grad grad) {
        ResultSet rs = null;
        try {
            rs = dajNoviIdGrad.executeQuery();
            int id = 0;
            if (!rs.next()) return;
            id = rs.getInt(1);

            if (grad.getId() >= 1 && grad.getId() < id) {
                azurirajGrad.setString(1, grad.getNaziv());
                azurirajGrad.setInt(2, grad.getBrojStanovnika());
                azurirajGrad.setInt(3, grad.getDrzava().getId());

                azurirajGrad.setInt(4, grad.getId());
                azurirajGrad.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
