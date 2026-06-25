/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tasktracker;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author yumerth-mijail
 */
class Task {
    static int lastId;
    private int id;
    private String description;
    private Status state;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    
    public Task(String description) {
        this.id = lastId + 1;
        this.description = description;
        this.state = Status.TO_DO;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Status getState() {
        return state;
    }

    public void markInProgress() {
        this.state = Status.IN_PROGRESS;
        this.updateAt = LocalDateTime.now();
    }

    public void markDone() {
        this.state = Status.DONE;
        this.updateAt = LocalDateTime.now();
    }

    public void updateDescription(String description){
        this.description = description;
        this.updateAt = LocalDateTime.now();
    }

    public String toJson(){
        return "{ \"id\" : " + this.id + " ,\n"
                + " \"description\" : \"" + this.description + "\",\n"
                + " \"state\" : \"" + this.state+ "\",\n"
                + " \"createAt\" : \"" + this.createAt + "\",\n"
                + " \"updateAt\" : \"" + this.updateAt +"\" }";
    }

    public static Task fromJson(String json){
        
        String[] values = json.replace("{", "").replace("}", "")
                            .replace("\"", "").split(",");
        
        String id = values[0].split(":")[1].strip();
        String description = values[1].split(":")[1].strip();
        String state = values[2].split(":")[1].strip();
        String createAtString = values[3].split("[a-z] :")[1].strip();
        String updateAtString = values[4].split("[a-z] :")[1].strip();
        
        Status taskStatus = Status.valueOf(Status.class,
                state.toUpperCase().replace(" ", "_"));
        LocalDateTime createAt = LocalDateTime.parse(createAtString);
        LocalDateTime updateAt = LocalDateTime.parse(updateAtString);
        
        Task task = new Task(description);
        task.id = Integer.parseInt(id);
        task.state = taskStatus;
        task.createAt = createAt;
        task.updateAt = updateAt;
        
        if(lastId < task.id)
            lastId = task.id;
        
        return task;
    }

    @Override
    public String toString(){
        return String.format("%-3d %-20s %-13s %-35s %-35s", id, description, state, createAt, updateAt);
    }
}
