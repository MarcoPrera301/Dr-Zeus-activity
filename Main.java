import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        char[] texto = null;

        try {
            texto = leerArchivoACharArray("input.txt");

            System.out.println(texto);
            System.out.println(texto.length);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return; // evita continuar si falla
        }

        Map<Character, Integer> frecuencias = new HashMap<>();

        for (char c : texto) {
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }

        Nodo raiz = Compress.Arbol(frecuencias);

        System.out.println("Códigos Huffman:");
        MinHeap.generarCodigos(raiz, "");
    }

    public static char[] leerArchivoACharArray(String ruta) throws IOException {
        String contenido = new String(Files.readAllBytes(Paths.get(ruta)));
        return contenido.toCharArray();
    }

    public static String leerArchivo(String ruta) throws IOException {
        return new String(Files.readAllBytes(Paths.get(ruta)));
    }
}