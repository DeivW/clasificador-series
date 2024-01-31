package negocio;

import java.util.ArrayList;
import java.util.List;


/*
* Clase que representa a un género que será almacenado en la tabla. Contiene los datos necesarios para mostrar.
*
* */

public class Genero {

    private String nombre;
    private int cant_series;
    private int[] cant_punt;
    private ArrayList<String> series;

    public Genero(String nombre){
        this.nombre = nombre;
        this.series = new ArrayList<String>();
        this.cant_punt = new int[11];
        this.cant_series = 0;

    }

    // Métodos setters para sumar una cantidad, agregar una serie y sumar las series por puntuacion.

    public void sumarCantidad(){
        this.cant_series += 1;
    }
    public void agregarSerie(String serie){
        this.series.add(serie);
    }
    public void sumarCantidadPuntuacion(int puntuacion){
        int cant = this.cant_punt[puntuacion];
        this.cant_punt[puntuacion] = cant+1;
    }

    // Métodos getters
    public List<String> getSeries(){
        return this.series;
    }
    public int getCant_series(){
        return this.cant_series;
    }
    public int[] getCant_punt(){
        return this.cant_punt;
    }


    @Override
    public String toString() {
        return "Genero: " + this.nombre + "\n" +
                "Cantidad de series de este genero: " + this.cant_series + "\n" +
                "Series: " + this.series.toString() + "\n" +
                "Series por puntuacion: " + this.cant_punt[5];
    }
}
