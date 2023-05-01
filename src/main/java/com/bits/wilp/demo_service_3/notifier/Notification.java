package com.bits.wilp.demo_service_3.notifier;

public class Notification {

    public String id;

    public String message;

    public Notification(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}