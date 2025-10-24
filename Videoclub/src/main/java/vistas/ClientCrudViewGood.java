/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import com.mycompany.videoclub.DAO.IClienteImpl;
import com.mycompany.videoclub.Modelos.Cliente;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Isaac
 * @author Santiago
 * @author Richard
 * @author Enrique
 */
public class ClientCrudViewGood extends javax.swing.JFrame {
  private final IClienteImpl clienteDAO = new IClienteImpl();

  public ClientCrudViewGood() {
    initComponents();
    cargarDatosClientes();

    // Hace que el campo id no se pueda modificar y que esté vacío
    idInput.setEditable(false);
    idInput.setText("");

    // Listeners de botones
    
    //! Versión tradicional 
    // addButton.addActionListener(new java.awt.event.ActionListener() {
    //   public void actionPerformed(java.awt.event.ActionEvent evt) {
    //     addButtonActionPerformed(evt);
    //   }
    // });
    
    //! Versión moderna 
    addButton.addActionListener((java.awt.event.ActionEvent evt) -> { addButtonActionPerformed(evt); });
    editButton.addActionListener((java.awt.event.ActionEvent evt) -> { editButtonActionPerformed(evt); });
    deleteButton.addActionListener((java.awt.event.ActionEvent evt) -> { deleteButtonActionPerformed(evt); });
    deleteTextButton.addActionListener((java.awt.event.ActionEvent evt) -> { deleteTextButtonActionPerformed(evt); });


    // Listener para hacer clic en la tabla
    jTable1.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent evt) {
        jTable1MouseClicked(evt);
      }
    });

    this.setTitle("Gestión de Clientes (CRUD)");
    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
  }

  /**
   * Método para rellenar la tabla con los datos de la base de datos
   */
  private void cargarDatosClientes() {
    try {
      DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
      model.setRowCount(0);

      // Campos del header en la tabla
      String[] columnas = { "ID", "Nombre", "Apellidos", "Teléfono", "DNI", "Categoría" };
      model.setColumnIdentifiers(columnas);

      // Llamada al método
      Cliente[] clientes = clienteDAO.getAllClient();

      if (clientes != null) {
        for (Cliente c : clientes) {
          model.addRow(new Object[] {
              c.getId(),
              c.getNombre(),
              c.getApellidos(),
              c.getDni(),
              c.getTelefono(),
              c.getCategoria() != null ? c.getCategoria().name() : "N/A"
          });
        }
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this,
          "Error al cargar los datos: " + e.getMessage(),
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
    nameInput.setText("");
    apellidosInput.setText("");
    dniInput.setText("");
    telefonoInput.setText("");
  }

  private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
    int fila = jTable1.getSelectedRow();
    if (fila == -1) {
      return;
    }

    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    // Obtener los valores por índice de columna (ID=0, Nombre=1, Apellidos=2,
    // DNI=3, Teléfono=4)
    String id = model.getValueAt(fila, 0).toString();
    String nombre = model.getValueAt(fila, 1).toString();
    String apellidos = model.getValueAt(fila, 2).toString();
    String dni = model.getValueAt(fila, 3).toString();
    String telefono = model.getValueAt(fila, 4) != null ? model.getValueAt(fila, 4).toString() : "";

    // Asignar a los campos
    idInput.setText(id);
    nameInput.setText(nombre);
    apellidosInput.setText(apellidos);
    dniInput.setText(dni);
    telefonoInput.setText(telefono);
  }

  /**
   * Ejecuta el método añadir tras apretar el botón "Añadir"
   */
  private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {

    // Obtener todos los datos
    String nombre = nameInput.getText().trim();
    String apellidos = apellidosInput.getText().trim();
    String dni = dniInput.getText().trim();
    String telefono = telefonoInput.getText().trim();

    // Comprobación de datos
    if (nombre.isEmpty() || apellidos.isEmpty() || dni.isEmpty() || telefono.isEmpty()) {
      JOptionPane.showMessageDialog(this,
          "Nombre, Apellidos, DNI, y Teléfono son campos obligatorios.",
          "Error de Validación",
          JOptionPane.WARNING_MESSAGE);
      return;
    }

    try {
      // Ejecución del método crear
      boolean exito = clienteDAO.createClient(nombre, apellidos, dni, telefono);

      if (exito) {
        JOptionPane.showMessageDialog(this,
            "Cliente " + nombre + " añadido con éxito.",
            "Creación Exitosa",
            JOptionPane.INFORMATION_MESSAGE);

        limpiarCampos();
        cargarDatosClientes();
      } else {
        JOptionPane.showMessageDialog(this,
            "Cliente " + nombre + " no se ha podido.",
            "Creación Fallida",
            JOptionPane.INFORMATION_MESSAGE);
      }

    } catch (Exception e) {
      JOptionPane.showMessageDialog(this,
          "Cliente " + nombre + " no se ha podido. Ha habido una excepción",
          "Creación Fallida con excepción",
          JOptionPane.INFORMATION_MESSAGE);
    }
  }

  /**
   * Ejecuta el método edit al apretar el botón "Editar"
   */
  private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {

    // Obtener ID y datos del formulario
    String idStr = idInput.getText().trim();
    String nombre = nameInput.getText().trim();
    String apellidos = apellidosInput.getText().trim();
    String dni = dniInput.getText().trim();
    String telefono = telefonoInput.getText().trim();

    // Comprobación de que el id se ha capturado
    if (idStr.isEmpty()) {
      JOptionPane.showMessageDialog(this,
          "Selecciona un cliente de la tabla para editar.",
          "Error de Edición",
          JOptionPane.WARNING_MESSAGE);
      return;
    }

    // Comprobación de los demás campos
    if (nombre.isEmpty() || apellidos.isEmpty() || dni.isEmpty() || telefono.isEmpty()) {
      JOptionPane.showMessageDialog(this,
          "Nombre, Apellidos, DNI y Teléfono son campos obligatorios.",
          "Error de Validación",
          JOptionPane.WARNING_MESSAGE);
      return;
    }

    try {
      int id = Integer.parseInt(idStr);

      boolean exito = clienteDAO.updateClient(id, nombre, apellidos, dni, telefono);

      if (exito) {
        JOptionPane.showMessageDialog(this,
            "Cliente con ID " + id + " actualizado con éxito.",
            "Edición Exitosa",
            JOptionPane.INFORMATION_MESSAGE);

        limpiarCampos();
        cargarDatosClientes();
      } else {
        JOptionPane.showMessageDialog(this,
            "El cliente no pudo ser actualizado. Verifica si el DNI ya existe o si el ID es correcto.",
            "Fallo en la Edición",
            JOptionPane.ERROR_MESSAGE);
      }

    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this,
          "Error de formato: El ID no es un número entero válido.",
          "Error",
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
   * Borra el usuario seleccionado al apretar el botón de "Eliminar usuario"
   */
  private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
    String idStr = idInput.getText().trim();

    if (idStr.isEmpty()) {
      JOptionPane.showMessageDialog(this,
          "Selecciona un cliente de la tabla para eliminar.",
          "Error de Eliminación",
          JOptionPane.WARNING_MESSAGE);
      return;
    }

    try {
      int id = Integer.parseInt(idStr);

      // Pedir coonfirmación al usuario antes de ejecutar el borrado
      int confirmacion = JOptionPane.showConfirmDialog(this,
          "¿Estás seguro de que quieres eliminar al cliente con ID " + id + "?",
          "Confirmar Eliminación",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE);

      // Confirmación
      if (confirmacion == JOptionPane.YES_OPTION) {

        // Llamamos al método borrar
        boolean exito = clienteDAO.deleteClient(id);

        if (exito) {
          JOptionPane.showMessageDialog(this,
              "Cliente con ID " + id + " eliminado con éxito.",
              "Eliminación Exitosa",
              JOptionPane.INFORMATION_MESSAGE);

          limpiarCampos();
          // Se llama para añadir
          cargarDatosClientes();
        } else {
          JOptionPane.showMessageDialog(this,
              "El cliente no pudo ser eliminado. (Verifica si está siendo usado en otra tabla o error interno de BD).",
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
   * Limpia los inputs fields al apretar el botón de "Borrar texto"
   */
  private void deleteTextButtonActionPerformed(java.awt.event.ActionEvent evt) {
    limpiarCampos();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */

  /**
   * El código aquí dentro se modifica para cosas muy pequeñas aunque se pueden hacer directamente en el propio designs del jFrame.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated // Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTable1 = new javax.swing.JTable();
    nameInput = new javax.swing.JTextField();
    apellidosInput = new javax.swing.JTextField();
    dniInput = new javax.swing.JTextField();
    telefonoInput = new javax.swing.JTextField();
    addButton = new javax.swing.JButton();
    deleteTextButton = new javax.swing.JButton();
    editButton = new javax.swing.JButton();
    idInput = new javax.swing.JTextField();
    deleteButton = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null }
        },
        new String[] {
            "Title 1", "Title 2", "Title 3", "Title 4"
        }));
    jScrollPane1.setViewportView(jTable1);
    // dniInput.addActionListener(new java.awt.event.ActionListener() {
    // public void actionPerformed(java.awt.event.ActionEvent evt) {
    // dniInputActionPerformed(evt);
    // }
    // });

    // Texto en los botones
    addButton.setText("Añadir");
    deleteTextButton.setText("Borrar texto");
    editButton.setText("Editar");
    deleteButton.setText("Borrar usuario");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE,
                                191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(apellidosInput,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 191,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefonoInput,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 191,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dniInput, javax.swing.GroupLayout.PREFERRED_SIZE,
                                191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                false)
                            .addComponent(deleteButton,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteTextButton,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(idInput, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(280, 280, 280)));
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(idInput, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteTextButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(apellidosInput, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(telefonoInput, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dniInput, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 843,
                javax.swing.GroupLayout.PREFERRED_SIZE));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

    pack();
  }// </editor-fold>//GEN-END:initComponents

  /**
   * El main no lo he tocado para nada
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
    // (optional) ">
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(ClientCrudViewGood.class.getName()).log(java.util.logging.Level.SEVERE,
          null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(ClientCrudViewGood.class.getName()).log(java.util.logging.Level.SEVERE,
          null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(ClientCrudViewGood.class.getName()).log(java.util.logging.Level.SEVERE,
          null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(ClientCrudViewGood.class.getName()).log(java.util.logging.Level.SEVERE,
          null, ex);
    }
    // </editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new ClientCrudViewGood().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  // Variables automáticas al colocarlas en el jFrame en la vista de diseño
  private javax.swing.JButton addButton;
  private javax.swing.JTextField apellidosInput;
  private javax.swing.JButton deleteButton;
  private javax.swing.JButton deleteTextButton;
  private javax.swing.JTextField dniInput;
  private javax.swing.JButton editButton;
  private javax.swing.JTextField idInput;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jTable1;
  private javax.swing.JTextField nameInput;
  private javax.swing.JTextField telefonoInput;
  // End of variables declaration//GEN-END:variables
}
