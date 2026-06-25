package com.mycompany.tasktracker;

public class TaskTrackerV2 {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        if(args.length == 0){
            System.out.println("Please enter a command.");
            return;
        }
        String command = args[0].toLowerCase();
        switch(command){
            case "add": 
                if(args.length < 2) {
                    System.out.println("Use this form: \"add <description>\"");
                    return;
                }
                manager.addTask(args[1]);//description
                break;
            case "update":
                if(args.length < 3) {
                    System.out.println("Use this form: \"update <id> <description>\"");
                    return;
                }
                manager.updateTask(args[1],args[2]);//(id, description)
                break;
            case "delete":
                if(args.length < 2) {
                    System.out.println("Use this form: \"delete <id>\"");
                    return;
                }
                manager.deleteTask(args[1]);
                break;
            case "mark-in-progress":
                if(args.length < 2) {
                    System.out.println("Use this form: \"mark-in-progress <id>\"");
                    return;
                }
                manager.markInProgress(args[1]);
                break;
            case "mark-done":
                if(args.length < 2) {
                    System.out.println("Use this form: \"mark-done <id>\"");
                    return;
                }
                manager.markDone(args[1]);
                break;
            case "list":
                if ((args.length < 2)) {
                    manager.listTasks("all");
                } else {
                    manager.listTasks(args[1]);
                }
                break;
            default:
                System.out.println("Please insert a command.");        
        }
        manager.saveTasks();
    }
}
