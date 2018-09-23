/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
    import java.io.BufferedReader;
    import java.io.File;
    import java.io.FileNotFoundException;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.io.PrintWriter;
    import javax.swing.JOptionPane;
    import javax.swing.JFileChooser;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import models.ModelBloc;
    import views.ViewBlocNotas;     
    import extras.DataValidation;
    
/**
 *
 * @author Octaviano
 */
public class ControllerBloc {
   ModelBloc modelBlock;
   ViewBlocNotas vieBlocNotas;
   DataValidation dataValidation = new DataValidation();
   
   ActionListener al = new ActionListener(){
       @Override
       public void actionPerformed(ActionEvent e) {
          if(e.getSource()== vieBlocNotas.jmi_leer) jmi_leer_action_performed();
          if(e.getSource()== vieBlocNotas.jmi_guardar) jmi_esribir_action_performed();  
          if(e.getSource()== vieBlocNotas.jmi_cifrar) jmi_cifrar_action_performed();
          if(e.getSource()== vieBlocNotas.jmi_descifrar) jmi_descifrar_action_performed();
       }   
   };

    public ControllerBloc(ModelBloc modelBlock, ViewBlocNotas vieBlocNotas) {
        vieBlocNotas.setVisible(true);
        this.modelBlock = modelBlock;
        this.vieBlocNotas = vieBlocNotas;
        this.vieBlocNotas.jmi_leer.addActionListener(al);
        this.vieBlocNotas.jmi_guardar.addActionListener(al);
        this.vieBlocNotas.jmi_cifrar.addActionListener(al);
        this.vieBlocNotas.jmi_descifrar.addActionListener(al);
    }
    /**
     * En este metodo se llama al metodo de readFile 
     * y se le agrega su parametro para que pueda realizar su funcion de manera correcta
     * @param path 
     */
    public void jmi_leer_action_performed(){
        this.readFile();
    }
    /**
     * En este metodo se llama al metodo de writeFile
     * y se le agrega su parametro para que pueda realizar su funcion de manera correcta
     */
    public void jmi_esribir_action_performed(){
        this.writeFile(vieBlocNotas.jta_contenido.getText());
    }
    /**
     * En este metodo se llama al metodo de cifradoCesar
     * y se le agrega su parametro para que pueda realizar su funcion de manera correcta
     */
    public void jmi_cifrar_action_performed(){
        this.cifrado();
    }
    /**
     * En este metodo se llama al metodo de descifradoCesar
     * y se le agrega su parametro para que pueda realizar su funcion de manera correcta
     */
    public void jmi_descifrar_action_performed(){
        this.descifrado();
    }
    /**
     * En este metodo se lee un archivo
     * El archivo se vera en un TextArea 
     * @param path 
     */
    public String readFile(){
        try{  
            String acumula_lineas ="";
            String row; // indica una fila
            try {
                JFileChooser fileChooser = new JFileChooser(); //muestra directamente la venatana de JFileChosser
                int seleccion = fileChooser.showOpenDialog(vieBlocNotas.jta_contenido); 
                
                File archivo = fileChooser.getSelectedFile();
                if (archivo != null){
                    FileReader archivos = new FileReader(archivo);
                    BufferedReader bufferedReader = new BufferedReader(archivos);
                        while ((row=bufferedReader.readLine())!= null){
                            
                           acumula_lineas+=row+"\n"; // si el archivo tiene mas filas con esta linea ira mostrando cada una de ellas
                        }
                        vieBlocNotas.jta_contenido.setText(acumula_lineas); //abre el archivo en la TextArea
                        bufferedReader.close();
                }
        } catch(FileNotFoundException err){
            JOptionPane.showMessageDialog(null,"File not found"+err.getMessage());
        }
            
        } catch(IOException err){
            JOptionPane.showMessageDialog(null,"error on IO operation:"+err.getMessage());
        } 
       return null;
        
    }//ReadFile    
    /**
     * Escribe nuevas lineas en el archivo
     */
    public String writeFile (String mensaje) {
        try{
            JFileChooser file=new JFileChooser();
            file.showSaveDialog(vieBlocNotas.jta_contenido);
            File guardar =file.getSelectedFile();
            if(guardar !=null){
              FileWriter  save=new FileWriter(guardar);
              save.write(mensaje);
              save.close();
              JOptionPane.showMessageDialog(null,"El archivo se a guardado Exitosamente");
              }              
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"File not found:"+e.getMessage());
        } 
       return null;   
    }//WriteFile
    /*
    *Este metodo generara el cifrado cesar, el cual sera propporcionado por el usuario 
    *maneja un arreglo que sera el encargado de almacenar cada caracter de la cadena dada 
    *para despues cada caracter sera convertido a su valor correspodiente segun el valor del cifrado
    *usara el codigo ascii y se le sumara el numero que sea introducidopor el usuario.
    */
    public String cifrado(){
        char array[]; //almacenara letra por letra el texto proporcionado
        modelBlock.setNumero(dataValidation.String2Int(vieBlocNotas.jtf_cifrado.getText())); //numero para el cifrado otorgado por el usuario
        modelBlock.setTexto(vieBlocNotas.jta_contenido.getText());//pasar el contenido de la textarea a la variable 
        array = modelBlock.getTexto().toCharArray(); // convertir el texto a un arreglo 
        for(int i = 0; i<array.length; i++){ //la variable i ira de 0 a la catidad de caracteres dentro del arreglo
            array[i]=(char)(array[i] + (char)modelBlock.getNumero()); //hace la conversion de  cada caracter
        }
        modelBlock.setTexto_desencriptado(String.valueOf(array)); // pasamos el texto encriptado a un String y lo almacenamos en la variable
        vieBlocNotas.jta_contenido.setText(modelBlock.getTexto_desencriptado()); //mostramos en el textarea el testo encriptado       
       return null;         
    }
    /*
    *Este metodo generara el descifrado cesar, el cual sera propporcionado por el usuario 
    *maneja un arreglo que sera el encargado de almacenar cada caracter de la cadena dada 
    *para despues cada caracter sera convertido a su valor correspodiente
    *usara el codigo ascii y se le restara el numero que sea introducidopor el usuario 
    /ya que se aplica la operaion inversa al cifrado 
    */ 
    public String descifrado(){
        char array[]; //almacenara letra por letra el texto proporcionado
        modelBlock.setNumero(dataValidation.String2Int(vieBlocNotas.jtf_cifrado.getText())); //numero para el descifrado otorgado por el usuario
        modelBlock.setTexto(vieBlocNotas.jta_contenido.getText());//pasar el contenido de la textarea a la variable 
        array = modelBlock.getTexto().toCharArray(); // convertir el texto a un arreglo 
        for(int i = 0; i<array.length; i++){ //la variable i ira de 0 a la catidad de caracteres dentro del arreglo
            array[i]=(char)(array[i]-(char)modelBlock.getNumero()); //hace la conversion de  cada caracter
        }
        modelBlock.setTexto_desencriptado(String.valueOf(array)); // pasamos el texto desencriptado a un String y lo almacenamos en la variable
        vieBlocNotas.jta_contenido.setText(modelBlock.getTexto_desencriptado()); //mostramos en el textarea el testo desencriptado       
       return null;      
    }   
}//class
