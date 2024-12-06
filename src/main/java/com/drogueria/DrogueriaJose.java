package com.drogueria;

import javax.swing.*;
import java.awt.*;

public class DrogueriaJose {
    public static void main(String[] args) {   
    	
      // ventana principal
    	JFrame frame = new JFrame("Pedido de Medicamentos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 720);
        frame.setLayout(new BorderLayout());
        
       
        /*// este codigo no me sirve sin embargo no lo quero perder
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
        frame.add(imagePanel, BorderLayout.WEST);*/


        // formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10)); // 6 filas y 2 columnas

        // componentes
        JTextField txtNombreMedicamento = new JTextField();
        JTextField txtCantidad = new JTextField();

        // JComboBox
        JComboBox<String> comboTipoMedicamento = new JComboBox<>(new String[] {
        "Seleccione tipo de medicamento", "Analgésico", "Analeptico", "Anestésico", "Antiácido", "Antidepresivo", "Antibiótico"}); 
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
        btnConfirmar.setBackground(new Color(0, 128, 0));  
        btnConfirmar.setForeground(Color.WHITE); 
       

        // Estilo del botón Cancelar (color rojo)
        btnCancelar.setBackground(new Color(255, 0, 0));  
        btnCancelar.setForeground(Color.WHITE);  
        

        // botones del formulario
        formPanel.add(btnConfirmar);
        formPanel.add(btnCancelar);
        
        // agregar formulario
        frame.add(formPanel, BorderLayout.CENTER);
        
        // Manejo de eventos
        btnConfirmar.addActionListener(new BotonAction(frame, "Confirmar", 
        		txtNombreMedicamento, 
        		txtCantidad, 
        		comboTipoMedicamento, 
        		groupDistribuidores, 
        		chkSucursalPrincipal, 
        		chkSucursalSecundaria));
        btnCancelar.addActionListener(new BotonAction(frame, "Cancelar", 
        		txtNombreMedicamento, 
        		txtCantidad, 
        		comboTipoMedicamento, 
        		groupDistribuidores, 
        		chkSucursalPrincipal, 
        		chkSucursalSecundaria));
       
        // mostrar ventana
        frame.setVisible(true);
    }
}
