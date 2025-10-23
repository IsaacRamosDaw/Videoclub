package pelicula;

import com.mycompany.videoclub.DAO.IPeliculaImpl;
import com.mycompany.videoclub.Modelos.Pelicula;

/**
 * Prueba la funcionalidad de actualización de la película (updatePelicula).
 */
public class PeliculaUpdateTest {

    public static void main(String[] args) {
        
        System.out.println("--- PRUEBA DE ACTUALIZACIÓN DE PELÍCULA (ID: 1) ---");
        
        IPeliculaImpl peliculaDAO = new IPeliculaImpl();
        final int ID_A_MODIFICAR = 1;
        
        String nuevoNombre = "Interestelar (Edición 4K)";
        String nuevoDirector = "Christopher Nolan";
        String nuevoLanzamiento = "2014-11-07";
        String nuevoGenero = "Ciencia Ficción/Drama";
        int nuevaCantidad = 15; 

        try {
            boolean actualizacionExitosa = peliculaDAO.updatePelicula(
                ID_A_MODIFICAR, nuevoNombre, nuevoDirector, nuevoLanzamiento, nuevoGenero, nuevaCantidad
            );

            if (actualizacionExitosa) {
                System.out.println("\nACTUALIZACIÓN EXITOSA: Película con ID " + ID_A_MODIFICAR + " modificada.");
                
                Pelicula peliculaDespues = peliculaDAO.getPelicula(ID_A_MODIFICAR);
                
                if (peliculaDespues != null) {
                    System.out.println("\n--- ESTADO POSTERIOR (VERIFICACIÓN) ---");
                    System.out.println("Nuevo Título: " + peliculaDespues.getNombre());
                    System.out.println("Nuevo Stock: " + peliculaDespues.getCantidad());
                } else {
                    System.err.println("WARNING: La actualización fue reportada como exitosa, pero falló la lectura de verificación.");
                }

            } else {
                System.err.println("\nFALLO EN LA ACTUALIZACIÓN: El método updatePelicula devolvió false (0 filas afectadas o ID no encontrado).");
            }
            
        } catch (Exception e) {
            System.err.println("ERROR FATAL durante la prueba de actualización: " + e.getMessage());
            e.printStackTrace();
        }
    }
}