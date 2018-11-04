/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema1_apostolstefan;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 *
 * @author Apostol
 */
public class Agenda implements Serializable {

    public enum CriteriuOrdonare {   //enum cu criteriile de ordonare
        DUPA_NUME, DUPA_PRENUME, DUPA_VARSTA, DUPA_TELEFON
    }

    public List Contacte = new ArrayList();  //crearea listei de contacte 
    public List y = new ArrayList();
    Predicate<Contact> camp = (Contact x) -> true; //fixam predicatul camp pe true
    CriteriuOrdonare camp_curent;

    public Agenda() {

    }

    public void Adauga(String nume, String prenume, String data, String numar) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy"); //definirea formatului
        LocalDate data_nastere = LocalDate.parse(data, formatter);    //formatarea datii dupa formatul de mai sus
        Contact c = new Contact(nume, prenume, data_nastere, numar);  //crearea contactului 
        if (Contacte.contains(c)) {                           //verificare daca contactul exista in agenda
            throw new RuntimeException("contactul exista!");   //daca da aruncam exceptie
        } else {
            Contacte.add(c);   //daca nu il adaugam
        }
    }

    public void Stergere(Contact c) {
        if (Contacte.contains(c)) {   //verificam daca contactul exista in agenda
            Contacte.remove(c);   //daca da il stergem
        } else {
            throw new RuntimeException("Contactul nu se gaseste in lista de contacte"); //daca nu aruncam exceptie
        }
    }
    
    
    public void Modifica(String nume, String prenume, String data, String numar, Contact c) {  //metoda care modifica un contact existent
        if (nume.length() < 2 || prenume.length() < 2) {   //verificam daca numele nou si prenumele nou este valid
            throw new RuntimeException("NUMELE SAU PRENUMELE PREA SCURT!");
        }
        if (numar.startsWith("07") || numar.startsWith("03") || numar.startsWith("02")) {  //verificam daca numarul de telefon este valid
         } else {
            throw new RuntimeException("Numarul nu incepe cu 07,02 sau 03!");
        }
        if(numar.length()!=10){             //verificam ca numarul de telefon sa aiba 10 cifre
             throw new RuntimeException("Numaru nu are 10 cifre...");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");  //cream formatul de data
        LocalDate data_nastere = LocalDate.parse(data, formatter);       //formatam data
        Contact test = new Contact(nume, prenume, data_nastere, numar);   //cream un contact copie(test)
        if(Contacte.contains(test)){   //testam daca agenda contine contactul test
            throw new RuntimeException("contactul exista!");   //daca da atuncam exceptie
        }
        else{   //daca nu modificam contactul nou cu campurile contactului test
        c.nume = nume;
        c.prenume = prenume;
        c.data_nastere = data_nastere;
        c.telefon.numar_telefon = numar;
        }

    }

    public void filtreazaNrFix() {
        camp = (Contact x) -> x.getTelefon().numar_telefon.startsWith("021");  //setam campul de filtare pentru numar fix
    }

    public void filtreazaNrMobil() {
        camp = (Contact x) -> x.getTelefon().numar_telefon.startsWith("07");  //setam campul de filtare pentru numar mobil
    }

    public void filtreazaNascutiAstazi() {
        camp = (Contact x) -> x.getData_nastere().getDayOfMonth()==(LocalDate.now().getDayOfMonth());  //setam campul de filtare pentru Nascuti Azi

    }
    //filtreaza personalizat

    public void filtreazaPersonalizat(String filtru) {   //setam campul de filtare pentru personalizat
        camp = (Contact x) -> (x.getNume().contains(filtru) || x.getPrenume().contains(filtru) || x.telefon.toString().contains(filtru));
    }

    public void filtreazaNascutiLunaCurenta() {
        camp = (Contact x) -> x.getData_nastere().getMonth().equals(LocalDate.now().getMonth());   //setam campul de filtrare pentru luna curenta
    }

    public void Ordoneaza(CriteriuOrdonare camp_curent) {
        this.camp_curent = camp_curent;  //setarea campului curent cu cel primit prin parametru
    }

    public List contacte_functie(int i) {
        if (i == 0) {// filtrari

            y = (List) Contacte.stream().filter(camp).collect(Collectors.toList());  //filtrare dupa camp ul predefinit

            return y;
        } else {
            switch (camp_curent) {     //ordonare dupa camp_curent
                case DUPA_NUME:
                    y = (List) Contacte.stream().sorted(Comparator.comparing(Contact::getNume)).collect(Collectors.toList());
                    break;
                case DUPA_PRENUME:
                    y = (List) Contacte.stream().sorted(Comparator.comparing(Contact::getPrenume)).collect(Collectors.toList());
                    break;
                case DUPA_VARSTA:
                    y = (List) Contacte.stream().sorted(Comparator.comparing(Contact::getData_nastere)).collect(Collectors.toList());
                    break;
                case DUPA_TELEFON:
                    y = (List) Contacte.stream().sorted(Comparator.comparing(Contact::getTelefon)).collect(Collectors.toList());
                    break;
            }
            return y;

        }
    }
}
