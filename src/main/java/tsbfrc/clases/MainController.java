package tsbfrc.clases;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.stage.FileChooser;
import negocio.Generos;
import soporte.*;

import java.io.File;
import java.util.*;

public class MainController {

    public ComboBox cmbGeneros;
    public ListView lvSeries;
    public Button btnSeleccionar;
    public Button btnCantidad;
    public Button btnDetalle;
    public Button btnPuntuacion;
    public Label lblResultado;
    public Label lblUrl;
    private Generos generos;
    private String ruta;
    private String generoSelec;
    @FXML
    private TextArea txtPruebaa;

    public void initialize(){
        /*
        * Al inicializarse la ventana se carga la tabla con el archivo "series_data_clean.csv" por defecto
        * puede ser modificado luego durante la ejecución.
        * Se recorre la tabla mediante una vista y se llena el combo para seleccionar un género.
        */
        this.ruta = "series_data_clean.csv";
        DataSet ds = new DataSet(this.ruta);
        generos = ds.cargarDataset();

        Set<String> keyset = generos.getGeneros();
        Iterator<String> it = keyset.iterator();
        while(it.hasNext()){
            cmbGeneros.getItems().add(it.next());
        }
        lblUrl.setText(this.ruta);
        btnCantidad.setDisable(true);
        btnDetalle.setDisable(true);
        btnPuntuacion.setDisable(true);

    }
    /*
    * Método que se utiliza como evento para el botón que sirve para cambiar el archivo origen de datos.
    * */
    @FXML
    protected void cambiarOrigenDatos(){
        FileChooser fc = new FileChooser();
        File archivo = fc.showOpenDialog(null);
        String rutaActual = this.ruta;
        if(archivo == null){
            this.ruta = rutaActual;
        }
        else{
            this.ruta = archivo.toString();
            lblUrl.setText(this.ruta);
            DataSet ds = new DataSet(this.ruta);
            generos = ds.cargarDataset();
        }

    }

    // Llena el ListView con las series del género seleccioando
    @FXML
    protected void mostrarSeries(){

        String genero = (String)cmbGeneros.getValue();
        lvSeries.setItems(FXCollections.observableList(generos.getSeries(generoSelec)));
    }

    // Llena el ListView con la cantidad de series del género seleccionado.
    @FXML
    protected void mostrarCantidad(){
        String genero = (String)cmbGeneros.getValue();
        lvSeries.setItems(FXCollections.observableList(new ArrayList<>()));
        lvSeries.getItems().add("Cantidad de series del género seleccionado: " + generos.getCantidadSeries(generoSelec));

    }

    // Llena el ListView con las cantidades de series por puntuacion posible del género seleccionado
    @FXML
    protected void mostrarCantPorPuntuacion(){
        String genero = (String)cmbGeneros.getValue();
        int[] cantPunt = generos.getCantPuntuacion(generoSelec);
        lvSeries.setItems(FXCollections.observableList(new ArrayList<>()));
        lvSeries.getItems().add("Cantidad de series por puntuación del género: ");
        for (int i = 1; i < cantPunt.length; i++){
            lvSeries.getItems().add("Puntuacion: " + i + " - Cantidad de series: " + cantPunt[i]);
        }
    }

    // Alerta que será mostrada en caso de no seleccionar ningun género.
    @FXML
    private void mostrarAlertWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("ALERTA");
        alert.setContentText("Debe seleccionar un género de la lista!");
        alert.showAndWait();
    }

    // utilizado para seleccionar un género del comboBox, para habilitar los botones de seleccion de alguna opcion a mostrar.
    @FXML
    private void seleccionarGenero(){
        if((cmbGeneros.getValue()==null)){

            this.mostrarAlertWarning();
            btnCantidad.setDisable(true);
            btnDetalle.setDisable(true);
            btnPuntuacion.setDisable(true);

        }
        else{
            this.generoSelec = (String)cmbGeneros.getValue();
            btnCantidad.setDisable(false);
            btnDetalle.setDisable(false);
            btnPuntuacion.setDisable(false);
            lblResultado.setText("Resultado para el género " + this.generoSelec);
            lvSeries.setItems(FXCollections.observableList(new ArrayList<>()));
        }
    }
}