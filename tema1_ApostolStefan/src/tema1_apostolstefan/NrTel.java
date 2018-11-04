/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema1_apostolstefan;

import java.io.Serializable;

/**
 *
 * @author Apostol
 */
public abstract class NrTel implements Comparable,Serializable {

    String numar_telefon;
    
    //constructorul
    public NrTel(String numar_telefon) {
        if (validareNumar(numar_telefon) == true) {  //Apelarea metodei abstracted care este extinsa 
            this.numar_telefon = numar_telefon;       //in NrMobil si NrFix pentru a verifica daca nr de 
        } else {                                      //telefon este fix sau mobil
            //nu aruncam erori pentru ca le vom arunca in validareNumar care se afla in NrFis si NrMobil
        }
    }

    public abstract boolean validareNumar(String numar);   //metoda abstracata validareNumar care trebuie rescrisa in subclasele NrFix si NrMobil
   
    
    
    @Override
    public int compareTo(Object t) {   //metoda care compara 2 numere de telefon (verifica daca sunt lafel pentru a vedea daca exista contacte identice)
    int diferenta=((NrTel)t).numar_telefon.compareToIgnoreCase(numar_telefon);
    return diferenta;   //returneaza 0 daca sunt identice
    }

    @Override
    public boolean equals(Object o) {
     
        return (numar_telefon == null ? ((NrTel) o).numar_telefon == null : numar_telefon.equals(((NrTel) o).numar_telefon));
    }

//toString
    @Override
    public String toString() {       //rescrierea metodei toString pentru a afisa nr de telefon
        return numar_telefon;
    }
}
