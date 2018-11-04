/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema1_apostolstefan;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Apostol
 */
public class Contact implements Serializable {

    public String nume;
    public String prenume;         //campurile unui Contact
    public LocalDate data_nastere;
    public NrTel telefon;
    public String numar;

    //getteri,setteri
    public String getNume() {    //getter pentru Nume
        return nume;
    }

    public String getPrenume() {   //getter pentru Prenume
        return prenume;
    }

    public LocalDate getData_nastere() {   //getter pentru data nasterii
        return data_nastere;
    }

    public NrTel getTelefon() {    //getter pentru nr Telefon
        return telefon;
    }

    //builder
    public Contact(String nume, String prenume, LocalDate data_nastere, String numar) {   //Nu am folosit setteri ci builder pentru clasa Contact.
        this.nume = nume;
        this.prenume = prenume;
        this.data_nastere = data_nastere;
        this.numar = numar;

        if (nume.length() < 2 || prenume.length() < 2) {                 
            throw new RuntimeException("NUMELE SAU PRENUMELE PREA SCURT!"); //Aruncarea de eroare nume scurt
        } else {

            if (numar.startsWith("07") || numar.startsWith("03") || numar.startsWith("02")) {  //testare daca este numar mobil/fix sau invalid.

                if (numar.startsWith("07")) {
                    telefon = new NrMobil(numar);
                }
                if (numar.startsWith("02") || numar.startsWith("03")) {
                    telefon = new NrFix(numar);
                }
                } else {
                    throw new RuntimeException("Numarul nu incepe cu 07,02 sau 03!");  //Aruncarea de eroare numar invalid
                }

            }
        }

        @Override
        public String toString() {  //rescrierea functiei toString pentru afisarea campurilor intr-o anumita ordine
        return nume + " " + prenume + " " + data_nastere + "  " + telefon;
        }

        @Override
        public boolean equals(Object c) { //rescrierea functiei equals pentru a verifica daca exista 2 contacte identice
        if (((Contact) c).nume.equalsIgnoreCase(nume) && ((Contact) c).prenume.equalsIgnoreCase(prenume) && ((Contact) c).telefon.equals(telefon) && ((Contact) c).data_nastere.equals(data_nastere)) {
                return true;
            }
            return false;
        }
    }
