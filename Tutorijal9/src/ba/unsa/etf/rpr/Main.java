package ba.unsa.etf.rpr;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /*
    public static void obrisiDrzavu() {
        System.out.println("Unesite naziv drzave: ");
        Scanner ulaz = new Scanner(System.in);
        String drzava = ulaz.nextLine();
        GeografijaDAO dao = GeografijaDAO.getInstance();
        dao.obrisiDrzavu(drzava);
        System.out.println(ispisiGradove());
    }

    public static void nadjiDrzavu() {
        System.out.println("Unesite naziv drzave: ");
        Scanner ulaz = new Scanner(System.in);
        String drzava = ulaz.nextLine();
        GeografijaDAO dao = GeografijaDAO.getInstance();
        Drzava d = dao.nadjiDrzavu(drzava);
        String result = "Drzava: " + d.getNaziv() + ", glavni grad "
                + (d.getGlavniGrad().getNaziv() + " (" + d.getGlavniGrad().getDrzava().getNaziv() + ") - " + d.getGlavniGrad().getBrojStanovnika() + "\n");
        System.out.println(result);
    }

    public static void dodajGrad() {
        GeografijaDAO dao = GeografijaDAO.getInstance();
        Drzava francuska = dao.nadjiDrzavu("Francuska");
        nadjiDrzavu();
        Grad grad = new Grad();
        grad.setNaziv("Marseille");
        grad.setBrojStanovnika(869815);
        grad.setDrzava(francuska);
        dao.dodajGrad(grad);
    }

    public static void dodajDrzavu() {
        Grad sarajevo = new Grad();
        sarajevo.setNaziv("Sarajevo");
        sarajevo.setBrojStanovnika(500000);
        Drzava bih = new Drzava();
        bih.setNaziv("Bosna i Hercegovina");
        bih.setGlavniGrad(sarajevo);
        sarajevo.setDrzava(bih);

        GeografijaDAO dao = GeografijaDAO.getInstance();
        dao.dodajDrzavu(bih);
        dao.dodajGrad(sarajevo);
    }

    public static void izmjeniGrad() {
        GeografijaDAO dao = GeografijaDAO.getInstance();
        Grad grad = dao.glavniGrad("Bosna i Hercegovina");
        System.out.println("Update " + grad.getNaziv() + ": \n");
        grad.setNaziv("Bugojno");
        dao.izmijeniGrad(grad);
        System.out.println(ispisiGradove());
    }
*/

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

    public static void main(String[] args) {
        System.out.println("Gradovi su:\n" + ispisiGradove());
        glavniGrad();
    }

}
