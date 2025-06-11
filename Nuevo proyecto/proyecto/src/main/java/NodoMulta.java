/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class NodoMulta {
    String departamento, placa, fecha, descripcion;
    double monto;
    NodoMulta siguiente, anterior;

    public NodoMulta(String departamento, String placa, String fecha, String descripcion, double monto) {
        this.departamento = departamento;
        this.placa = placa;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
    }

    @Override
    public String toString() {
        return placa + "," + fecha + "," + descripcion + "," + monto;
    }

    
    
}
