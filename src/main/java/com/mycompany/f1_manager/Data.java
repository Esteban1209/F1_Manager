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

/**
 * Clase Data - Contiene métodos estáticos para validar y obtener datos de entrada del usuario.
 * Proporciona validación robusta para diferentes tipos de datos (texto, enteros, decimales).
 */
public class Data {

    // ==================== MÉTODOS DE VALIDACIÓN ====================

    /**
     * Solicita y valida un texto no vacío al usuario
     * @param mensaje Mensaje a mostrar en el cuadro de diálogo
     * @return Texto validado (sin espacios al inicio/fin)
     */
    public static String pedirTexto(String mensaje) {
        String input;
        do {
            // Mostrar cuadro de diálogo y obtener entrada
            input = JOptionPane.showInputDialog(mensaje);
            
            // Repetir mientras la entrada sea nula (cancelada) o vacía
        } while (input == null || input.trim().isEmpty());
        
        return input.trim(); // Devuelve el texto sin espacios extras
    }

    /**
     * Solicita y valida un número entero dentro de un rango específico
     * @param mensaje Mensaje a mostrar
     * @param min Valor mínimo permitido (inclusive)
     * @param max Valor máximo permitido (inclusive)
     * @return Número validado, o -1 si se cancela la operación
     */
    public static int pedirEntero(String mensaje, int min, int max) {
        Integer num = null; // Inicializar como null para controlar la validación
        
        while (num == null || num < min || num > max) {
            // Mostrar cuadro de diálogo
            String input = JOptionPane.showInputDialog(mensaje);

            // Si el usuario cancela
            if (input == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return -1; // Valor especial para indicar cancelación
            }

            try {
                // Intentar convertir a número
                num = Integer.parseInt(input);
                
                // Validar rango
                if (num < min || num > max) {
                    JOptionPane.showMessageDialog(null, 
                        "El valor debe estar entre " + min + " y " + max + ". Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                // Manejar error si no es un número válido
                JOptionPane.showMessageDialog(null, 
                    "Entrada inválida. Debe ingresar un número entero.");
            }
        }
        return num; // Devolver número validado
    }

    /**
     * Solicita y valida un número decimal positivo
     * @param mensaje Mensaje a mostrar
     * @return Número decimal positivo validado
     */
    public static double pedirDecimal(String mensaje) {
        Double num = null;
        
        while (num == null || num <= 0) {
            String input = JOptionPane.showInputDialog(mensaje);
            try {
                num = Double.parseDouble(input);
                
                // Validar que sea positivo
                if (num <= 0) {
                    JOptionPane.showMessageDialog(null, "El número debe ser positivo.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número positivo");
            }
        }
        return num;
    }
}