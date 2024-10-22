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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;



public class ModificarCitas implements Initializable {
    //TextFiled
    @FXML private TextField txtCodigoCita;
    @FXML private TextField txtDni;

    //DatePiker
    @FXML private DatePicker dpFecha;

    //ComboBox Horas y Minutos
    @FXML private ComboBox<Integer> cbHoras;
    @FXML private ComboBox<Integer> cbMinutos;
    ObservableList<Integer> Horas= FXCollections.observableArrayList(9,10,11,12,13,14,15,16,17,18,19,20,21,22);

    ObservableList<Integer>Minutos=FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,23,24,25,26,27,28,29,30,
            31,32,33,34,35,36,37,38,39,40,41,42,43,44,
            45,46,47,48,49,50,51,52,53,54,55,56,57,58,59);

    //Check box tratamientos
    @FXML private CheckBox chbSecado;
    @FXML private CheckBox chbCorte;
    @FXML private CheckBox chbTinte;
    PeluqueriaBD pBD=new PeluqueriaBD();

    public void ModificarCita(MouseEvent mouseEvent){
        boolean completos=CamposCompletos();
        LocalTime horaCita;
        int codigo=0;
        LocalDate fechaCita=dpFecha.getValue();

        if (completos) {

           horaCita= SacarHora();
           completos=pBD.HoraCogida(fechaCita, horaCita);

            if (completos) {
                codigo= Integer.parseInt(txtCodigoCita.getText());

                pBD.ModifcarCitaTabla(codigo, txtDni.getText(),fechaCita,
                                        horaCita, chbSecado.isSelected(), chbCorte.isSelected(), chbTinte.isSelected());

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

    private LocalTime SacarHora() {
        LocalTime horaCita=null;

        horaCita=LocalTime.of(cbHoras.getValue(),cbMinutos.getValue());


        return horaCita;
    } //Sacmos los valores de cbHoras y de cbMinutos y los almacenamos en una tipo Time para sacar la hora de la cita

    private boolean CamposCompletos() {
        if (!txtCodigoCita.getText().equals("") && !txtDni.getText().equals("") &&
            dpFecha.getValue()!=null && (cbHoras.getValue()!=0 ||
            cbMinutos.getValue()!=0) && (chbCorte.isSelected() || chbSecado.isSelected() || chbTinte.isSelected())) {
            return true;
        }

        return false;
    } //Comprobamos que todos los campos contienen informacion y no estan vacios


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Combobox de Horas y minutos
        cbHoras.setItems(Horas);
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

        } catch (IOException e) {
            System.out.println("Message---> "+e.getMessage());
        }
    }
}
