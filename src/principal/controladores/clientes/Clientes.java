package principal.controladores.clientes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import principal.controladores.Bases_de_Datos.ClientesBD;
import principal.controladores.Bases_de_Datos.PeluqueriaBD;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Clientes  implements Initializable {
    @FXML private Button btnNuevoCliente;
    @FXML private Button btnEliminarCliente;
    @FXML private Button btnModificarCliente;
    @FXML private Button btnAtras;

    ObservableList<ClientesBD> listaClientes;
    @FXML private TableView<ClientesBD> tvClientes;
    @FXML private TableColumn<ClientesBD, String> tcdni;
    @FXML private TableColumn<ClientesBD, String> tcNombre;
    @FXML private TableColumn<ClientesBD, String> tcApellido;
    @FXML private TableColumn<ClientesBD, Integer> tcNumeroTelefono;
    @FXML private TableColumn<ClientesBD, String> tcEmail;

    NuevoCliente nc=new NuevoCliente();
    PeluqueriaBD pBD=new PeluqueriaBD();
    String cosa;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PeluqueriaBD pBD=new PeluqueriaBD();

        //-----TableView------
        listaClientes= FXCollections.observableArrayList();
        pBD.ListaClientes(listaClientes);
        tvClientes.setItems(listaClientes);
        LlenarColumnas();


    }

    private void LlenarColumnas() {
        tcdni.setCellValueFactory(new PropertyValueFactory<ClientesBD, String>("dni"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<ClientesBD, String>("nombre"));
        tcApellido.setCellValueFactory(new PropertyValueFactory<ClientesBD, String>("apellido"));
        tcNumeroTelefono.setCellValueFactory(new PropertyValueFactory<ClientesBD, Integer>("numTelefono"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<ClientesBD, String>("email"));
    }


    /*-------MOVERSE A SIGIENTES VENTANAS------*/
    public void IrANuevoCliente(){
        try {
            FXMLLoader NuevoCliente= new FXMLLoader(getClass().getResource("/principal/formularios/clientesfx/ClientesNuevo.fxml"));
            Parent padre=NuevoCliente.load();

            NuevoCliente nc=new NuevoCliente();

            Scene scene=new Scene(padre);
            Stage stage=new Stage();

            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> nc.VolverdeNuevoCliente());

            /*-------CERRAR VENTANA ACTUAL---------------------------------*/
            Stage clientesStage=(Stage)this.btnNuevoCliente.getScene().getWindow();
            clientesStage.close();

        } catch (IOException e) {
            System.out.println("Message---> "+e.getMessage());
        }
    }

    public void IrAEliminarCliente(){
        try {
            FXMLLoader EliminarCliente= new FXMLLoader(getClass().getResource("/principal/formularios/clientesfx/ClientesEliminar.fxml"));
            Parent padre=EliminarCliente.load();

            EliminarCliente ec=new EliminarCliente();

            Scene scene=new Scene(padre);
            Stage stage=new Stage();

            stage.setScene(scene);
            stage.setOnCloseRequest(e -> ec.VolverdeEliminarClientes());
            stage.show();

            /*-------CERRAR VENTANA ACTUAL---------------------------------*/
            Stage clientesStage=(Stage)this.btnEliminarCliente.getScene().getWindow();
            clientesStage.close();

        } catch (IOException e) {
            System.out.println("Message---> "+e.getMessage());
        }

    }

    public void IrAModificarCliente(){
        try {
            FXMLLoader EliminarCliente= new FXMLLoader(getClass().getResource("/principal/formularios/clientesfx/ClientesModificar.fxml"));
            Parent padre=EliminarCliente.load();

            ModificarCliente mc=new ModificarCliente();

            Scene scene=new Scene(padre);
            Stage stage=new Stage();

            stage.setScene(scene);

            stage.show();
            stage.setOnCloseRequest(e -> mc.VolveraClientes());
            /*-------CERRAR VENTANA ACTUAL---------------------------------*/

            Stage clientesStage=(Stage)this.btnModificarCliente.getScene().getWindow();
            clientesStage.close();

        } catch (IOException e) {
            System.out.println("Message---> "+e.getMessage());
        }
    }

    /*-----RETORNAR AL PRINCIPAL---------*/

    public void VolverPrincipal(){
        try {
            FXMLLoader principal= new FXMLLoader(getClass().getResource("/principal/formularios/principal/principal.fxml"));
            Parent padre=principal.load();

            Scene scene=new Scene(padre);
            Stage stage=new Stage();

            stage.setScene(scene);
            stage.show();

            /*-------CERRAR VENTANA ACTUAL---------------------------------*/
            Stage clientesStage=(Stage)this.btnAtras.getScene().getWindow();
            clientesStage.close();

        } catch (IOException e) {
            System.out.println("Message---> "+e.getMessage());
        }
    }

}
