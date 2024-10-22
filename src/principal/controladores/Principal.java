package principal.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import principal.controladores.citas.Citas;
import principal.controladores.clientes.Clientes;
import principal.controladores.listacancelacion.ListaCancelaciones;

import java.io.IOException;

public class Principal{
    @FXML private Button btnClientes;
    @FXML private Button btnCitas;
    @FXML private Button btnCancelaciones;


    public void AbrirClientes(MouseEvent mouse){

         try {
             FXMLLoader clientesPrincipal= new FXMLLoader(getClass().getResource("/principal/formularios/clientesfx/ClientesPrincipal.fxml"));
             Parent padre=clientesPrincipal.load();

             Clientes clientes= clientesPrincipal.getController();

             Scene scene=new Scene(padre);
             Stage stage=new Stage();

             stage.setScene(scene);
             stage.show();

             /*------LO QUE SE EJECUTA AL CERRAR ClioentesPrincipal.fxml------*/
             stage.setOnCloseRequest(e -> clientes.VolverPrincipal());

             /*-------CERRAR VENTANA ACTUAL---------------------------------*/
             Stage principal=(Stage) this.btnClientes.getScene().getWindow();
             principal.close();

         } catch (IOException e) {
             System.out.println("Message---> "+e.getMessage());
         }
    } //Variable que habre el formulario ClientesBD


    public void AbrirCitas(MouseEvent mouse){
        try {
            FXMLLoader citasPrincipal= new FXMLLoader(getClass().getResource("/principal/formularios/citasfx/CitasPrincipal.fxml"));
            Parent padre=citasPrincipal.load();

            Citas citas= citasPrincipal.getController();

            Scene scene=new Scene(padre);
            Stage stage=new Stage();

            stage.setScene(scene);
            stage.show();

            /*------LO QUE SE EJECUTA AL CERRAR ClioentesPrincipal.fxml------*/
            stage.setOnCloseRequest(e -> citas.VolverPrincipal());

            /*-------CERRAR VENTANA ACTUAL---------------------------------*/
            Stage principal=(Stage) this.btnCitas.getScene().getWindow();
            principal.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//Variable que nos manda a la ventana Citas


    public void AbrirListaCancelaciones(MouseEvent mouse){
        try {
            FXMLLoader listaCancelaciones= new FXMLLoader(getClass().getResource("/principal/formularios/listacancelacionfx/ListaCancelaciones.fxml"));
            Parent padre=listaCancelaciones.load();

            ListaCancelaciones listaCan= listaCancelaciones.getController();

            Scene scene=new Scene(padre);
            Stage stage=new Stage();

            stage.setScene(scene);
            stage.show();

            /*------LO QUE SE EJECUTA AL CERRAR ClioentesPrincipal.fxml------*/
            stage.setOnCloseRequest(e -> listaCan.VolverPrincipal());

            /*-------CERRAR VENTANA ACTUAL---------------------------------*/
            Stage principal=(Stage) this.btnCancelaciones.getScene().getWindow();
            principal.close();

        } catch (IOException e) {
            System.out.println("Message---> "+e.getMessage());
        }
    } //Varaible que abre la ventana Formulario

}
