package principal.controladores.listacancelacion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import principal.controladores.Bases_de_Datos.PeluqueriaBD;

import java.io.IOException;

public class ListaCancelaciones {

    @FXML private Button btnBuscar;

    /*-------TEXT-----------*/
    @FXML private TextField txtDni;
    @FXML private TextField txtNumCancelaciones;
    PeluqueriaBD pBD=new PeluqueriaBD();




    public void NumeroCancelaciones(MouseEvent mouseEvent){

        int numCancelaciones=pBD.NumCancelacionesDNI(txtDni.getText());//Sacmos el numero de citas canceladas que contengan el dni que se le introduce
        txtNumCancelaciones.setText(numCancelaciones+""); //Mostramos el valor devuelto en el TextField
    }

    /*---------VOLVER AL PRINCIPAL--------------*/
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
