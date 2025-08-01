/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.f1_manager;

/**
 *
 * @author hp
 */
import javax.swing.JOptionPane;

public class Data {
    public static String pedirTexto(String mensaje) {
        String input;
        do {
            input = JOptionPane.showInputDialog(mensaje);
        } while (input == null || input.trim().isEmpty());
        return input.trim();
    }

    public static int pedirEntero(String mensaje, int min, int max) {
        Integer num = null;
        while (num == null || num < min || num > max) {
            String input = JOptionPane.showInputDialog(mensaje);

            if (input == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return -1; 
            }

            try {
                num = Integer.parseInt(input);
                if (num < min || num > max) {
                    JOptionPane.showMessageDialog(null, 
                        "El valor debe estar entre " + min + " y " + max + ". Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, 
                    "Entrada inválida. Debe ingresar un número entero.");
            }
        }
        return num;
    }

    public static double pedirDecimal(String mensaje) {
        Double num = null;
        while (num == null || num <= 0) {
            String input = JOptionPane.showInputDialog(mensaje);
            try {
                num = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número positivo");
            }
        }
        return num;
    }
}