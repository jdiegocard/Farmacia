package com.drogueria;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonAction implements ActionListener {
    private JFrame frame;

    public BotonAction(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source.getText().equals("Confirmar")) {
            JOptionPane.showMessageDialog(frame, "Pedido Confirmado");
        } else if (source.getText().equals("Cancelar")) {
            // Limpiar 
            frame.setTitle("Formulario Restablecido");
        }
    }
}

