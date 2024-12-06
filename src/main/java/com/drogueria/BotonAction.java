package com.drogueria;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonAction implements ActionListener {
    private JFrame frame;
    private String action;
    private JTextField txtNombreMedicamento, txtCantidad;
    private JComboBox<String> comboTipoMedicamento;
    private ButtonGroup groupDistribuidores;
    private JCheckBox chkSucursalPrincipal, chkSucursalSecundaria;

    public BotonAction(JFrame frame, String action, 
    		JTextField txtNombreMedicamento, 
    		JTextField txtCantidad,
                       JComboBox<String> comboTipoMedicamento, 
                       ButtonGroup groupDistribuidores,
                       JCheckBox chkSucursalPrincipal, 
                       JCheckBox chkSucursalSecundaria) {
        this.frame = frame;
        this.action = action;
        this.txtNombreMedicamento = txtNombreMedicamento;
        this.txtCantidad = txtCantidad;
        this.comboTipoMedicamento = comboTipoMedicamento;
        this.groupDistribuidores = groupDistribuidores;
        this.chkSucursalPrincipal = chkSucursalPrincipal;
        this.chkSucursalSecundaria = chkSucursalSecundaria;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (action.equals("Confirmar")) {
            confirmarPedido();
        } else if (action.equals("Cancelar")) {
            cancelarPedido();
        }
    }   
    
    
    private void confirmarPedido() {
        // Validaciones
        String nombreMedicamento = txtNombreMedicamento.getText();
        if (nombreMedicamento.isEmpty() || !nombreMedicamento.matches("[a-zA-Z0-9 ]+")) {
            JOptionPane.showMessageDialog(frame, "Debe ingresar un nombre válido para el medicamento.");
            txtNombreMedicamento.requestFocus();
            return;
        }

        String tipoMedicamento = (String) comboTipoMedicamento.getSelectedItem();
        if (tipoMedicamento.equals("Seleccione tipo de medicamento")) {
            JOptionPane.showMessageDialog(frame, "Debe seleccionar un tipo de medicamento.");
            return;
        }

        String cantidadStr = txtCantidad.getText();
        if (cantidadStr.isEmpty() || !cantidadStr.matches("\\d+") || Integer.parseInt(cantidadStr) <= 0) {
            JOptionPane.showMessageDialog(frame, "Debe ingresar una cantidad válida (mayor a 0).");
            txtCantidad.requestFocus();
            return;
        }

        String distribuidor = "";
        if (groupDistribuidores.getSelection() != null) {
            for (AbstractButton button : java.util.Collections.list(groupDistribuidores.getElements())) {
                if (button.isSelected()) {
                    distribuidor = button.getText();
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Debe seleccionar un distribuidor.");
            return;
        }

        String sucursal = "";
        if (chkSucursalPrincipal.isSelected()) sucursal += "Principal Calle de la Rosa n. 28 ";
        if (chkSucursalSecundaria.isSelected()) sucursal += "Secundaria Calle Alcazabilla n. 3";
        		/* (chkSucursalPrincipal.isSelected() ? "Farmacia Principal " : "") +
                (chkSucursalSecundaria.isSelected() ? "Farmacia Secundaria" : "");*/
        if (sucursal.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Debe seleccionar al menos una sucursal.");
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                frame,
                "¿Está seguro de confirmar este pedido?\n" +
                "Medicamento: " + nombreMedicamento + "\n" +
                "Tipo: " + tipoMedicamento + "\n" +
                "Cantidad: " + cantidadStr + "\n" +
                "Distribuidor: " + distribuidor + "\n" +
                "Sucursal: " + sucursal,
                "Confirmar Pedido",
                JOptionPane.YES_NO_OPTION
        );
        
        if (opcion == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(frame, "Pedido confirmado: \n" +
            		"Medicamento: " + nombreMedicamento + "\n" +
            		"Tipo: " + tipoMedicamento + "\n" +
            		"Cantidad: " + cantidadStr + "\n" +
            		"Distribuidor: " + distribuidor + "\n" +
            		"Sucursal: " + sucursal);
            
            		limpiarFormulario();
        };
        
      }

    private void cancelarPedido() {
        int opcion = JOptionPane.showConfirmDialog(
                frame,
                "¿Estás seguro de que deseas cancelar y limpiar el formulario?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            limpiarFormulario();
             JOptionPane.showMessageDialog(frame, "Formulario cancelado");
        }
    }

    private void limpiarFormulario() {
        txtNombreMedicamento.setText("");
        txtCantidad.setText("");
        comboTipoMedicamento.setSelectedIndex(0);
        groupDistribuidores.clearSelection();
        chkSucursalPrincipal.setSelected(false);
        chkSucursalSecundaria.setSelected(false);
    }
}
