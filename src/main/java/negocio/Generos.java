package negocio;

import soporte.TSBHashtableDA;

import java.util.List;
import java.util.Set;

/*
* Clase que contrendrá la Hashtable que será utilizada para almacenar los generos.
* */

public class Generos {

    // Hashtable clave valor - Key: nombre del genero Value: Objeto Genero
    private TSBHashtableDA<String,Genero> table;


    public Generos(){
        this.table = new TSBHashtableDA<String,Genero>();
    }

    public void agregarSerie(String key, Genero value){
        table.put(key, value);
    }

    @Override
    public String toString() {
        return "Generos{" +
                "table=" + table.toString() +
                '}';
    }
    public String buscarGenero(String key){

        Genero genero = this.table.get(key);

        return genero.toString();
    }

    public boolean revisarSiEsta(String key){ return this.table.containsKey(key);}


    public void agregarSerie(String key, String serie){

        Genero value = this.table.get(key);
        value.agregarSerie(serie);
        this.table.put(key, value);
    }
    public void sumarCantidad(String key){
        Genero value = this.table.get(key);
        value.sumarCantidad();
        this.table.put(key, value);
    }
    public void sumarCantPuntuacion(String key, Integer puntuacion){
        Genero value = this.table.get(key);
        value.sumarCantidadPuntuacion(puntuacion);
        this.table.put(key, value);
    }

    // Métodos getters
    public Set<String> getGeneros(){
        Set<String> keyset = this.table.keySet();
        return keyset;
    }
    public List<String> getSeries(String key){
        return this.table.get(key).getSeries();
    }
    public int getCantidadSeries(String key){
        return this.table.get(key).getCant_series();
    }
    public int[] getCantPuntuacion(String key){
        return this.table.get(key).getCant_punt();
    }
}

