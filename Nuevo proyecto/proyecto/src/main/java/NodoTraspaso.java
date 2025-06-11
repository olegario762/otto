/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class NodoTraspaso {
    String placa, dpiAnterior, nombreAnterior, fecha, dpiNuevo, nombreNuevo, departamento;
    NodoTraspaso siguiente;

    public NodoTraspaso(String placa, String dpiAnterior, String nombreAnterior, String fecha,
                        String dpiNuevo, String nombreNuevo, String departamento) {
        this.placa = placa;
        this.dpiAnterior = dpiAnterior;
        this.nombreAnterior = nombreAnterior;
        this.fecha = fecha;
        this.dpiNuevo = dpiNuevo;
        this.nombreNuevo = nombreNuevo;
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return placa + "," + dpiAnterior + "," + nombreAnterior + "," + fecha + "," + dpiNuevo + "," + nombreNuevo + "," + departamento;
    }

    
    
}
