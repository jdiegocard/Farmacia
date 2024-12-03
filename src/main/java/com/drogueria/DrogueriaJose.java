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

       
        //  imagen, pero no funciona 
        JPanel imagePanel = new JPanel();
        ImageIcon medicamentoImage = new ImageIcon("src/main/java/com/drogueria/imagenes/medicamentos.jpg");
        JLabel imageLabel = new JLabel(medicamentoImage);
        imagePanel.add(imageLabel);
        frame.add(imagePanel, BorderLayout.NORTH);  // Agregar la imagen en la parte superior

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
                String nombreMedicamento = txtNombreMedicamento.getText();

                // Obtener el valor seleccionado
                String tipoMedicamento = (String) comboTipoMedicamento.getSelectedItem();
                
                // Verificar si no se ha seleccionado un tipo de medicamento
                if (tipoMedicamento.equals("Seleccione tipo de medicamento")) {
                    JOptionPane.showMessageDialog(frame, "Debe seleccionar un tipo de medicamento.");
                    return;
                }

                String cantidadStr = txtCantidad.getText();
                String distribuidor = rbtnCofarma.isSelected() ? "Cofarma" : rbtnEmpsephar.isSelected() ? "Empsephar" : rbtnCemefar.isSelected() ? "Cemefar" : "";
                String sucursal = "";
                if (chkSucursalPrincipal.isSelected()) sucursal += "Calle de la Rosa n. 28 ";
                if (chkSucursalSecundaria.isSelected()) sucursal += "Calle Alcazabilla n. 3";

                // Validación de los datos
                if (nombreMedicamento.isEmpty() || !nombreMedicamento.matches("[a-zA-Z0-9 ]+")) {
                    JOptionPane.showMessageDialog(frame, "Nombre del medicamento incorrecto.");
                    return;
                }
                if (cantidadStr.isEmpty() || !cantidadStr.matches("\\d+")) {
                    JOptionPane.showMessageDialog(frame, "Cantidad incorrecta");
                    return;
                }
                int cantidad = Integer.parseInt(cantidadStr);
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(frame, "Cantidad debe ser un mayor a 0");
                    return;
                }

                // Ahora se valida si se seleccionó un distribuidor
                if (distribuidor.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Debe seleccionar un distribuidor.");
                    return;
                }
                if (sucursal.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Debe seleccionar una sucursal.");
                    return;
                }

                // Si todo es correcto, mostrar el mensaje de confirmación
                JOptionPane.showMessageDialog(frame, "Pedido confirmado a " + distribuidor +" " + sucursal);
                // Limpiar el formulario después de confirmar
                txtNombreMedicamento.setText("");
                txtCantidad.setText("");
                comboTipoMedicamento.setSelectedIndex(0);  // Establecer "Seleccione tipo de medicamento"
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
