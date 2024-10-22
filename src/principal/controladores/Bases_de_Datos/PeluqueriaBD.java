package principal.controladores.Bases_de_Datos;


import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import principal.controladores.clientes.EliminarCliente;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class PeluqueriaBD {


    /*---------CLIENTES---------------*/
    public void ListaClientes(ObservableList<ClientesBD> lista){
        Statement ps=null;
        Connection con=connectarBD();
        try{
            String linea="select * from clientes";
            ps=con.createStatement();
            ResultSet rs=ps.executeQuery(linea);

            while (rs.next()){
                lista.add(new ClientesBD(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                ));
            }

        }catch (Exception e){
            System.out.println("Message---> "+e.getMessage());
        }
    } //Extraemmos los datos de los clientes


    public void InsertarCliente(String dni, String nombre, String apellido, String telefono, String email){
        int tf=Integer.parseInt(telefono);


            PreparedStatement ps=null;
            Connection con=connectarBD();
            String linea=null;

                try{

                   linea="insert into clientes(dni, nombre, apellido, num_telefono, email) values (?,?,?,?,?);";

                    ps=con.prepareStatement(linea);
                    ps.setString(1,dni);
                    ps.setString(2,nombre);
                    ps.setString(3,apellido);
                    ps.setInt(4,tf);
                    ps.setString(5,email);
                    int n=ps.executeUpdate();

                    if (n>0) {
                        Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Accion realizada");
                        alerta.setContentText("Se ha realizado la accion de forma correcta : - )");
                        alerta.showAndWait();
                    }

                }catch (Exception e){
                    System.out.println("Message---> "+e.getMessage());
                }




    } //Añadimos un cliente a la tabla clientes


    public void EliminarCliente(String dniCliente){



        try{
                Connection con=connectarBD();

                String linea="delete from clientes where dni=?";
                PreparedStatement ps=con.prepareStatement(linea);
                ps.setString(1,dniCliente);
                int n=ps.executeUpdate();
            if (n>0) {
                Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Cliente Eliminado");
                alerta.setContentText("Se ha eliminado el cliente");
                alerta.showAndWait();
            }


        }catch (Exception e){
            System.out.println("Message ----> "+e.getMessage());
        }

    } //Eliminamos el cliente que contenga ese dni


    public void ModificarCliente(String dniViejo,String DNInuevo, String nombre,
                                 String apellido, String telefono, String email){

        int tf=Integer.parseInt(telefono);


            PreparedStatement ps=null;
            Connection con=connectarBD();
            String linea=null;

            try{

                linea="update clientes set dni=?, nombre=?, apellido=?, num_telefono=?, email=? where dni=?;";

                ps=con.prepareStatement(linea);
                ps.setString(1,DNInuevo);
                ps.setString(2,nombre);
                ps.setString(3,apellido);
                ps.setInt(4,tf);
                ps.setString(5,email);
                ps.setString(6,dniViejo);
                int n=ps.executeUpdate();

                if (n>0) {
                    Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Accion realizada");
                    alerta.setContentText("Se ha realizado la accion de forma correcta : - )");
                    alerta.showAndWait();
                }

            }catch (Exception e){
                System.out.println("Message---> "+e.getMessage());
            }



    } //Modificamos el estado de los clientes



    /*---------CITAS-----------------*/

    public void ListaCitas(ObservableList<CitasBD> lista){

        Statement ps=null;
        Connection con=connectarBD();

        try{
            String linea="select * from citas order by fecha";
            ps=con.createStatement();
            ResultSet rs=ps.executeQuery(linea);

            while (rs.next()){

               String tratamiento=TratamientoCompleto(rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7));

                lista.add(new CitasBD(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getTime(4),
                        rs.getBoolean(5),
                        rs.getBoolean(6),
                        rs.getBoolean(7),
                        tratamiento,
                        rs.getInt(8)
                ));
            }

        }catch (Exception e){
            System.out.println("Message--> "+e.getMessage());
        }

    }

    public void ListarCitasPorDia(ObservableList<CitasBD>listaDia, LocalDate fecha){
        PreparedStatement ps=null;
        Connection con=connectarBD();
        Date fechaDate=Date.valueOf(fecha.toString());

        try{
            String linea="select * from citas where fecha=? order by hora;";
            ps=con.prepareStatement(linea);
            ps.setDate(1, fechaDate);
            ResultSet rs=ps.executeQuery();

            while (rs.next()){

                String tratamiento=TratamientoCompleto(rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7));

                listaDia.add(new CitasBD(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getTime(4),
                        rs.getBoolean(5),
                        rs.getBoolean(6),
                        rs.getBoolean(7),
                        tratamiento,
                        rs.getInt(8)
                ));
            }

        }catch (Exception e){
            System.out.println("Message----> "+e.getMessage());
        }
    }

    private String TratamientoCompleto(boolean secado, boolean corte, boolean tinte) {
        String t="";
        if (secado) {
            t="  secado  ";
        }
        if (corte) {
            t+="  corte  ";
        }

        if (tinte) {
            t+="  tinte";
        }

        return t;
    } //Mediante el valor de corte de pelo, secado y tinte generamos una funcion string que creara el tartamiento adecuado en forma string


    public void CrearCita(String dni, LocalDate fecha, LocalTime horacita,
                          boolean secado, boolean corte, boolean tinte){

        PreparedStatement ps=null;
        Connection con=connectarBD();

        Date fechaCita=Date.valueOf(fecha.toString()); //Convertimos la fecha y la hora en Date y Time
        Time horaCita=Time.valueOf(horacita);
            try{
                String linea="insert into citas(dni_cliente, fecha, hora, secado, corte, tinte, duracion_cita, estado_cita) values(?,?,?,?,?,?,?,?)";
                ps=con.prepareStatement(linea);
                ps.setString(1, dni);
                ps.setDate(2,fechaCita);
                ps.setTime(3, horaCita);
                ps.setBoolean(4,secado);
                ps.setBoolean(5, corte);
                ps.setBoolean(6, tinte);
                ps.setTime(7,null);
                ps.setString(8, null);
                int n=ps.executeUpdate();

                if (n>0) {
                    Alert alerta= new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Cita Insertada");
                    alerta.setContentText("La cita se ha Insertado Correctamente");
                    alerta.showAndWait();
                } //En caso de que cumpla la tarea informamos al user

            }catch (Exception e){
                System.out.println("Message---> "+e.getMessage());
            }



    } //Creamos la cita y comprobamos que el dni pertenece a un cliente registrado


    public void ModifcarCitaTabla(int codigoCita, String dni, LocalDate fecha, LocalTime hora, boolean secado, boolean corte, boolean tinte){


        Connection con=connectarBD();
        PreparedStatement ps=null;

        Date fechaDate=Date.valueOf(fecha.toString()); //Convertimos la fecha y la hora en Date y Time
        Time horaCita=Time.valueOf(hora);


            try{
                String linea="update citas set dni_cliente=?, fecha=?, hora=?, secado=?, corte=? , tinte=?, duracion_cita=null, estado_cita=null where codigo_cita=?;";

                ps=con.prepareStatement(linea);
                ps.setString(1,dni);
                ps.setDate(2, fechaDate);
                ps.setTime(3,horaCita);
                ps.setBoolean(4, secado);
                ps.setBoolean(5, corte);
                ps.setBoolean(6,tinte);
                ps.setInt(7,codigoCita);
                int n=ps.executeUpdate();

                if (n>0) {
                    Alert alerta= new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Cita Actualizada");
                    alerta.setContentText("La cita se ha actualizado con exsito");
                    alerta.showAndWait();
                }else{
                    Alert alerta= new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Codigo no encontrado");
                    alerta.setContentText("La cita se ha actualizado con exsito");
                    alerta.showAndWait();
                }//Comprobamos si se han ejecutado los canvios y avisamos al user en caso de que se ahayan ejecutado

            }catch (Exception e){
                System.out.println("Message--> "+e.getMessage());
            }

    }//Modificamos los datos de la cita y comprobamos si el codigo de la ciuta existe


    public boolean HoraCogida(LocalDate fecha, LocalTime hora) {
        boolean horaOcupada=true;
        LocalTime duracionTotal=null;
        Date fechaDate=Date.valueOf(fecha.toString());//Convertimos el valor de la fecha de LocalDate a Date

        PreparedStatement ps=null;
        Connection con=connectarBD();


        try{
            String linea="select hora, duracion_cita from citas where fecha=? ";
            ps=con.prepareStatement(linea);
            ps.setDate(1, fechaDate);
            ResultSet rs=ps.executeQuery();

            while (rs.next()){
                Time cosa = null;
                duracionTotal=rs.getTime(1).toLocalTime().plusMinutes(rs.getInt(2));

                if (duracionTotal.isAfter(hora) || rs.getTime(1).toLocalTime()==hora) {
                    horaOcupada=false;
                    System.out.println("Hora cogida----> "+duracionTotal);
                }

            }
            System.out.println("Hora Libre--> "+rs.getTime(1));

        }catch (Exception e){
            System.out.println("Message---> "+e.getMessage());
        }

        return horaOcupada;

    } //Comprobamos si LA HORA SELECCIONADA COINCIDE CON ALGUNA OTRA hora


    public void EliminarCitadeTable(int codigo_cita, String estadoCita){

            Connection con=connectarBD();
            PreparedStatement ps=null;

            ActualizarValorCita(codigo_cita, estadoCita); //Actualizamos el estado de la cita en la tabla citas antes de eliminarla

            try{
                String line="delete from citas where codigo_cita= ? ";
                ps=con.prepareStatement(line);
                ps.setInt(1,codigo_cita);
                int n=ps.executeUpdate();

                if (n>0) {
                    Alert alerta= new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Cita Eliminada");
                    alerta.setContentText("La cita se ha eliminado correctamente de la tabla CITAS");
                    alerta.showAndWait();
                }

            }catch (Exception e){

            }

    }



    private void ActualizarValorCita(int codigo_cita, String estadoCita) {
        Connection con=connectarBD();
        PreparedStatement ps=null;

        try{
            String linea="update citas set estado_cita=? where codigo_cita=? ";
            ps=con.prepareStatement(linea);
            ps.setString(1, estadoCita);
            ps.setInt(2,codigo_cita);
            ps.executeUpdate();

        }catch (Exception e){
            System.out.println("Message ---> "+e.getMessage());
        }

    } //Actualizamos el estado con el fin de poder insertar este estado en el historial de citas borradas


    /*---------LISTA CITAS-----------*/

    public int NumCancelacionesDNI(String dni){
        String linea=null;
        int n=0;
        Connection con=connectarBD();
        Statement ps;

        try{
            linea="select estado_cita from historial_cita ";

            ps=con.createStatement();
            ResultSet rs=ps.executeQuery(linea);
            while (rs.next()){
                if (rs.getString(1).equals("cancelada")) {
                    n+=1;
                }
            }


        }catch (Exception e){

        }
        return n;
    } //Sacamso el numero de canelaciones de un clientes



    /*------------------------------*/


    public static Connection connectarBD(){
        Connection con=null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/peluqueria_javafx", "root", "");
        }catch (Exception e){
            System.out.println("Error al conecta a peluqueria_javafx");
            Alert alerta= new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setContentText("ERROR EN LA CONECXION A LA TABLA");
            alerta.showAndWait();
        }

        return con;
    } //Nos conectamos a la base de datos peluqueria_javafx


}
