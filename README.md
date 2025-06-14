#  Task Tracker (Java CLI)

Task Tracker es una aplicación de línea de comandos escrita en Java que te permite gestionar tus tareas fácilmente desde la terminal. 
Puedes agregar, actualizar, cambiar el estado y listar tareas. Los datos se almacenan en un archivo `tasks.json`.

---

##  Requisitos

- Java JDK 8 o superior
- Git Bash, CMD o PowerShell (en Windows) / Terminal (en macOS/Linux)

---

##  Estructura del proyecto

```
TaskTracker/
├── Main.java
├── Task.java
├── TaskManager.java
├── manifest.txt
└── tasks.json (se crea automáticamente)
```

---

##  Compilación

1. Abre la terminal y navega a la carpeta del proyecto:

```bash
cd ruta/del/proyecto/TaskTracker
```

2. Compila los archivos `.java`:

```bash
javac Task.java TaskManager.java Main.java
```

3. Crea el archivo `.jar` ejecutable:

```bash
jar cfm tasktracker.jar manifest.txt *.class
```

---

## 🛠️ Uso

Ejecuta comandos usando el archivo `.jar`:

```bash
java -jar tasktracker.jar <comando> [argumentos]
```

### Comandos disponibles:

| Comando                             | Descripción                                        |
|-------------------------------------|----------------------------------------------------|
| `add <descripción>`                | Agrega una nueva tarea                             |
| `update <id> <nueva_descripción>`  | Actualiza la descripción de una tarea              |
| `delete <id>`                      | Elimina una tarea por su ID                        |
| `status <id> <todo|in-progress|done>` | Cambia el estado de una tarea                   |
| `list`                             | Lista todas las tareas                             |
| `list <todo|in-progress|done>`     | Lista tareas por estado específico                 |

---

##  Ejemplos

```bash
java -jar tasktracker.jar add "Estudiar para el examen"
java -jar tasktracker.jar status 1 in-progress
java -jar tasktracker.jar update 1 "Estudiar programación avanzada"
java -jar tasktracker.jar list
java -jar tasktracker.jar list done
java -jar tasktracker.jar delete 1
```

---

##  Almacenamiento

- Las tareas se guardan en el archivo `tasks.json` en el mismo directorio del programa.
- Cada tarea contiene:
  - `id`: Identificador único
  - `description`: Texto de la tarea
  - `status`: `todo`, `in-progress`, o `done`
  - `createdAt` y `updatedAt`: Fechas en formato ISO

---

##  Licencia
Este proyecto es de uso libre para fines educativos y personales.
## URL del proyecto
https://roadmap.sh/projects/task-tracker
