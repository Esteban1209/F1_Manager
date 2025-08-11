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
 * Clase Equipos - Gestiona los equipos y corredores de Fórmula 1
 * Permite registrar, asignar y mostrar información de equipos y sus pilotos
 */
// Define la clase `Equipos`.
public class Equipos {
    
    // Declara un arreglo de objetos `Equipo` llamado `equipos`.
    // Es `final`, lo que significa que el arreglo en sí no puede ser reasignado, aunque su contenido sí puede cambiar.
    // Su tamaño está fijo en 10.
    private final Equipo[] equipos = new Equipo[10];
    
    // Declara una variable entera `totalEquipos` e la inicializa en 0.
    // Esta variable se usa para llevar la cuenta de cuántos equipos se han registrado.
    private int totalEquipos = 0;

    // ---
    
    // ## Método auxiliar mejorado para pedir enteros con validación
    // Define un método privado que pide un número entero al usuario.
    // Este método es "auxiliar" porque solo se usa dentro de la clase `Equipos`.
    // Recibe un `mensaje`, un valor `min` y un valor `max` para la validación.
    // Devuelve un objeto `Integer` para poder devolver `null` si el usuario cancela.
    private Integer pedirEnteroConCancelar(String mensaje, int min, int max) {
        
        // Inicia un bucle infinito que se ejecuta hasta que se devuelve un valor válido o se cancela.
        while (true) {
            
            // Muestra un cuadro de diálogo al usuario con el `mensaje` y espera su entrada.
            // La entrada se guarda como un `String` en la variable `input`.
            String input = JOptionPane.showInputDialog(mensaje);
            
            // Verifica si el usuario presionó el botón "Cancelar" o cerró la ventana.
            // Si es así, `input` será `null`.
            if (input == null) {
                
                // Devuelve `null` para indicar que la operación fue cancelada.
                return null;
            }
            
            // Usa un bloque `try-catch` para manejar posibles errores si el usuario no ingresa un número.
            try {
                
                // Convierte la entrada de `String` a `int`, eliminando espacios en blanco al principio y al final (`trim()`).
                int valor = Integer.parseInt(input.trim());
                
                // Comprueba si el `valor` está dentro del rango permitido (`min` y `max`).
                if (valor >= min && valor <= max) {
                    
                    // Si el valor es válido, lo devuelve.
                    return valor;
                } else {
                    
                    // Si el valor está fuera del rango, muestra un mensaje de error.
                    JOptionPane.showMessageDialog(null, 
                        "Error: El valor debe estar entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                
                // Si la conversión a entero falla (por ejemplo, si el usuario escribió "abc"),
                // se captura la excepción `NumberFormatException` y se muestra un mensaje de error.
                JOptionPane.showMessageDialog(null, 
                    "Error: Debe ingresar un número entero válido.");
            }
        }
    }

    // ---

    // ## Registrar un nuevo equipo
    // Define un método público para registrar un nuevo equipo.
    public void registrarEquipo() {
        
        // Comprueba si ya se ha alcanzado el límite de 10 equipos.
        if (totalEquipos >= 10) {
            
            // Si el límite está lleno, muestra un mensaje de error y sale del método.
            JOptionPane.showMessageDialog(null, "No se pueden registrar más equipos. Límite alcanzado (10).");
            return;
        }

        // Declara las variables para el nombre y para saber si el nombre está repetido.
        String nombre;
        boolean nombreRepetido;
        
        // Inicia un bucle `do-while` para pedir el nombre del equipo.
        // Se repetirá mientras el nombre esté repetido o esté vacío.
        do {
            
            // Pide al usuario el nombre del equipo.
            nombre = JOptionPane.showInputDialog("Nombre del equipo:");
            
            // Si el usuario cancela, muestra un mensaje y sale del método.
            if (nombre == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.");
                return;
                
            // Si el nombre está vacío o solo tiene espacios, muestra un error.
            } else if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: El nombre no puede estar vacío.");
                // Establece `nombreRepetido` en `true` para que el bucle continúe.
                nombreRepetido = true;
                continue;
            }

            // Llama al método auxiliar para verificar si el nombre del equipo ya existe.
            nombreRepetido = existeNombreEquipo(nombre);
            
            // Si el nombre ya existe, muestra un mensaje de error.
            if (nombreRepetido) {
                JOptionPane.showMessageDialog(null, "Error: El nombre '" + nombre + "' ya está en uso.");
            }
        } while (nombreRepetido || nombre.trim().isEmpty());

        // Usa el método auxiliar `pedirEnteroConCancelar` para solicitar el performance del equipo.
        // Pide un valor entre 0 y 10.
        Integer performance = pedirEnteroConCancelar("Performance del equipo (0-10):", 0, 10);
        
        // Si el usuario canceló, muestra un mensaje y sale del método.
        if (performance == null) {
            JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.");
            return;
        }

        // Sección para validar el presupuesto.
        double presupuesto = 0;
        boolean presupuestoValido = false;
        
        // Inicia un bucle `while` para pedir el presupuesto hasta que sea válido.
        while (!presupuestoValido) {
            
            // Pide al usuario el presupuesto como un `String`.
            String presupuestoStr = JOptionPane.showInputDialog("Presupuesto del equipo:");
            
            // Si el usuario cancela, muestra un mensaje y sale del método.
            if (presupuestoStr == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.");
                return;
            }
            
            // Usa un bloque `try-catch` para manejar posibles errores de formato de número.
            try {
                
                // Convierte el `String` del presupuesto a un `double`.
                presupuesto = Double.parseDouble(presupuestoStr);
                
                // Comprueba que el presupuesto no sea negativo.
                if (presupuesto >= 0) {
                    // Si es válido, establece la bandera en `true` para salir del bucle.
                    presupuestoValido = true;
                } else {
                    // Si es negativo, muestra un error.
                    JOptionPane.showMessageDialog(null, "Error: El presupuesto no puede ser negativo.");
                }
            } catch (NumberFormatException e) {
                
                // Si la conversión falla, muestra un mensaje de error.
                JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido para el presupuesto.");
            }
        }

        // Crea un nuevo objeto `Equipo` con los datos recopilados.
        // Lo agrega al arreglo `equipos` en la posición `totalEquipos`.
        // `totalEquipos++` agrega el equipo y luego incrementa el contador.
        equipos[totalEquipos++] = new Equipo(nombre, performance, presupuesto);
        
        // Muestra un mensaje de éxito, incluyendo el ID del nuevo equipo.
        JOptionPane.showMessageDialog(null, "¡Equipo registrado exitosamente!\nID: " + equipos[totalEquipos-1].getId());
    }
    
    // ---

    // ## Registrar un nuevo corredor
    // Define un método público para registrar un corredor.
    public void registrarCorredor() {
        
        // Verifica si hay al menos un equipo registrado.
        if (totalEquipos == 0) {
            
            // Si no hay equipos, muestra un error y sale del método.
            JOptionPane.showMessageDialog(null, "Error: Primero debe registrar al menos un equipo.");
            return;
        }

        // Llama al método auxiliar para que el usuario seleccione un equipo para el corredor.
        // Muestra un cuadro de diálogo con una lista de los equipos disponibles.
        Equipo equipo = seleccionarEquipo("Seleccione un equipo para el corredor:", "Registrar Corredor");
        
        // Si el usuario cancela la selección del equipo, muestra un mensaje y sale del método.
        if (equipo == null) {
            JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.");
            return;
        }

        // Verifica si el equipo seleccionado ya tiene el número máximo de corredores (generalmente 2).
        if (equipo.tieneMaxCorredores()) {
            
            // Si ya está lleno, muestra un error y sale del método.
            JOptionPane.showMessageDialog(null, "Error: Este equipo ya tiene 2 corredores.");
            return;
        }

        // Declara las variables para el nombre del corredor y para saber si está repetido.
        String nombre;
        boolean nombreRepetido;
        
        // Inicia un bucle `do-while` para pedir el nombre del corredor.
        // Se repetirá mientras el nombre esté repetido o vacío.
        do {
            
            // Pide al usuario el nombre del corredor.
            nombre = JOptionPane.showInputDialog("Nombre completo del corredor:");
            
            // Si el usuario cancela, muestra un mensaje y sale del método.
            if (nombre == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.");
                return;
                
            // Si el nombre está vacío, muestra un error y continúa el bucle.
            } else if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: El nombre no puede estar vacío.");
                nombreRepetido = true;
                continue;
            }

            // Llama a un método auxiliar para verificar si el nombre del corredor ya existe en cualquier equipo.
            nombreRepetido = existeNombreCorredorEnCualquierEquipo(nombre);
            
            // Si el nombre ya existe, muestra un mensaje de error.
            if (nombreRepetido) {
                JOptionPane.showMessageDialog(null, "Error: El corredor '" + nombre + "' ya existe en otro equipo.");
            }
        } while (nombreRepetido || nombre.trim().isEmpty());

        // Sección para la validación del número del corredor.
        Integer numero = null;
        boolean numeroValido = false;
        
        // Bucle `while` para pedir un número hasta que sea válido y no esté en uso.
        while (!numeroValido) {
            
            // Usa el método auxiliar `pedirEnteroConCancelar` para pedir el número.
            // El rango permitido es de 1 a 999.
            numero = pedirEnteroConCancelar("Número del corredor (1-999):", 1, 999);
            
            // Si el usuario cancela, muestra un mensaje y sale del método.
            if (numero == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.");
                return;
            }
            
            // Llama a un método auxiliar para verificar si el número ya existe.
            if (numeroExiste(numero)) {
                
                // Si el número ya está en uso, muestra un error.
                JOptionPane.showMessageDialog(null, "Error: El número " + numero + " ya está en uso.");
            } else {
                
                // Si el número es único, establece la bandera en `true` para salir del bucle.
                numeroValido = true;
            }
        }

        // Sección para la validación del nivel de habilidad.
        Integer habilidad = null;
        boolean habilidadValida = false;
        
        // Bucle `while` para pedir la habilidad hasta que sea válida.
        while (!habilidadValida) {
            
            // Pide el nivel de habilidad (de 0 a 10) usando el método auxiliar.
            habilidad = pedirEnteroConCancelar("Nivel de habilidad (0-10):", 0, 10);
            
            // Si el usuario cancela, muestra un mensaje y sale del método.
            if (habilidad == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.");
                return;
            }
            
            // Comprueba si la habilidad está en el rango correcto.
            if (habilidad >= 0 && habilidad <= 10) {
                
                // Si es válida, establece la bandera en `true`.
                habilidadValida = true;
            } else {
                
                // Si no, muestra un error.
                JOptionPane.showMessageDialog(null, "Error: La habilidad debe estar entre 0 y 10.");
            }
        }

        // Sección para la validación del nivel de experiencia.
        Integer experiencia = null;
        boolean experienciaValida = false;
        
        // Bucle `while` para pedir la experiencia hasta que sea válida.
        while (!experienciaValida) {
            
            // Pide el nivel de experiencia (de 0 a 10) usando el método auxiliar.
            experiencia = pedirEnteroConCancelar("Nivel de experiencia (0-10):", 0, 10);
            
            // Si el usuario cancela, muestra un mensaje y sale del método.
            if (experiencia == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.");
                return;
            }
            
            // Comprueba si la experiencia está en el rango correcto.
            if (experiencia >= 0 && experiencia <= 10) {
                
                // Si es válida, establece la bandera en `true`.
                experienciaValida = true;
            } else {
                
                // Si no, muestra un error.
                JOptionPane.showMessageDialog(null, "Error: La experiencia debe estar entre 0 y 10.");
            }
        }

        // Crea un nuevo objeto `Corredor` con los datos recopilados.
        // Le asigna el ID del equipo seleccionado.
        Corredor nuevo = new Corredor(nombre, numero, habilidad, experiencia, equipo.getId());
        
        // Llama al método `agregarCorredor` del objeto `Equipo` para añadir el nuevo corredor.
        equipo.agregarCorredor(nuevo);
        
        // Muestra un mensaje de éxito, incluyendo el ID del nuevo corredor.
        JOptionPane.showMessageDialog(null, "¡Corredor registrado exitosamente!\nID: " + nuevo.getId());
    }


    // Define un método auxiliar privado para verificar si el nombre de un corredor ya existe en algún equipo.
    private boolean existeNombreCorredorEnCualquierEquipo(String nombre) {
        
        // Si el nombre es `null`, no puede existir, así que devuelve `false`.
        if (nombre == null) return false;
        
        // Recorre todos los equipos registrados.
        for (int i = 0; i < totalEquipos; i++) {
            
            // Verifica que el equipo no sea `null` y que contenga un corredor con ese nombre.
            // Se asume que el método `contieneCorredorConNombre` existe en la clase `Equipo`.
            if (equipos[i] != null && equipos[i].contieneCorredorConNombre(nombre)) {
                
                // Si encuentra un corredor con ese nombre, devuelve `true` y termina.
                return true;
            }
        }
        
        // Si el bucle termina sin encontrar el nombre, devuelve `false`.
        return false;
    }

    // ---

    // ## Asignar corredor principal
    // Define un método público para asignar un corredor principal a cada equipo.
    public void asignarPrincipal() {
        
        // Itera sobre todos los equipos en el arreglo.
        for (Equipo e : equipos) {
            
            // Si el espacio del arreglo está vacío, salta a la siguiente iteración.
            if (e == null) continue;
            
            // Verifica si el equipo tiene corredores.
            if (!e.tieneCorredores()) {
                
                // Si no tiene, muestra un mensaje y salta al siguiente equipo.
                JOptionPane.showMessageDialog(null, e.getNombre() + " no tiene corredores");
                continue;
            }
            
            // Llama a un método auxiliar para que el usuario seleccione un corredor de ese equipo.
            Corredor seleccionado = seleccionarCorredor(e, "Seleccione corredor principal para " + e.getNombre() + ":");
            
            // Si el usuario cancela la selección, muestra un mensaje y salta al siguiente equipo.
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada para el equipo " + e.getNombre());
                continue;
            }
            
            // Llama al método `setPrincipal` del objeto `Equipo` para establecer el corredor seleccionado como principal.
            e.setPrincipal(seleccionado);
        }
    }

    // ---

    // ## Mostrar equipos y corredores
    // Define un método público para mostrar la información de todos los equipos y sus corredores.
    public void mostrarEquiposCorredores() {
        
        // Usa un `StringBuilder` para construir una cadena de texto de manera eficiente.
        StringBuilder sb = new StringBuilder("=== EQUIPOS Y CORREDORES ===\n\n");
        
        // Itera sobre todos los equipos.
        for (Equipo e : equipos) {
            
            // Si el espacio del arreglo está vacío, salta a la siguiente iteración.
            if (e == null) continue;
            
            // Agrega la información del equipo al `StringBuilder`.
            // Utiliza `String.format` para formatear el presupuesto con dos decimales.
            sb.append(e.getId()).append(" | ").append(e.getNombre())
              .append(" | Performance: ").append(e.getPerformance())
              .append(" | Presupuesto: $").append(String.format("%,.2f", e.getPresupuesto())).append("\n");
              
            // Itera sobre los corredores de ese equipo.
            for (Corredor c : e.getCorredores()) {
                
                // Si el corredor no es `null`.
                if (c != null) {
                    
                    // Agrega la información del corredor con una indentación.
                    sb.append("    ").append(c.getId()).append(" | ").append(c.getNombre())
                      .append(" (#").append(c.getNumero()).append(") | Habilidad: ").append(c.getHabilidad())
                      .append("/10 | Experiencia: ").append(c.getExperiencia()).append("/10");
                      
                    // Verifica si este corredor es el principal del equipo.
                    if (e.getPrincipal() == c) sb.append(" [PRINCIPAL]");
                    
                    // Agrega un salto de línea.
                    sb.append("\n");
                }
            }
            
            // Agrega un salto de línea adicional para separar los equipos.
            sb.append("\n");
        }
        
        // Muestra toda la cadena de texto construida en un cuadro de diálogo.
        JOptionPane.showMessageDialog(null, sb.toString());
    }
    
    // ---

    // ## Métodos auxiliares
    // Define un método auxiliar para verificar si un número de corredor ya existe en algún equipo.
    private boolean numeroExiste(int numero) {
        
        // Itera sobre todos los equipos.
        for (Equipo e : equipos) {
            
            // Si el equipo no es `null` y el número del corredor existe dentro de ese equipo, devuelve `true`.
            // Se asume que el método `existeNumeroCorredor` existe en la clase `Equipo`.
            if (e != null && e.existeNumeroCorredor(numero)) {
                return true;
            }
        }
        
        // Si no se encuentra el número, devuelve `false`.
        return false;
    }

    // Define un método auxiliar para verificar si un nombre de equipo ya existe.
    private boolean existeNombreEquipo(String nombre) {
        
        // Si el nombre es `null`, no existe, devuelve `false`.
        if (nombre == null) return false;
        
        // Itera sobre todos los equipos registrados.
        for (int i = 0; i < totalEquipos; i++) {
            
            // Compara el nombre del equipo con el nombre proporcionado, ignorando mayúsculas y minúsculas.
            // También elimina los espacios en blanco (`trim()`) para la comparación.
            if (equipos[i] != null && equipos[i].getNombre().equalsIgnoreCase(nombre.trim())) {
                
                // Si se encuentra, devuelve `true`.
                return true;
            }
        }
        
        // Si no se encuentra, devuelve `false`.
        return false;
    }

    // Define un método auxiliar para mostrar un cuadro de diálogo y permitir al usuario seleccionar un equipo.
    private Equipo seleccionarEquipo(String mensaje, String titulo) {
        
        // Crea un arreglo de `String` para las opciones del cuadro de diálogo.
        // El tamaño del arreglo es igual al número total de equipos.
        String[] opciones = new String[totalEquipos];
        
        // Rellena el arreglo `opciones` con el ID y el nombre de cada equipo.
        for (int i = 0; i < totalEquipos; i++) {
            opciones[i] = equipos[i].getId() + " - " + equipos[i].getNombre();
        }
        
        // Muestra un cuadro de diálogo de selección con las opciones.
        String seleccion = (String) JOptionPane.showInputDialog(null, 
            mensaje, titulo, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            
        // Si el usuario cancela, `seleccion` será `null`, y el método devuelve `null`.
        if (seleccion == null) return null;
        
        // Itera sobre los equipos para encontrar el que coincide con la selección.
        for (Equipo e : equipos) {
            
            // Verifica que el equipo no sea `null` y que la selección comience con el ID del equipo.
            if (e != null && seleccion.startsWith(e.getId())) {
                
                // Si encuentra el equipo, lo devuelve.
                return e;
            }
        }
        
        // Si por alguna razón no se encuentra, devuelve `null`.
        return null;
    }

    // Define un método auxiliar para que el usuario seleccione un corredor de un equipo específico.
    private Corredor seleccionarCorredor(Equipo equipo, String mensaje) {
        
        // Crea un arreglo de `String` para las opciones de corredores, con un tamaño máximo de 2.
        String[] opciones = new String[2];
        int count = 0;
        
        // Obtiene el arreglo de corredores del equipo.
        Corredor[] corredores = equipo.getCorredores();
        
        // Si el primer corredor existe, lo agrega a las opciones.
        if (corredores[0] != null) opciones[count++] = corredores[0].getNombre() + " (#" + corredores[0].getNumero() + ")";
        
        // Si el segundo corredor existe, lo agrega.
        if (corredores[1] != null) opciones[count++] = corredores[1].getNombre() + " (#" + corredores[1].getNumero() + ")";
        
        // Muestra el cuadro de diálogo de selección.
        String seleccion = (String) JOptionPane.showInputDialog(null,
            mensaje, "Asignar Principal", JOptionPane.QUESTION_MESSAGE, null,
            opciones, opciones[0]);
            
        // Si el usuario no cancela (`seleccion` no es `null`).
        if (seleccion != null) {
            
            // Compara la selección con la primera opción y devuelve el corredor correspondiente si coincide.
            if (seleccion.equals(opciones[0])) return corredores[0];
            
            // Si hay un segundo corredor y coincide la selección, lo devuelve.
            else if (count > 1 && seleccion.equals(opciones[1])) return corredores[1];
        }
        
        // Si el usuario cancela o no se encuentra ninguna coincidencia, devuelve `null`.
        return null;
    }

    // ---

    // ## Métodos Getters
    // Define un método público para obtener el arreglo de equipos.
    public Equipo[] getEquipos() {
        return equipos;
    }

    // Define un método público para obtener el número total de equipos registrados.
    public int getTotalEquipos() {
        return totalEquipos;
    }
}