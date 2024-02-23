package com.tarefa.agenda.exception;
public class IdException extends  RuntimeException {

    public IdException(Long id) {
        super("Task not found" + id);
    }
}