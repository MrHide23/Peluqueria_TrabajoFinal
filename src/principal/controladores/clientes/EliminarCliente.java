package principal.controladores.clientes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import principal.controladores.Bases_de_Datos.ClientesBD;
import principal.controladores.Bases_de_Datos.PeluqueriaBD;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EliminarCliente{
    //Button
    @FXML private Button btnEliminar;
    @FXML private Button btnCancelar;
    PeluqueriaBD pBD= new PeluqueriaBD();

    //Text
    @FXML private TextField txtDNI;


    public void EliminarClienteExistente(MouseEvent mouseEvent){

        pBD.EliminarCliente(txtDNI.getText());
        
    }

    public void VolverdeEliminarClientes(){
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
