package principal.controladores.citas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import principal.controladores.Bases_de_Datos.CitasBD;
import principal.controladores.Bases_de_Datos.PeluqueriaBD;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Citas implements Initializable {
    //Buttons
    @FXML private Button btnNuevaCita;
    @FXML private Button btnEliminarCitas;
    @FXML private Button btnModificarCita;
    @FXML private Button btnComprobar;


    //Table View
    @FXML private TableView<CitasBD> tvTablaCitas;
    @FXML private TableColumn<CitasBD, Integer> tcCodigoCita;
    @FXML private TableColumn<CitasBD, String> tcdni;
    @FXML private TableColumn<CitasBD, Time> tcHora;
    @FXML private TableColumn<CitasBD, Date> tcFecha;
    @FXML private TableColumn<CitasBD, String> tcTratamientos;
    @FXML private TableColumn<CitasBD, Integer> tcDuracion;
    ObservableList<CitasBD> listaCitas;
    PeluqueriaBD pBD=new PeluqueriaBD();

    //Datapicker
    @FXML private DatePicker dpTareasHoy;




    public void ComprobarCitasHoy(MouseEvent mouseEvent){

        LocalDate fecha=dpTareasHoy.getValue();

        if (fecha!= null) {
            LimpiarLista(listaCitas);

            pBD.ListarCitasPorDia(listaCitas,fecha);
            tvTablaCitas.setItems(listaCitas);
            llenarTableCitas();
        }else{
            Alert alerta= new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Fecjha no encontrada");
            alerta.setContentText("Debes seleccionar una feha para poder filtrar");
            alerta.showAndWait();
        }

    }

    private void LimpiarLista(ObservableList<CitasBD> lista) {
        lista.clear();


    }//Eliminamos los elementos preexistentes de la lista y en las columnas


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //----Llenar tabalview------
        listaCitas= FXCollections.observableArrayList();
        pBD.ListaCitas(listaCitas);
        tvTablaCitas.setItems(listaCitas);
        llenarTableCitas();

    }

    private void llenarTableCitas() {

        tcCodigoCita.setCellValueFactory(new PropertyValueFactory<CitasBD,Integer>("codigo_cita"));
        tcdni.setCellValueFactory(new PropertyValueFactory<CitasBD,String>("dni_cliente"));
        tcHora.setCellValueFactory(new PropertyValueFactory<CitasBD,Time>("hora_cita"));
        tcFecha.setCellValueFactory(new PropertyValueFactory<CitasBD,Date>("fecha_cita"));
        tcDuracion.setCellValueFactory(new PropertyValueFactory<CitasBD,Integer>("duracion"));
        tcTratamientos.setCellValueFactory(new PropertyValueFactory<CitasBD, String>("tratamiento"));
    }

    /*---------MOVIMINETO ENTRE PANTALLAS-------------*/

    public void IraNuevaCita(){
        try {
            FXMLLoader NuevaCita= new FXMLLoader(getClass().getResource("/principal/formularios/citasfx/CitasNueva.fxml"));
            Parent padre=NuevaCita.load();

            NuevaCita ncita=new NuevaCita();

            Scene scene=new Scene(padre);
            Stage stage=new Stage();

            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> ncita.VolveraPrincipal());

            Stage clientesStage=(Stage)this.btnNuevaCita.getScene().getWindow();
            clientesStage.close();

        } catch (IOException e) {
            System.out.println("Message---> "+e.getMessage());
            e.printStackTrace();
        }
    }

    public void IraEliminarCita(){
        try {
            FXMLLoader EliminarCita= new FXMLLoader(getClass().getResource("/principal/formularios/citasfx/CitasEiminar.fxml"));
            Parent padre=EliminarCita.load();

            EliminarCita ec=new EliminarCita();

            Scene scene=new Scene(padre);
            Stage stage=new Stage();

            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> ec.VolverdeEliminarCitas());

            Stage clientesStage=(Stage)this.btnEliminarCitas.getScene().getWindow();
            clientesStage.close();

        } catch (Exception e) {
            System.out.println("Message---> "+e.getMessage());
        }
    }

    public void IrAModificarCita(){
        try {
            FXMLLoader EliminarCliente= new FXMLLoader(getClass().getResource("/principal/formularios/citasfx/CitasModificar.fxml"));
            Parent padre=EliminarCliente.load();

            ModificarCitas mc=new ModificarCitas();

            Scene scene=new Scene(padre);
            Stage stage=new Stage();

            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> mc.VolveraPrincipal());

            /*-------CERRAR VENTANA ACTUAL---------------------------------*/
            Stage clientesStage=(Stage)this.btnModificarCita.getScene().getWindow();
            clientesStage.close();

        } catch (IOException e) {
            System.out.println("Message---> "+e.getMessage());
        }
    }

    /*---VOLVER A PRINCIPAL---------*/
    public void VolverPrincipal(){
        try {
            FXMLLoader principal= new FXMLLoader(getClass().getResource("/principal/formularios/principal/principal.fxml"));
            Parent padre=principal.load();

            Scene scene=new Scene(padre);
            Stage stage=new Stage();

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("Message---> "+e.getMessage());
        }
    }



}
