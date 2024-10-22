package principal.controladores.citas;

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
import java.util.ResourceBundle;

public class EliminarCita implements Initializable {
    @FXML private Button btnEliminar;

     //Text
    @FXML private TextField txtCodigoCita;

    //Check box
    @FXML private RadioButton rbCompleta;
    @FXML private RadioButton rbCancelada;
    PeluqueriaBD pBD=new PeluqueriaBD();

    public void EliminarlaCita(MouseEvent mouseEvent){

        if (!txtCodigoCita.getText().equals("") &&( rbCompleta.isSelected() || rbCancelada.isSelected())) {//Comprobamos si uno de los dos radio buttons esta seleccionado

            String estado=SacarEstado();
            int codigo=Integer.parseInt(txtCodigoCita.getText());
            System.out.println(codigo);
            pBD.EliminarCitadeTable(codigo,estado);
        }

    }

    private String SacarEstado() {
        String estado=null;
        if (rbCompleta.isSelected()) {
            estado="completa";
        }else if (rbCancelada.isSelected()) {
            estado="cancelada";
        }

        return estado;
    } //Depende de que radio button aya seleccionada devolvemos "completada" o "seleccionada"


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Grupo de Radio Buttons
        ToggleGroup estado=new ToggleGroup(); //Definimos un ToggleGroup para que unicamente pueda seleccionar uno de los radio buttons
        rbCancelada.setToggleGroup(estado);
        rbCompleta.setToggleGroup(estado);

    }

    public void VolverdeEliminarCitas(){
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
