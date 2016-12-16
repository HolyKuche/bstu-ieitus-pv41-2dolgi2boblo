package ru.tdtb.application.security;

public class ManagersNotFoundException extends RuntimeException {
    public ManagersNotFoundException(String message) {
        super(message);
    }
}