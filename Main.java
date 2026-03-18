import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            char[] miArray = leerArchivoACharArray("input.txt");

            System.out.println(miArray);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static char[] leerArchivoACharArray(String ruta) throws IOException {
        String contenido = new String(Files.readAllBytes(Paths.get(ruta)));
        return contenido.toCharArray();
    }
}
