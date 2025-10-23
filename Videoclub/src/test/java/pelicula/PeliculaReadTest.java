package pelicula;

import com.mycompany.videoclub.DAO.IPeliculaImpl;
import com.mycompany.videoclub.Modelos.Pelicula;

/**
 * Prueba la lectura de una sola película por ID.
 */
public class PeliculaReadTest {

    public static void main(String[] args) {
        
        System.out.println("--- PRUEBA DE LECTURA DE PELÍCULA POR ID (ID: 1) ---");
        
        IPeliculaImpl peliculaDAO = new IPeliculaImpl();
        final int ID_A_LEER = 1;

        try {
            Pelicula pelicula = peliculaDAO.getPelicula(ID_A_LEER);

            if (pelicula != null) {
                System.out.println("\nLECTURA EXITOSA: Película encontrada.");
                System.out.println("----------------------------------------");
                System.out.println("ID: " + pelicula.getId());
                System.out.println("Título: " + pelicula.getNombre());
                System.out.println("Director: " + pelicula.getDirector());
                System.out.println("Lanzamiento: " + pelicula.getLanzamiento());
                System.out.println("Género: " + pelicula.getGenero());
                System.out.println("Cantidad: " + pelicula.getCantidad());
                System.out.println("----------------------------------------");
                
            } else {
                System.err.println("\nLECTURA FALLIDA: No se encontró ninguna película con ID " + ID_A_LEER + ".");
            }
            
        } catch (Exception e) {
            System.err.println("ERROR FATAL durante la prueba de lectura: " + e.getMessage());
            e.printStackTrace();
        }
    }
}