/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


/**
 *
 * @author Octaviano
 */
public class ModelBloc {
   String message;
   String texto; //almacenara el texto a encriptar 
   String texto_encriptado; //almacenara el texto encriptado
   String texto_desencriptado; //almaenara el texto desencriptado
   int numero;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
   
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTexto_encriptado() {
        return texto_encriptado;
    }

    public void setTexto_encriptado(String texto_encriptado) {
        this.texto_encriptado = texto_encriptado;
    }

    public String getTexto_desencriptado() {
        return texto_desencriptado;
    }

    public void setTexto_desencriptado(String texto_desencriptado) {
        this.texto_desencriptado = texto_desencriptado;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }    
}//class
