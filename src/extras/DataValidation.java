/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extras;

import javax.swing.JOptionPane;

/**
 *
 * @author Octaviano
 */
public class DataValidation {
        public int String2Int(String value){
        float numero =0;
        try{
            numero = Integer.parseInt(value);   
        }catch(NumberFormatException err){
            JOptionPane.showMessageDialog(null,"Error ingresa solo datos numericos");
            numero=0;
        }
        return (int) numero;
    }
}
