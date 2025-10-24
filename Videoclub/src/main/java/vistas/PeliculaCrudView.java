/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import com.mycompany.videoclub.DAO.IPeliculaImpl; // Asegúrate de que esta ruta sea correcta
import com.mycompany.videoclub.Modelos.Pelicula; // Asegúrate de que esta ruta sea correcta
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date; // Necesario para manejar la fecha (si usas java.util.Date)
import java.text.SimpleDateFormat; // Necesario para formatear la fecha
// Si usas java.sql.Date, ajusta en los métodos. Asumo java.util.Date para el input.

/**
 *
 * @author Dante
 */
public class PeliculaCrudView extends javax.swing.JFrame {

    private IPeliculaImpl peliculaDAO;

    // Variables declaration - do not modify                     
    // private javax.swing.JButton addButton;
    // private javax.swing.JTextField cantidadInput;
    // private javax.swing.JButton deleteButton;
    // private javax.swing.JButton deleteTextButton;
    // private javax.swing.JTextField directorInput;
    // private javax.swing.JButton editButton;
    // private javax.swing.JTextField fechaInput;
    // private javax.swing.JTextField generoInput;
    // private javax.swing.JTextField idInput;
    // private javax.swing.JPanel jPanel1;
    // private javax.swing.JScrollPane jScrollPane1;
    // private javax.swing.JTable jTable1;
    // private javax.swing.JTextField nombreInput;
    // End of variables declaration                   

    /**
     * Creates new form PeliculaCrudView
     */
    public PeliculaCrudView() {
        initComponents();

        // 1. Inicializar el DAO y cargar la tabla
        peliculaDAO = new IPeliculaImpl();
        cargarDatosPeliculas();

        // 2. Configurar el campo ID y la fecha de forma inicial
        idInput.setEditable(false);
        idInput.setText("");

        // 3. Configurar Listeners para los botones
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteTextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTextButtonActionPerformed(evt);
            }
        });

        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt); // Implementaremos en el paso 5
            }
        });

        // 4. Listener para hacer clic en la tabla (Paso 4)
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });

        this.setTitle("Gestión de Películas (CRUD)");
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void cargarDatosPeliculas() {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            // Columnas: 6 campos (ID, Nombre, Director, Género, Fecha, Cantidad)
            String[] columnas = { "ID", "Nombre", "Director", "Género", "Fecha Lanzamiento", "Cantidad" };
            model.setColumnIdentifiers(columnas);

            Pelicula[] peliculas = peliculaDAO.getAllPelicula(); // Asegúrate de tener este método en tu DAO

            if (peliculas != null) {
                for (Pelicula p : peliculas) {

                    model.addRow(new Object[] {
                            p.getId(),
                            p.getNombre(),
                            p.getDirector(),
                            p.getGenero(),
                            p.getLanzamiento(),
                            p.getCantidad()
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar los datos de películas: " + e.getMessage(),
                    "Error de BD",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Limpia todos los campos de texto del formulario.
     */
    private void limpiarCampos() {
        idInput.setText("");
        nombreInput.setText("Nombre");
        // 💡 Cambio: usar "director" en minúsculas para coincidir con initComponents()
        directorInput.setText("director");
        generoInput.setText("Género");
        fechaInput.setText("Fecha Lanzamiento"); // Coincide con initComponents()
        cantidadInput.setText("0");
    }

    /**
     * Carga los datos de la fila seleccionada de la JTable a los campos de
     * texto.
     */
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        // Obtener los valores por índice de columna (ID=0, Nombre=1, Director=2,
        // Genero=3, Fecha=4, Cantidad=5)
        String id = model.getValueAt(fila, 0).toString();
        String nombre = model.getValueAt(fila, 1).toString();
        String director = model.getValueAt(fila, 2).toString();
        String genero = model.getValueAt(fila, 3).toString();
        String fecha = model.getValueAt(fila, 4) != null ? model.getValueAt(fila, 4).toString() : "";
        String cantidad = model.getValueAt(fila, 5).toString();

        // Asignar a los campos
        idInput.setText(id);
        nombreInput.setText(nombre);
        directorInput.setText(director);
        generoInput.setText(genero);
        fechaInput.setText(fecha);
        cantidadInput.setText(cantidad);
    }

    /**
     * Maneja el evento al pulsar el botón "Borrar" (DELETE).
     */
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String idStr = idInput.getText().trim();

        if (idStr.isEmpty() || idStr.equals("Id")) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona una película de la tabla para eliminar.",
                    "Error de Eliminación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);

            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de que quieres eliminar la película con ID " + id + "?",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {

                boolean exito = peliculaDAO.deletePeliculaById(id);

                if (exito) {
                    JOptionPane.showMessageDialog(this,
                            "Película con ID " + id + " eliminada con éxito.",
                            "Eliminación Exitosa",
                            JOptionPane.INFORMATION_MESSAGE);

                    limpiarCampos();
                    cargarDatosPeliculas(); // Refrescar la tabla
                } else {
                    JOptionPane.showMessageDialog(this,
                            "La película no pudo ser eliminada. El ID no existe o ya está en un alquiler.",
                            "Fallo en la Eliminación",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Error de formato: El ID no es un número entero válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error de Base de Datos al eliminar: " + e.getMessage(),
                    "Error de BD",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Maneja el evento al pulsar el botón "Borrar Texto" (Limpiar campos).
     */
    private void deleteTextButtonActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarCampos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        idInput = new javax.swing.JTextField();
        nombreInput = new javax.swing.JTextField();
        directorInput = new javax.swing.JTextField();
        generoInput = new javax.swing.JTextField();
        fechaInput = new javax.swing.JTextField();
        cantidadInput = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        deleteTextButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        idInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idInputActionPerformed(evt);
            }
        });

        nombreInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreInputActionPerformed(evt);
            }
        });

        directorInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                directorInputActionPerformed(evt);
            }
        });

        generoInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generoInputActionPerformed(evt);
            }
        });

        fechaInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaInputActionPerformed(evt);
            }
        });

        cantidadInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadInputActionPerformed(evt);
            }
        });

        addButton.setText("Añadir");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteTextButton.setText("Borrar Texto");

        editButton.setText("Editar");

        deleteButton.setText("Borrar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(directorInput, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(deleteTextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(idInput, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(nombreInput, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(47, 47, 47)
                            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(cantidadInput, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(generoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fechaInput, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(idInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombreInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addButton))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(directorInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteTextButton))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(generoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fechaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(editButton)
                                .addGap(17, 17, 17)
                                .addComponent(deleteButton)))
                        .addGap(18, 18, 18)
                        .addComponent(cantidadInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idInputActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_idInputActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_idInputActionPerformed

    private void nombreInputActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_nombreInputActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_nombreInputActionPerformed

    private void directorInputActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_directorInputActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_directorInputActionPerformed

    private void generoInputActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_generoInputActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_generoInputActionPerformed

    private void fechaInputActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_fechaInputActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_fechaInputActionPerformed

    private void cantidadInputActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cantidadInputActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_cantidadInputActionPerformed

    /**
     * Maneja el evento al pulsar el botón "Añadir" (CREATE).
     */
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Aquí NO va limpiarCampos();

        String nombre = nombreInput.getText().trim();
        String director = directorInput.getText().trim();
        String genero = generoInput.getText().trim();
        String fechaStr = fechaInput.getText().trim();
        String cantidadStr = cantidadInput.getText().trim();

        // 1. Validación de campos obligatorios (considerando los placeholders)
        if (nombre.isEmpty() || nombre.equals("Nombre") ||
                director.isEmpty() || director.equals("Director") || director.equals("director") || // Director tiene
                                                                                                    // minúsculas
                genero.isEmpty() || genero.equals("Género") ||
                fechaStr.isEmpty() || fechaStr.equals("Fecha Lanzamiento")
                || fechaStr.equals("Fecha Lanzamiento (YYYY-MM-DD)") ||
                cantidadStr.isEmpty() || cantidadStr.equals("0")) { // El "0" es el placeholder

            JOptionPane.showMessageDialog(this,
                    "Nombre, Director, Género, Fecha y Cantidad (mayor a 0) son campos obligatorios.",
                    "Error de Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 2. Convertir cantidad a entero
            int cantidad = Integer.parseInt(cantidadStr);

            // Re-validación de cantidad, solo por si acaso
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this,
                        "La cantidad de copias debe ser mayor que cero.",
                        "Error de Validación",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 3. Llamar al DAO para crear
            // (Asegúrate de que createPelicula() acepta los parámetros y lanza Exception)
            boolean exito = peliculaDAO.createPelicula(nombre, director, genero, fechaStr, cantidad);

            if (exito) {
                JOptionPane.showMessageDialog(this,
                        "Película '" + nombre + "' añadida con éxito.",
                        "Creación Exitosa",
                        JOptionPane.INFORMATION_MESSAGE);

                limpiarCampos();
                cargarDatosPeliculas(); // Refrescar la tabla
            } else {
                JOptionPane.showMessageDialog(this,
                        "La película no pudo ser añadida. (Verifica unicidad de título o error interno de BD).",
                        "Fallo en la Creación",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Error de formato: La Cantidad debe ser un número entero válido.",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error de Base de Datos al añadir: " + e.getMessage(),
                    "Error de BD",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Maneja el evento al pulsar el botón "Editar" (UPDATE).
     */
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // 1. Obtener ID y datos del formulario
        String idStr = idInput.getText().trim();
        String nombre = nombreInput.getText().trim();
        String director = directorInput.getText().trim();
        String genero = generoInput.getText().trim();
        String fechaStr = fechaInput.getText().trim();
        String cantidadStr = cantidadInput.getText().trim();

        // Validación 1: Debe haber un ID seleccionado
        if (idStr.isEmpty() || idStr.equals("Id")) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona una película de la tabla para editar (el ID está vacío).",
                    "Error de Edición",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validación 2: Campos de datos obligatorios
        if (nombre.isEmpty() || director.isEmpty() || genero.isEmpty() || fechaStr.isEmpty() || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nombre, Director, Género, Fecha y Cantidad son campos obligatorios para la edición.",
                    "Error de Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 3. Convertir ID y Cantidad a entero
            int id = Integer.parseInt(idStr);
            int cantidad = Integer.parseInt(cantidadStr);

            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this,
                        "La cantidad de copias debe ser mayor que cero.",
                        "Error de Validación",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 4. Llamar al DAO para actualizar
            // (Asegúrate de que updatePelicula() acepta los parámetros y lanza Exception)
            boolean exito = peliculaDAO.updatePelicula(id, nombre, director, genero, fechaStr, cantidad);

            if (exito) {
                JOptionPane.showMessageDialog(this,
                        "Película con ID " + id + " actualizada con éxito.",
                        "Edición Exitosa",
                        JOptionPane.INFORMATION_MESSAGE);

                limpiarCampos();
                cargarDatosPeliculas(); // Refrescar la tabla
            } else {
                JOptionPane.showMessageDialog(this,
                        "La película no pudo ser actualizada. Verifica si el ID es correcto o si hay duplicados.",
                        "Fallo en la Edición",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Error de formato: El ID o la Cantidad no son números enteros válidos.",
                    "Error de Formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error de Base de Datos al editar: " + e.getMessage(),
                    "Error de BD",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PeliculaCrudView.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PeliculaCrudView.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PeliculaCrudView.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PeliculaCrudView.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PeliculaCrudView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField cantidadInput;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton deleteTextButton;
    private javax.swing.JTextField directorInput;
    private javax.swing.JButton editButton;
    private javax.swing.JTextField fechaInput;
    private javax.swing.JTextField generoInput;
    private javax.swing.JTextField idInput;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nombreInput;
    // End of variables declaration//GEN-END:variables
}
