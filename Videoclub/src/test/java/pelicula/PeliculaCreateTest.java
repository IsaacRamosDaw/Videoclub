package pelicula;

import com.mycompany.videoclub.DAO.IPeliculaImpl;
import com.mycompany.videoclub.Modelos.Pelicula;

/**
 * Prueba la creación de una nueva película.
 */
public class PeliculaCreateTest {

  public static void main(String[] args) {

    System.out.println("--- PRUEBA DE CREACIÓN DE NUEVA PELÍCULA ---");

    IPeliculaImpl peliculaDAO = new IPeliculaImpl();

    // Datos de la nueva película a crear
    String nombre = "Interestelar";
    String director = "Christopher Nolan";
    String lanzamiento = "2014-11-07";
    String genero = "Ciencia Ficción";
    int cantidad = 8;

    try {
      boolean creacionExitosa = peliculaDAO.createPelicula(nombre, director, lanzamiento, genero, cantidad);

      if (creacionExitosa) {
        System.out.println("\nCREACIÓN EXITOSA: Película '" + nombre + "' insertada.");

        Pelicula nuevaPelicula = peliculaDAO.getPelicula(nombre);

        if (nuevaPelicula != null) {
          System.out.println("\n--- VERIFICACIÓN DE LECTURA ---");
          System.out.println("ID asignado: " + nuevaPelicula.getId());
          System.out.println("Director leído: " + nuevaPelicula.getDirector());
          System.out.println("Unidades en Stock: " + nuevaPelicula.getCantidad());
        } else {
          System.err.println("WARNING: La película se insertó, pero falló la lectura de verificación.");
        }

      } else {
        System.err.println("FALLIDA: El método createPelicula devolvió false (0 filas afectadas o error de BD).");
      }

    } catch (Exception e) {
      System.err.println("ERROR FATAL durante la prueba de creación: " + e.getMessage());
      e.printStackTrace();
    }
  }
}