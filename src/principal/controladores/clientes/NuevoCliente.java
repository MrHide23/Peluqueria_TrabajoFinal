package principal.controladores.clientes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import principal.controladores.Bases_de_Datos.PeluqueriaBD;

public class NuevoCliente {
    //Button
    @FXML private Button btnAceptar;
    @FXML private Button btnCancelar;

    //TextField
    @FXML private TextField txtDni;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtEmail;
    PeluqueriaBD pBD=new PeluqueriaBD();


    public void InsertarNuevoCliente(MouseEvent mouseEvent){

        if (ComprobarDatos()) {
            pBD.InsertarCliente(txtDni.getText(), txtNombre.getText(),txtApellido.getText(), txtTelefono.getText(),
                                txtEmail.getText());  //Insertar nuevo cliente
        }else {
            Alert alerta=new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Campos Incompletos");
            alerta.setContentText("La informacion que se solicita esta incompleta");
            alerta.showAndWait();
        }//Comporbamos si todos los campos del documentos esta completos
    }

    private boolean ComprobarDatos() {
        if (!txtDni.getText().equals("") && !txtNombre.getText().equals("") &&
            !txtApellido.getText().equals("") && !txtTelefono.getText().equals("") &&
            !txtEmail.getText().equals("")) {
            return true;

        }

        return false;
    }




    public void VolverdeNuevoCliente(){
        try {
            FXMLLoader principal= new FXMLLoader(getClass().getResource("/principal/formularios/principal/principal.fxml"));
            Parent padre=principal.load();

            Scene scene=new Scene(padre);
            Stage stage=new Stage();

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Message---> "+e.getMessage());
        }
    }


}
