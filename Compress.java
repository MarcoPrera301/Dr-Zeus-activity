import java.util.HashMap;
import java.util.Map;

public class Compress{
    public static Node Arbol(Map<Character, Integer> frecuencias){
        MinHeap heap = new MinHeap(3461)
        
        for (Map.Entry<Character, Integer>entry:frecuencias.entrySet()){
            heap.insertar(new Nodo(entry.getKey(), entry.getValue()));
        }

        while (heap.getSize() > 1) {

            Nodo n1 = heap.extraerMin();
            Nodo n2 = heap.extraerMin();

            Nodo nuevo = new Nodo(n1.frecuencia + n2.frecuencia, n1, n2);

            heap.insertar(nuevo);
        }

        return heap.extraerMin();
    }
}