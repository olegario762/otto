/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class Vehiculo {

    String departamento, placa, dpi, nombre, marca, modelo;
    int anio;
    int multas;
    int traspasos;
    ListaMultasDoble listaMultas = new ListaMultasDoble();
    ListaTraspasosCircular listaTraspasos = new ListaTraspasosCircular();

    public Vehiculo(String departamento, String placa, String dpi, String nombre, String marca, String modelo, int anio, int multas, int traspasos) {
        this.departamento = departamento;
        this.placa = placa;
        this.dpi = dpi;
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.multas = multas;
        this.traspasos = traspasos;
    }

    public String getPlaca() {
        return placa;
    }

    public int getMultas() {
        return multas;
    }

    public void setMultas(int multas) {
        this.multas = multas;
    }

    public int getTraspasos() {
        return traspasos;
    }

    public void setTraspasos(int traspasos) {
        this.traspasos = traspasos;
    }

    public ListaMultasDoble getListaMultas() {
        return listaMultas;
    }

    public ListaTraspasosCircular getListaTraspasos() {
        return listaTraspasos;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
}
