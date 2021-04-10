package org.example;

import javafx.beans.property.SimpleStringProperty;

// класс клиентов
public class NewClient {

    // Property позволяет нам получать автоматические уведомления при любых изменениях переменных
    private final SimpleStringProperty fio;
    private final SimpleStringProperty agreementNumber;
    private final SimpleStringProperty homeAddress;
    private final SimpleStringProperty ipAddress;
    // ссылка на следующий элемент списка
    // поле next содержит всего лишь ссылку на другой элемент списка,
    // а не сам объект NewClient
    public NewClient next;
    // ссылка представляет собой число,ассоциированное с объектом
    // это число соответствует АДРЕСУ объекта в памяти компьютера

    // Конструктор с четырьмя аргументами (по характеристикам объекта)
    public NewClient(String fio, String agreementNumber, String homeAddress, String ipAddress) {
        /*
        с помощью ключевого слова this мы обращаемся к полям объекта.
        (требуется для того, чтобы метод мог сослаться на вызвавший его объект)
        Если в методе нет своей переменной с таким же именем, this можно опустить.
         */
        this.fio = new SimpleStringProperty(fio);
        this.agreementNumber = new SimpleStringProperty(agreementNumber);
        this.homeAddress = new SimpleStringProperty(homeAddress);
        this.ipAddress = new SimpleStringProperty(ipAddress);
    }


    // конструктор по готовому элементу
    public NewClient(NewClient students) {
        this.fio = students.fio;
        this.agreementNumber = students.agreementNumber;
        this.homeAddress = students.homeAddress;
        this.ipAddress = students.ipAddress;
        // значение null указывает на то, что ссылка не указывает ни на что
        // до того, как элемент будет связан с другими элементами
        next = null;
    }

    // метод для получения следующего элемента
    public NewClient getNext(){
        return next;
    }

    // метод для установки следующего элемента
    public void setNext(NewClient students){
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

    public String getAgreementNumber() {
        return agreementNumber.get();
    }

    public SimpleStringProperty agreementNumberProperty() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber.set(agreementNumber);
    }

    public String getHomeAddress() {
        return homeAddress.get();
    }

    public SimpleStringProperty homeAddressProperty() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress.set(homeAddress);
    }

    public String getIpAddress() {
        return ipAddress.get();
    }

    public SimpleStringProperty ipAddressProperty() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress.set(ipAddress);
    }
}
