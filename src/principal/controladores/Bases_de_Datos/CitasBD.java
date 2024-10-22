package principal.controladores.Bases_de_Datos;

import java.sql.Date;
import java.sql.Time;

public class CitasBD {

    private int codigo_cita;
    private String dni_cliente;
    private Time hora_cita;
    private Date fecha_cita;
    private boolean secado;
    private boolean corte;
    private boolean tinte;
    private String tratamiento;
    private int duracion;

    public CitasBD() {
    }

    public CitasBD(int codigo_cita, String dni_cliente,
                   Date fecha_cita, Time hora_cita,
                   boolean secado, boolean corte,
                   boolean tinte, String tratamiento,int duracion) {
        this.codigo_cita = codigo_cita;
        this.dni_cliente = dni_cliente;
        this.hora_cita = hora_cita;
        this.fecha_cita = fecha_cita;
        this.secado = secado;
        this.corte = corte;
        this.tinte = tinte;
        this.tratamiento=tratamiento;
        this.duracion=duracion;
    }

    public int getCodigo_cita() {
        return codigo_cita;
    }

    public void setCodigo_cita(int codigo_cita) {
        this.codigo_cita = codigo_cita;
    }

    public String getDni_cliente() {
        return dni_cliente;
    }

    public void setDni_cliente(String dni_cliente) {
        this.dni_cliente = dni_cliente;
    }

    public Time getHora_cita() {
        return hora_cita;
    }

    public void setHora_cita(Time hora_cita) {
        this.hora_cita = hora_cita;
    }

    public Date getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(Date fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public boolean isSecado() {
        return secado;
    }

    public void setSecado(boolean secado) {
        this.secado = secado;
    }

    public boolean isCorte() {
        return corte;
    }

    public void setCorte(boolean corte) {
        this.corte = corte;
    }

    public boolean isTinte() {
        return tinte;
    }

    public void setTinte(boolean tinte) {
        this.tinte = tinte;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
