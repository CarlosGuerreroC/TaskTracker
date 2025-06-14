import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskManager {
    private static final String FILE_NAME = "tasks.json";
    private List<Task> tasks;

    public TaskManager() {
        tasks = loadTasks();
    }

    private List<Task> loadTasks(){
        List<Task> loadedTasks = new ArrayList<>();
        try {
            if (!Files.exists(Paths.get(FILE_NAME))){
                Files.write(Paths.get(FILE_NAME), "[]".getBytes());
            }
            String content = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
            content = content.trim();
            if (content.length() > 2){
                content = content.substring(1, content.length() - 1);
                String[] entries = content.split("},\\s*\\{");
                for (String entry : entries){
                    String json = entry;
                    if (!json.startsWith("{")) json = "{" + json;
                    if (!json.endsWith("}"))  json = json + "}";
                    loadedTasks.add(parseTask(json));
                }
            }
        }catch (IOException e){
            System.out.println("Error al cargar la tarea: " + e.getMessage());
        }
        return loadedTasks;
    }

    private Task parseTask(String json){
        Map<String, String> map = new HashMap<>();
        json = json.replaceAll("[{}\"]","");
        String[] parts = json.split(",");
        for (String part : parts){
            String[] kv = part.split(":",2);
            if (kv.length==2){
                map.put(kv[0].trim(),kv[1].trim());
            }
        }
        return new Task(
                Integer.parseInt(map.get("id")),
                map.get("description"),
                map.get("status"),
                map.get("createdAt"),
                map.get("updatedAt")
        );
    }

    private void saveTaskas(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("[\n");
            for (int i = 0; i < tasks.size(); i++){
                Task t = tasks.get(i);
                writer.write(String.format("  {\"id\": %d, \"description\": \"%s\", \"status\": \"%s\", \"createdAt\": \"%s\", \"updatedAt\": \"%s\"}",
                t.getId(), t.getDescription(), t.getStatus(), t.getCreatedAt(), t.getUpdatedAt()));
                if (i < tasks.size() - 1) writer.write(",");
                writer.write("[\n");
            }
            writer.write("]");
        }catch (IOException e){
            System.out.println("Error al guardar las tareas: "+ e.getMessage());
        }
    }

    public void addTask(String description){
        int id = tasks.stream().mapToInt(Task::getId).max().orElse(0) +1;
        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Task newTask = new Task(id,description, "todo",now,now);
        tasks.add(newTask);
        saveTaskas();
        System.out.println("Tarea agregada.");
    }

    public void updateTask(int id, String newDescription){
        for (Task t : tasks){
            if (t.getId() == id){
                t.setDescription(newDescription);
                saveTaskas();
                System.out.println("Tarea actualizada");
                return;
            }
        }
        System.out.println("Tarea no encontrada.");
    }

    public void deleteTask(int id){
        boolean removed = tasks.removeIf(t -> t.getId() == id);
        if (removed){
            saveTaskas();
            System.out.println("Tarea eliminada. ");
        }else {
            System.out.println("Tarea no encontrada");
        }
    }

    public void changeStatus(int id, String status){
        for (Task t: tasks){
            if (t.getId()==id){
                t.setStatus(status);
                saveTaskas();
                System.out.println("Estado actualizado.");
                return;
            }
        }
        System.out.println("Tarea no encontrada.");
    }

    public void listAll(){
        tasks.forEach(System.out::println);
    }

    public void listByStatus(String status){
        tasks.stream()
                .filter(t -> t.getStatus().equalsIgnoreCase(status))
                .forEach(System.out::println);
    }




}
