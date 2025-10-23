package pelicula;

import com.mycompany.videoclub.DAO.IPeliculaImpl;
import com.mycompany.videoclub.Modelos.Pelicula;

/**
 * Prueba la funcionalidad de listar todas las películas (getAllPelicula).
 */
public class PeliculaReadAllTest {

    public static void main(String[] args) {

        System.out.println("--- PRUEBA DE LECTURA DE TODAS LAS PELÍCULAS (getAllPelicula) ---");

        IPeliculaImpl peliculaDAO = new IPeliculaImpl();

        try {
            Pelicula[] listaPeliculas = peliculaDAO.getAllPelicula();

            if (listaPeliculas == null || listaPeliculas.length == 0) {
                System.out.println("\nLa base de datos está conectada, pero no se encontraron películas.");
                return;
            }

            System.out.println("\nLectura exitosa. Películas encontradas: " + listaPeliculas.length);
            System.out.println("---------------------------------------------------------------------------------");

            for (Pelicula p : listaPeliculas) {
                System.out.printf("ID: %d | Título: %s | Director: %s | Stock: %d%n",
                        p.getId(), p.getNombre(), p.getDirector(), p.getCantidad());
            }

            System.out.println("---------------------------------------------------------------------------------");

        } catch (Exception e) {
            System.err.println("\nERROR FATAL durante la prueba de lectura: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
