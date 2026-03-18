import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            char[] Array = leerArchivoACharArray("input.txt");

            System.out.println(Array);
            System.out.println(Array.length);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        Map<Character, Integer> frecuencias = new HashMap<>();

        for (char c : Array{
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }

        Nodo raiz = construir(frecuencias);

        System.out.println("Códigos Huffman:");
        generarCodigos(raiz, "");
        
    }

    public static char[] leerArchivoACharArray(String ruta) throws IOException {
        String contenido = new String(Files.readAllBytes(Paths.get(ruta)));
        return contenido.toCharArray();
    }

    public static String leerArchivo(String ruta) throws IOException {
        String contenido = new String(Files.readAllBytes(Paths.get(ruta)));
        return contenido;

    }
}
