
import java.util.ArrayList;

public class NodoAVL {
    String placa;
    ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    NodoAVL izquierdo, derecho;
    int altura;

    NodoAVL(String placa, Vehiculo vehiculo) {
        this.placa = placa;
        this.vehiculos.add(vehiculo);
        this.altura = 1;
    }

    void actualizarAltura() {
        int izq = (izquierdo == null) ? 0 : izquierdo.altura;
        int der = (derecho == null) ? 0 : derecho.altura;
        altura = 1 + Math.max(izq, der);
    }

    int obtenerBalance() {
        int izq = (izquierdo == null) ? 0 : izquierdo.altura;
        int der = (derecho == null) ? 0 : derecho.altura;
        return izq - der;
    }
}