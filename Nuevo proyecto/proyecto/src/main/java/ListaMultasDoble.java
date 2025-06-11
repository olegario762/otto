
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ixtamer
 */
public class ListaMultasDoble {
    NodoMulta cabeza, cola;

    public void agregarMulta(String departamento, String placa, String fecha, String descripcion, double monto) {
        NodoMulta nueva = new NodoMulta(departamento, placa, fecha, descripcion, monto);
        if (cabeza == null) {
            cabeza = cola = nueva;
        } else {
            cola.siguiente = nueva;
            nueva.anterior = cola;
            cola = nueva;
        }
    }

    public void guardarMultasEnArchivosExistentes(String rutaPrincipal) {
        if (cabeza == null) {
            System.out.println("La lista de multas está vacía.");
            return;
        }

        NodoMulta actual = cabeza;
        HashSet<String> departamentosProcesados = new HashSet<>();

        while (actual != null) {
            String departamento = actual.departamento;

            if (!departamentosProcesados.contains(departamento)) {
                departamentosProcesados.add(departamento);

                File carpeta = new File(rutaPrincipal + File.separator + departamento);
                if (!carpeta.exists()) carpeta.mkdirs();

                File archivo = new File(carpeta, departamento + "_multas.txt");

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                    NodoMulta temp = cabeza;
                    while (temp != null) {
                        if (temp.departamento.equals(departamento)) {
                            bw.write(temp.toString());
                            bw.newLine();
                        }
                        temp = temp.siguiente;
                    }
                    System.out.println("Archivo de multas para '" + departamento + "' guardado correctamente.");
                } catch (IOException e) {
                    System.out.println("Error al guardar el archivo de " + departamento + ": " + e.getMessage());
                }
            }

            actual = actual.siguiente;
        }
    }

    public void cargarMultasDesdeArchivo(String departamento, String rutaPrincipal) {
        File archivo = new File(rutaPrincipal + File.separator + departamento, departamento + "_multas.txt");
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length != 4) continue;

                String placa = datos[0].trim();
                String fecha = datos[1].trim();
                String descripcion = datos[2].trim();
                double monto = Double.parseDouble(datos[3].trim());

                this.agregarMulta(departamento, placa, fecha, descripcion, monto);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar multas: " + e.getMessage());
        }
    }

    public int contarMultas() {
        int conteo = 0;
        NodoMulta temp = cabeza;
        while (temp != null) {
            conteo++;
            temp = temp.siguiente;
        }
        return conteo;
    }

    public void mostrarMultas() {
        NodoMulta temp = cabeza;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.siguiente;
        }
    }
    
}
