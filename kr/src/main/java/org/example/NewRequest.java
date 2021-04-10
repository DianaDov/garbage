package org.example;

import javafx.beans.property.SimpleStringProperty;

// класс заявок
public class NewRequest {
    private final SimpleStringProperty fio;
    private final SimpleStringProperty problem;
    private final SimpleStringProperty date;
    private final SimpleStringProperty time;
    private final SimpleStringProperty ready;
    // ссылка на следующий элемент списка
    public NewRequest next;

    // Конструктор с пятью аргументами (по характеристикам объекта)
    public NewRequest(String fio, String problem, String date,
                      String time, String ready) {
        this.fio = new SimpleStringProperty(fio);
        this.problem = new SimpleStringProperty(problem);
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.ready = new SimpleStringProperty(ready);
    }

    // конструктор по готовому элементу
    public NewRequest(NewRequest students) {
        this.fio = students.fio;
        this.problem = students.problem;
        this.date = students.date;
        this.time = students.time;
        this.ready = students.ready;
        next = null;
    }

    // метод для получения следующего элемента
    public NewRequest getNext(){
        return next;
    }

    // метод для установки следующего элемента
    public void setNext(NewRequest students){
        next = students;
    }

    public String getFio() {
        return fio.get();
    }

    public SimpleStringProperty fioProperty() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio.set(fio);
    }

    public String getProblem() {
        return problem.get();
    }

    public SimpleStringProperty problemProperty() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem.set(problem);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getReady() {
        return ready.get();
    }

    public SimpleStringProperty readyProperty() {
        return ready;
    }

    public void setReady(String ready) {
        this.ready.set(ready);
    }
}
