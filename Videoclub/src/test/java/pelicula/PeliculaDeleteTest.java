package pelicula;

import com.mycompany.videoclub.DAO.IPeliculaImpl;
import com.mycompany.videoclub.Modelos.Pelicula;

/**
 * Prueba la funcionalidad de eliminación de la película (deletePelicula).
 */
public class PeliculaDeleteTest {

    public static void main(String[] args) {
        
        System.out.println("--- PRUEBA DE ELIMINACIÓN DE PELÍCULA (ID: 1) ---");
        
        IPeliculaImpl peliculaDAO = new IPeliculaImpl();
        final int ID_A_ELIMINAR = 1;

        try {
            String resultadoEliminacion = peliculaDAO.deletePelicula(ID_A_ELIMINAR);

            if (resultadoEliminacion.startsWith("Película con ID")) {
                System.out.println("\nELIMINACIÓN EXITOSA: " + resultadoEliminacion);
                
                Pelicula peliculaEliminada = peliculaDAO.getPelicula(ID_A_ELIMINAR);
                
                if (peliculaEliminada == null) {
                    System.out.println("--- VERIFICACIÓN DE LECTURA ---");
                    System.out.println("La película con ID " + ID_A_ELIMINAR + " ya no existe en la DB. Prueba completada.");
                } else {
                    System.err.println("WARNING: La eliminación fue reportada como exitosa, pero la película sigue siendo legible.");
                }

            } else if (resultadoEliminacion.startsWith("No se encontró")) {
                System.out.println("\nELIMINACIÓN (SIN ENCONTRAR): " + resultadoEliminacion);
                
            } else {
                System.err.println("\nFALLO EN LA ELIMINACIÓN (Mensaje de error): " + resultadoEliminacion);
            }
            
        } catch (Exception e) {
            System.err.println("ERROR FATAL durante la prueba de eliminación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}