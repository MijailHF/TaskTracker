/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.tasktracker;

/**
 *
 * @author yumerth-mijail
 */
public enum Status {
    TO_DO("to do"),
    IN_PROGRESS("in progress"),
    DONE("done");
    
    String value;
    
    Status(String value){
        this.value = value;
    }
    
    @Override
    public String toString(){
        return value;
    }
    
}
