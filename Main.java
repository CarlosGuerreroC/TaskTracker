public class Main {
    public static void main(String[] args){
        if (args.length==0){
            printHelp();
            return;
        }

        TaskManager manager = new TaskManager();
        String command = args[0];
        try {
            switch (command.toLowerCase()){
                case "add":
                    if (args.length < 2){
                        System.out.println("Uso: add <descripcion>");
                    }else {
                        String description = args[1];
                        manager.addTask(description);
                    }
                    break;
                case "update":
                    if (args.length < 3){
                        System.out.println("Uso: update <id> <nueva_descripcion>");
                    }else {
                        int id = Integer.parseInt(args[1]);
                        String newDesc = args[2];
                        manager.updateTask(id, newDesc);
                    }
                    break;
                case "delete":
                    if (args.length < 2){
                        System.out.println("Uso: delete <id>");
                    }else {
                        int id = Integer.parseInt(args[1]);
                        manager.deleteTask(id);
                    }
                    break;
                case "status":
                    if (args.length < 3){
                        System.out.println("Uso: status <id> <todo|in-progress|done>");
                    }else {
                        int id = Integer.parseInt(args[1]);
                        String status = args[2];
                        manager.changeStatus(id, status);
                    }
                    break;
                case "list":
                    if (args.length < 1){
                        manager.listAll();
                    }else {
                        manager.listByStatus(args[1]);
                    }
                    break;
                default:
                    System.out.println("Comando no reconocido.");
                    printHelp();
            }
        }catch (NumberFormatException e){
            System.out.println("Error: el ID debe ser un numero entero.");
        }
    }
    private static void printHelp(){
        System.out.println("Comandos disponibles:");
        System.out.println("add <descripción>                  → Agrega una nueva tarea");
        System.out.println("update <id> <nueva_descripción>    → Actualiza la descripción de una tarea");
        System.out.println("delete <id>                        → Elimina una tarea por su ID");
        System.out.println("status <id> <todo|in-progress|done> → Cambia el estado de una tarea");
        System.out.println("list                               → Lista todas las tareas");
        System.out.println("list <todo|in-progress|done>       → Lista tareas por estado");
    }
}
