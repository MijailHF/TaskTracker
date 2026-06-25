package com.mycompany.tasktracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class TaskManager {
    List<Task> tasks;
    Path PATH = Path.of("task.json");

    public TaskManager() {
        this.tasks = loadTask();
    }
    
    private List<Task> loadTask(){
        List<Task> storedTasks = new ArrayList<>();
        if(!Files.exists(PATH)){
            return storedTasks;
        }
        try {
            String tasksString = Files.readString(PATH);
            String[] tasksArray = tasksString.replace("[", "")
                                .replace("]", "").split("},");

            for (String task : tasksArray) {
                if(!task.endsWith("}")){
                    task = task + "}";
                    storedTasks.add(Task.fromJson(task));
                }
                else {
                    storedTasks.add(Task.fromJson(task));
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return storedTasks;    
    }
    
    public void saveTasks(){
        String json = "";
        for (Task task : tasks) {
            json = json + task.toJson() + ",\n";
        }
        if (json.endsWith(",\n")){
            json = json.substring(0, json.length() - 2);
            json = json + "\n";
        }
        
        json = "[" + json +"]";
        
        try{
            Files.writeString(PATH, json);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void addTask(String description){   
        tasks.add(new Task(description));
        System.out.println("Task added succesfully!");
    }
    
    public void deleteTask(String id){
        Task taskToDelete = findTask(id);
        if (taskToDelete != null) {
            tasks.remove(taskToDelete);
            System.out.println("Task deleted successfully.");
        }
    }

    public void updateTask(String id, String description) {
        Task taskToUpdate = findTask(id);
        if (taskToUpdate != null) {
            taskToUpdate.updateDescription(description);
            System.out.println("Task updated successfully.");
        }
    }

    public void markInProgress(String id) {
        Task taskToMark = findTask(id);
        if (taskToMark != null)
            taskToMark.markInProgress();
    }

    public void markDone(String id) {
        Task taskToMark = findTask(id);
        if (taskToMark != null)
            taskToMark.markDone();
    }

    public void listTasks(String type) {
        Status filteredStatus = null;

        if (!type.equals("all")) {
            try {
                filteredStatus = Status.valueOf(type.replace("-", "_").toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Illegal status: " + type);
            }
        }

        for (Task task : this.tasks) {
            String status = task.getState().toString();
            if (type.equals("all") || task.getState().equals(filteredStatus)) {
                System.out.println(task);
            }
        }
    }
    
    private Task findTask(String id){
        try {
            for (Task task : tasks) {
                if(task.getId() == Integer.parseInt(id))
                    return task;
            }
            System.out.println("That id does not exist.");
        } catch (NumberFormatException e) {
            System.err.println("That task doesn't exists. " + e.getMessage());
        }
        return null;
    }
}
