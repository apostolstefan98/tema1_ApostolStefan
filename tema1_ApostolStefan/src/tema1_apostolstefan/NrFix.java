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
public class NrFix extends NrTel implements Serializable { 

    public NrFix(String numar_telefon) {  //constructor
        super(numar_telefon);
    }

    @Override
    public boolean validareNumar(String numar) {  //metoda abstracata validareNumar din NrTel care trebuie definita 
        if (numar.length() == 10) {
            return true;
        } else {
            throw new RuntimeException("Numaru nu are 10 cifre..."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
