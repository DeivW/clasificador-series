package soporte;

import negocio.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/*
* Clase que se utilizará para cargar la tabla con los datos del archivo.
* */

public class DataSet {

    private File file;

    public DataSet(String ruta) {
        file = new File(ruta);
    }

    public Generos cargarDataset(){

        String linea;
        String[] campos;
        // referencia a la clase que contiene la Hashtable como atributo.
        Generos generos = new Generos();

        ArrayList<String> generis = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {

                // linea = cada linea del archivo csv que contiene una serie.
                // campos = cada campo de una linea que es delimitado por una coma ","
                linea = sc.nextLine();
                campos = linea.split(",");

                // campo_generos = campo nro 4 es de géneros. Hay varios géneros separados con un pipe |
                String campo_generos = campos[4];

                // generos_por_campo = cada serie puede tener varios generos. Array que guarda cada genero de una linea.
                String[] generos_por_campo = campo_generos.split("\\|");

                if(!(campos[4].equals("Genre"))){
                    for (int i = 0; i < generos_por_campo.length; i++){
                        if (!(generos.revisarSiEsta(generos_por_campo[i]))){
                            Genero genero = new Genero(generos_por_campo[i]);
                            genero.agregarSerie(campos[0]);
                            genero.sumarCantidad();
                            float punt = Float.parseFloat(campos[5]);
                            int puntInt = (int)punt;
                            genero.sumarCantidadPuntuacion(puntInt);
                            generos.agregarSerie(generos_por_campo[i], genero);
                        }
                        else{
                            generos.agregarSerie(generos_por_campo[i], campos[0]);
                            generos.sumarCantidad((generos_por_campo[i]));
                            float punt = Float.parseFloat(campos[5]);
                            int puntInt = (int)punt;
                            generos.sumarCantPuntuacion(generos_por_campo[i], puntInt);
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se pudo abrir el archivo " + file.getName());
        }

        finally{
            return generos;
        }
    }
}
