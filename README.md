# 🏎️ F1 Manager - Sistema de Gestión de Equipos de Fórmula 1


## 📋 Características Principales
- Registro de equipos y corredores
- Asignación de corredores principales
- Simulación básica de carreras
- Generación de reportes en CSV:
  - `equipos.csv`: Listado de equipos registrados
  - `corredores.csv`: Listado de corredores por equipo
  - `carreras.csv`: Resultados de carreras simuladas

## 🛠️ Tecnologías Utilizadas
- Java SE
- JOptionPane para la interfaz
- FileWriter para generación de CSV

## 📁 Estructura del Proyecto
F1_Manager/
├── src/
│ ├── F1_Manager.java # Clase principal
│ ├── Equipo.java # Modelo de equipo
│ ├── Corredor.java # Modelo de corredor
│ ├── Carrera.java # Modelo de carrera
│ ├── Registro.java # Lógica de registro
│ ├── Reporte.java # Generación de reportes
│ └── Menu.java # Menú principal
├── equipos.csv # Reporte de equipos (generado)
├── corredores.csv # Reporte de corredores (generado)
└── carreras.csv # Reporte de carreras (generado)

## ▶️ Cómo Ejecutar
1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/F1_Manager.git
Compila y ejecuta:
javac src/*.java
java -cp src F1_Manager

🖥️ Uso del Sistema
Selecciona opciones del menú principal:

Registrar equipos y corredores

Asignar corredor principal a cada equipo

Simular carreras

Generar reportes automáticos

Los reportes se guardan automáticamente en archivos CSV.

📝 Requisitos
Java JDK 8 o superior

Permisos de escritura para generar archivos CSV

📄 Licencia
Este proyecto está bajo la licencia MIT. Ver LICENSE para más detalles.

Hecho con ❤️ para la clase de Introducción a la Programación del profe Andres

##Nota:
Si van a hacer un cambio o algo importante por favor, consultar a  Didier Escamilla
