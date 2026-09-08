package org.example;

public class InsufficientBalance extends RuntimeException{
    public InsufficientBalance(){
        super();
    }
    public InsufficientBalance(String message){
        super(message);
    }
}
