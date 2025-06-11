import java.io.*;
import javax.swing.table.DefaultTableModel;

public class ArbolAVL {
    NodoAVL raiz;

    public DefaultTableModel cargarVehiculosDesdeCarpetas(String rutaPrincipal) {
        DefaultTableModel modelo = crearModeloTabla();

        File carpeta = new File(rutaPrincipal);
        if (!carpeta.exists() || !carpeta.isDirectory()) return modelo;

        File[] subcarpetas = carpeta.listFiles(File::isDirectory);
        if (subcarpetas == null) return modelo;

        for (File sub : subcarpetas) {
            String nombreDepto = sub.getName();
            File archivo = new File(sub, nombreDepto + "_vehiculos.txt");

            if (!archivo.exists()) continue;

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length != 8) continue;

                    String placa = datos[0].trim();
                    String dpi = datos[1].trim();
                    String nombre = datos[2].trim();
                    String marca = datos[3].trim();
                    String modeloStr = datos[4].trim();
                    int anio = Integer.parseInt(datos[5].trim());
                    int multas = Integer.parseInt(datos[6].trim());
                    int traspasos = Integer.parseInt(datos[7].trim());

                    Vehiculo v = new Vehiculo(nombreDepto, placa, dpi, nombre, marca, modeloStr, anio, multas, traspasos);
                    insertar(v);
                    modelo.addRow(new Object[]{nombreDepto, placa, dpi, nombre, marca, modeloStr, anio, multas, traspasos});
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return modelo;
    }

    private DefaultTableModel crearModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Departamento", "Placa", "DPI", "Nombre", "Marca", "Modelo", "Año", "Multas", "Traspasos"});
        return modelo;
    }

    public void insertar(Vehiculo vehiculo) {
        raiz = insertarRec(raiz, vehiculo);
    }

    private NodoAVL insertarRec(NodoAVL nodo, Vehiculo vehiculo) {
        if (nodo == null) return new NodoAVL(vehiculo.getPlaca(), vehiculo);

        int cmp = vehiculo.getPlaca().compareTo(nodo.placa);

        if (cmp < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, vehiculo);
        } else if (cmp > 0) {
            nodo.derecho = insertarRec(nodo.derecho, vehiculo);
        } else {
            nodo.vehiculos.add(vehiculo);
            return nodo;
        }

        nodo.actualizarAltura();
        int balance = nodo.obtenerBalance();

        if (balance > 1 && vehiculo.getPlaca().compareTo(nodo.izquierdo.placa) < 0) return rotacionDerecha(nodo);
        if (balance < -1 && vehiculo.getPlaca().compareTo(nodo.derecho.placa) > 0) return rotacionIzquierda(nodo);
        if (balance > 1 && vehiculo.getPlaca().compareTo(nodo.izquierdo.placa) > 0) {
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }
        if (balance < -1 && vehiculo.getPlaca().compareTo(nodo.derecho.placa) < 0) {
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL T2 = x.derecho;

        x.derecho = y;
        y.izquierdo = T2;

        y.actualizarAltura();
        x.actualizarAltura();

        return x;
    }

    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL T2 = y.izquierdo;

        y.izquierdo = x;
        x.derecho = T2;

        x.actualizarAltura();
        y.actualizarAltura();

        return y;
    }

    public NodoAVL buscar(String placa) {
    return buscarRec(raiz, placa);
}

private NodoAVL buscarRec(NodoAVL nodo, String placa) {
    if (nodo == null) return null;

    int comparacion = placa.compareTo(nodo.placa);
    if (comparacion == 0) return nodo;
    else if (comparacion < 0) return buscarRec(nodo.izquierdo, placa);
    else return buscarRec(nodo.derecho, placa);
}

    // Recorridos para mostrar en JTable
    public DefaultTableModel recorrerInOrden() {
        DefaultTableModel modelo = crearModeloTabla();
        recorrerInOrdenRec(raiz, modelo);
        return modelo;
    }

    private void recorrerInOrdenRec(NodoAVL nodo, DefaultTableModel modelo) {
        if (nodo != null) {
            recorrerInOrdenRec(nodo.izquierdo, modelo);
            for (Vehiculo v : nodo.vehiculos) {
                modelo.addRow(new Object[]{v.getDepartamento(), v.getPlaca(), v.getDpi(), v.getNombre(), v.getMarca(), v.getModelo(), v.getAnio(), v.getMultas(), v.getTraspasos()});
            }
            recorrerInOrdenRec(nodo.derecho, modelo);
        }
    }

    public DefaultTableModel recorrerPreOrden() {
        DefaultTableModel modelo = crearModeloTabla();
        recorrerPreOrdenRec(raiz, modelo);
        return modelo;
    }

    private void recorrerPreOrdenRec(NodoAVL nodo, DefaultTableModel modelo) {
        if (nodo != null) {
            for (Vehiculo v : nodo.vehiculos) {
                modelo.addRow(new Object[]{v.getDepartamento(), v.getPlaca(), v.getDpi(), v.getNombre(), v.getMarca(), v.getModelo(), v.getAnio(), v.getMultas(), v.getTraspasos()});
            }
            recorrerPreOrdenRec(nodo.izquierdo, modelo);
            recorrerPreOrdenRec(nodo.derecho, modelo);
        }
    }

    public DefaultTableModel recorrerPostOrden() {
        DefaultTableModel modelo = crearModeloTabla();
        recorrerPostOrdenRec(raiz, modelo);
        return modelo;
    }

    private void recorrerPostOrdenRec(NodoAVL nodo, DefaultTableModel modelo) {
        if (nodo != null) {
            recorrerPostOrdenRec(nodo.izquierdo, modelo);
            recorrerPostOrdenRec(nodo.derecho, modelo);
            for (Vehiculo v : nodo.vehiculos) {
                modelo.addRow(new Object[]{v.getDepartamento(), v.getPlaca(), v.getDpi(), v.getNombre(), v.getMarca(), v.getModelo(), v.getAnio(), v.getMultas(), v.getTraspasos()});
            }
        }
    }
}
