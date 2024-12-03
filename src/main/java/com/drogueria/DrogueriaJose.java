package com.drogueria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrogueriaJose {
    public static void main(String[] args) {        
      // ventana principal
    	JFrame frame = new JFrame("Pedido de Medicamentos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 720);
        frame.setLayout(new BorderLayout());

     // Panel para la imagen
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());

     // Cargar la imagen desde los recursos utilizando la clase actual
        ImageIcon medicamentoImage = new ImageIcon(DrogueriaJose.class.getResource("/com/drogueria/imagenes/medicamentos.jpg"));


        // Crear una etiqueta para mostrar la imagen
        JLabel imageLabel = new JLabel(medicamentoImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER); // Centrar la imagen en el panel
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Ajustar el tamaño del panel de imagen
        imagePanel.setPreferredSize(new Dimension(200, frame.getHeight())); // Ancho fijo, altura dinámica

        // Agregar el panel de imagen a la parte izquierda del frame
        frame.add(imagePanel, BorderLayout.WEST);


        // formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10)); // 6 filas y 2 columnas con espaciado

        // componentes
        JTextField txtNombreMedicamento = new JTextField();
        JTextField txtCantidad = new JTextField();

        // JComboBox
        JComboBox<String> comboTipoMedicamento = new JComboBox<>(new String[] {
        "Seleccione tipo de medicamento", "Analgésico", "Analeptico", "Anestésico", "Antiácido", "Antidepresivo", "Antibiótico"}); //Medicamentos Disponibles
        JRadioButton rbtnCofarma = new JRadioButton("Cofarma"); 
        JRadioButton rbtnEmpsephar = new JRadioButton("Empsephar");
        JRadioButton rbtnCemefar = new JRadioButton("Cemefar");
        JCheckBox chkSucursalPrincipal = new JCheckBox("Farmacia Principal");
        JCheckBox chkSucursalSecundaria = new JCheckBox("Farmacia Secundaria");

        // Agrupación de botones para que solo uno sea seleccionado
        ButtonGroup groupDistribuidores = new ButtonGroup();
        groupDistribuidores.add(rbtnCofarma);
        groupDistribuidores.add(rbtnEmpsephar);
        groupDistribuidores.add(rbtnCemefar);

        // Añadir al formulario
        formPanel.add(new JLabel("Nombre del Medicamento:"));
        formPanel.add(txtNombreMedicamento);
        formPanel.add(new JLabel("Tipo de Medicamento:"));
        formPanel.add(comboTipoMedicamento);
        formPanel.add(new JLabel("Cantidad:"));
        formPanel.add(txtCantidad);
        formPanel.add(new JLabel("Distribuidor:"));

        JPanel panelDistribuidores = new JPanel();
        panelDistribuidores.add(rbtnCofarma);
        panelDistribuidores.add(rbtnEmpsephar);
        panelDistribuidores.add(rbtnCemefar);
        formPanel.add(panelDistribuidores);

        formPanel.add(new JLabel("Sucursal:"));
        JPanel panelSucursales = new JPanel();
        panelSucursales.add(chkSucursalPrincipal);
        panelSucursales.add(chkSucursalSecundaria);
        formPanel.add(panelSucursales);

        // Botones
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");
        
        // Estilo del botón Confirmar (color verde)
        btnConfirmar.setBackground(new Color(0, 128, 0));  // Fondo verde
        btnConfirmar.setForeground(Color.WHITE);  // Texto blanco
       

        // Estilo del botón Cancelar (color rojo)
        btnCancelar.setBackground(new Color(255, 0, 0));  // Fondo rojo
        btnCancelar.setForeground(Color.WHITE);  // Texto blanco
        

        // Añadir los botones al formulario
        formPanel.add(btnConfirmar);
        formPanel.add(btnCancelar);

        // Crear un panel para los componentes y agregarlo
        frame.add(formPanel, BorderLayout.CENTER);  

        // Acción de Confirmar
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

             // Validación del nombre del medicamento
                String nombreMedicamento = txtNombreMedicamento.getText();
                if (nombreMedicamento.isEmpty() || !nombreMedicamento.matches("[a-zA-Z0-9 ]+")) {
                    JOptionPane.showMessageDialog(frame, "Debe ingresar un nombre válido para el medicamento.");
                    txtNombreMedicamento.requestFocus(); // Enfocar el campo del nombre del medicamento
                    return;
                }

                // Validación del tipo de medicamento
                String tipoMedicamento = (String) comboTipoMedicamento.getSelectedItem();
                if (tipoMedicamento.equals("Seleccione tipo de medicamento")) {
                    JOptionPane.showMessageDialog(frame, "Debe seleccionar un tipo de medicamento.");
                    comboTipoMedicamento.requestFocus(); // Enfocar el combo box
                    return;
                }

                // Validación de la cantidad
                String cantidadStr = txtCantidad.getText();
                if (cantidadStr.isEmpty() || !cantidadStr.matches("\\d+")) {
                    JOptionPane.showMessageDialog(frame, "Debe ingresar una cantidad válida (solo números).");
                    txtCantidad.requestFocus(); // Enfocar el campo de cantidad
                    return;
                }
                int cantidad = Integer.parseInt(cantidadStr);
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(frame, "La cantidad debe ser mayor a 0.");
                    txtCantidad.requestFocus(); // Enfocar el campo de cantidad
                    return;
                }

                // Validación del distribuidor
                String distribuidor = rbtnCofarma.isSelected() ? "Cofarma" : 
                                      rbtnEmpsephar.isSelected() ? "Empsephar" : 
                                      rbtnCemefar.isSelected() ? "Cemefar" : "";
                if (distribuidor.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Debe seleccionar un distribuidor.");
                    return; // No hay un foco específico porque es un grupo de botones
                }

                // Validación de las sucursales
                String sucursal = "";
                if (chkSucursalPrincipal.isSelected()) sucursal += "Calle de la Rosa n. 28 ";
                if (chkSucursalSecundaria.isSelected()) sucursal += "Calle Alcazabilla n. 3";
                if (sucursal.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Debe seleccionar al menos una sucursal.");
                    return;
                }

                // Si todo es correcto, mostrar el mensaje de confirmación
                JOptionPane.showMessageDialog(frame, "Pedido confirmado:\n" +
                        "Medicamento: " + nombreMedicamento + "\n" +
                        "Tipo: " + tipoMedicamento + "\n" +
                        "Cantidad: " + cantidad + "\n" +
                        "Distribuidor: " + distribuidor + "\n" +
                        "Sucursal: " + sucursal);

                
                // Limpiar el formulario después de confirmar
                txtNombreMedicamento.setText("");
                txtCantidad.setText("");
                comboTipoMedicamento.setSelectedIndex(0); // Establecer "Seleccione tipo de medicamento"
                groupDistribuidores.clearSelection();
                chkSucursalPrincipal.setSelected(false);
                chkSucursalSecundaria.setSelected(false);
                
            }
        });

        // Acción de Cancelar (limpiar los campos)
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNombreMedicamento.setText("");
                txtCantidad.setText("");
                comboTipoMedicamento.setSelectedIndex(0);  // Establecer "Seleccione un tipo de medicamento"
                groupDistribuidores.clearSelection();
                chkSucursalPrincipal.setSelected(false);
                chkSucursalSecundaria.setSelected(false);
            }
        });

        // Hacer visible la ventana
        frame.setVisible(true);
    }
}
