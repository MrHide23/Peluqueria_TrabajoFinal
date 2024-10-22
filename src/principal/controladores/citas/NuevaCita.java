package principal.controladores.citas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import principal.controladores.Bases_de_Datos.PeluqueriaBD;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class NuevaCita implements Initializable {

    //Buttons
    @FXML private Button btnAnyadirCita;
    @FXML private TextField txtDni;

    //Check box
    @FXML private CheckBox cbxSecado;
    @FXML private CheckBox cbxCorte;
    @FXML private CheckBox cbxTinte;

    //Selector fecha
    @FXML private DatePicker dPFechaCita;
    PeluqueriaBD pBD =new PeluqueriaBD();


    //Check box
    @FXML private ComboBox<Integer> cbHora;
    @FXML private ComboBox<Integer> cbMinutos;
    ObservableList<Integer>Horas= FXCollections.observableArrayList(9,10,11,12,13,14,15,16,17,18,19,20,21,22);

    ObservableList<Integer>Minutos=FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,
                                                                        17,18,19,20,21,22,23,24,25,26,27,28,29,30,
                                                                        31,32,33,34,35,36,37,38,39,40,41,42,43,44,
                                                                        45,46,47,48,49,50,51,52,53,54,55,56,57,58,59);

    //Date picker
    @FXML private DatePicker dtFechaCita;


    public void InsertarNuevoCliente(MouseEvent mouseEvent) {
        boolean completos=CamposCompletos();
        LocalTime horaCita;
        LocalDate fechaCita=dPFechaCita.getValue();

        if (completos) {

            horaCita= SacarHoraCita();

            completos=pBD.HoraCogida(fechaCita, horaCita);


            if (completos) {

                pBD.CrearCita(txtDni.getText(),fechaCita,horaCita, cbxSecado.isSelected(), cbxCorte.isSelected(), cbxTinte.isSelected());

            }else{
                Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("INFORMACION");
                alerta.setContentText("Hora Ocupada, Selecciona una nueva Hora");
                alerta.showAndWait();
            }

        }else{
            Alert alerta=new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMACION");
            alerta.setContentText("CAMPOS INCOMPLETOS");
            alerta.showAndWait();
        }

    }


    private LocalTime SacarHoraCita() {
        LocalTime horaCita=null;
        horaCita=LocalTime.of(cbHora.getValue(),cbMinutos.getValue());
        return horaCita;
    } //Sacmos los valores de cbHoras y de cbMinutos y los almacenamos en una tipo Time para sacar la hora de la cita

    private boolean CamposCompletos() {
        if ( !txtDni.getText().equals("") && dPFechaCita.getValue()!=null
                && (cbHora.getValue()!=0 ||
                cbMinutos.getValue()!=0) && (cbxSecado.isSelected() || cbxCorte.isSelected() || cbxTinte.isSelected())) {

            return true;
        }

        return false;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Combobox de horas y minutos
        cbHora.setItems(Horas);
        cbMinutos.setItems(Minutos);

    }



    public void VolveraPrincipal(){
        try {
            FXMLLoader principal= new FXMLLoader(getClass().getResource("/principal/formularios/principal/principal.fxml"));
            Parent padre=principal.load();

            Scene scene=new Scene(padre);
            Stage stage=new Stage();

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
