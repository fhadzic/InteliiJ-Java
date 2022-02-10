package ba.unsa.etf.rpr.tutorijal8;

import com.github.tsijercic1.InvalidXMLException;
import com.github.tsijercic1.XMLParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Tutorijal {

    //Zadatak1
    public static ArrayList<Grad> ucitajGradove() {
        Scanner ulaz;
        ArrayList<Grad> gradovi = new ArrayList<>();

        try {
            ulaz = new Scanner(new FileReader("mjerenja.txt"));

        } catch (FileNotFoundException e) {
            System.out.println("Datoteka mjerenja.txt ne postoji ili se ne može otvoriti.");
            System.out.println("Greška: " + e);
            return null; // kraj programa
        }

        try {

            while (ulaz.hasNextLine()) {
                String line = ulaz.nextLine();
                String[] elementi = line.split(",");
                Grad grad = new Grad();
                double[] temperature = new double[1000];
                int vel = 0;

                for (String element : elementi) {
                    if (vel == 0) {
                        grad.setNaziv(element);
                        vel++;
                        continue;
                    }
                    temperature[vel - 1] = Double.parseDouble(element);
                    vel++;
                    if (vel == 1001) break;
                }
                grad.setTemperature(temperature, vel - 1);
                gradovi.add(grad);
            }

        } catch (Exception e) {
            System.out.println("Problem pri čitanju podataka.");
        } finally {
            ulaz.close();
        }

        return gradovi;
    }

    //Zadatak 2
//Bez Tarikove klase
    private static String odsjeciRazmake(String nazivSaRazmacima) {
        int begin = 0, end = 0;
        boolean prvi = true;
        for (int i = 0; i < nazivSaRazmacima.length(); i++) {
            if ((nazivSaRazmacima.charAt(i) >= 'A' && nazivSaRazmacima.charAt(i) <= 'Z')
                    || (nazivSaRazmacima.charAt(i) >= 'a' && nazivSaRazmacima.charAt(i) <= 'z')
                    || (nazivSaRazmacima.charAt(i) >= '0' && nazivSaRazmacima.charAt(i) <= '9')) {
                if (prvi) {
                    begin = i;
                    prvi = false;
                }
                end = i;
            }
        }
        return nazivSaRazmacima.substring(begin, end + 1);
    }


    private static Grad upisiGrad(Element element) {
        Grad glavniGrad = new Grad();
        glavniGrad.setBrojStanovnika(Integer.parseInt(element.getAttribute("stanovnika")));
        String nazivSaRazmacima = element.getTextContent();
        glavniGrad.setNaziv(odsjeciRazmake(nazivSaRazmacima));
        return glavniGrad;
    }

    private static Drzava ucitajDrzavu(Element element) {
        Drzava drzava = new Drzava();

        drzava.setBrojStanovnika(Integer.parseInt(element.getAttribute("stanovnika")));

        NodeList djeca = element.getChildNodes();

        for (int i = 0; i < djeca.getLength(); i++) {
            Node dijete = djeca.item(i);
            if (dijete instanceof Element) {
                if (((Element) dijete).getTagName().equals("naziv")) {
                    drzava.setNaziv(((Element) dijete).getTextContent());
                } else if (((Element) dijete).getTagName().equals("glavnigrad")) {
                    drzava.setGlavniGrad(upisiGrad((Element) dijete));
                } else if (((Element) dijete).getTagName().equals("povrsina")) {
                    drzava.setJedinica(((Element) dijete).getAttribute("jedinica"));
                    drzava.setPovrsina(Double.parseDouble(((Element) dijete).getTextContent()));
                }
            }
        }

        return drzava;
    }


    public static UN ucitajXml(ArrayList<Grad> gradovi) {
        UN un = new UN();

        Document xmlDoc = null;

        try {
            DocumentBuilder docReader = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmlDoc = docReader.parse(new File("drzave.xml"));
        } catch (Exception e) {
            System.out.println("drzave.xml nije validan XML dokument");
            return null;
        }

        Element korijen = xmlDoc.getDocumentElement();

        for (int i = 0; i < korijen.getElementsByTagName("drzava").getLength(); i++) {
            Node dijete = korijen.getElementsByTagName("drzava").item(i);
            if (dijete instanceof Element) {
                Drzava drzava = ucitajDrzavu((Element) dijete);
                un.dodajDrzavu(drzava);

                if (gradovi.stream().anyMatch((grad) -> {
                    return grad.getNaziv().equals(drzava.getGlavniGrad().getNaziv());
                })) {
                    gradovi.forEach((grad) -> {
                        if (grad.getNaziv().equals(drzava.getGlavniGrad().getNaziv())) {
                            drzava.getGlavniGrad().setTemperature(grad.getTemperature(), grad.getBrojMjerenja());
                        }
                    });
                }

            }
        }

        return un;
    }


    //Sa Tarikovom klasom
    private static Grad upisiGradTarik(Element element) {
        Grad glavniGrad = new Grad();
        glavniGrad.setBrojStanovnika(Integer.parseInt(element.getAttribute("stanovnika")));
        String nazivSaRazmacima = element.getTextContent();
        glavniGrad.setNaziv(odsjeciRazmake(nazivSaRazmacima));
        return glavniGrad;
    }

    private static Drzava ucitajDrzavuTarik(com.github.tsijercic1.Node element) {
        Drzava drzava = new Drzava();

        drzava.setBrojStanovnika(Integer.parseInt(element.getAttributes().get("stanovnika")));

        ArrayList<com.github.tsijercic1.Node> djeca = element.getChildNodes();

        for (int i = 0; i < djeca.size(); i++) {
            com.github.tsijercic1.Node dijete = djeca.get(i);
            if (((Element) dijete).getTagName().equals("naziv")) {
                drzava.setNaziv(((Element) dijete).getTextContent());
            } else if (((Element) dijete).getTagName().equals("glavnigrad")) {
                drzava.setGlavniGrad(upisiGradTarik((Element) dijete));
            } else if (((Element) dijete).getTagName().equals("povrsina")) {
                drzava.setJedinica(((Element) dijete).getAttribute("jedinica"));
                drzava.setPovrsina(Double.parseDouble(((Element) dijete).getTextContent()));
            }
        }

        return drzava;
    }

    public static UN ucitajXMLTarik(ArrayList<Grad> gradovi) throws IOException {
        UN un = new UN();

        XMLParser xmlParser = new XMLParser("drzave.xml");

        try {
            com.github.tsijercic1.Node node = xmlParser.getDocumentRootNode();
            ArrayList<com.github.tsijercic1.Node> nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.size(); i++) {
                com.github.tsijercic1.Node dijete = nodeList.get(i);
                Drzava drzava = ucitajDrzavuTarik(dijete);
                un.dodajDrzavu(drzava);
                if (gradovi.stream().anyMatch((grad) -> {
                    return grad.getNaziv().equals(drzava.getGlavniGrad().getNaziv());
                })) {
                    gradovi.forEach((grad) -> {
                        if (grad.getNaziv().equals(drzava.getGlavniGrad().getNaziv())) {
                            drzava.getGlavniGrad().setTemperature(grad.getTemperature(), grad.getBrojMjerenja());
                        }
                    });
                }
            }
        } catch (InvalidXMLException e) {
            e.printStackTrace();
        }

        return un;
    }


    //Zadatak 3
    public static void zapisiXML(UN un) {

        try {
            XMLEncoder izlaz = new XMLEncoder(new FileOutputStream("un.xml"));
            izlaz.writeObject(un);
            izlaz.close();
        } catch (FileNotFoundException e) {
            System.out.println("Greška: " + e);
        }

    }

    public static UN ucitajUnIzXML() {
        UN un = null;

        try {
            XMLDecoder ulaz = new XMLDecoder(new FileInputStream("un.xml"));
            un = (UN) ulaz.readObject();
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return un;
    }

    public static void main(String[] args) {
        // write your code here
        ArrayList<Grad> gradovi = Tutorijal.ucitajGradove();
//        System.out.println(gradovi);
        UN un = ucitajXml(gradovi);
//        System.out.println(un.getDrzave());
//        zapisiXML(un);
//        System.out.println(ucitajUnIzXML().getDrzave());
        System.out.println(un.getDrzave());
    }

}
