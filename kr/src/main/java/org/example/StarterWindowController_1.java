package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StarterWindowController_1 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button list_of_clients;

    @FXML
    private Button new_request;

    @FXML
    private Button list_of_requests;

    @FXML
    private Button charts;

    @FXML
    void initialize() {
        // тени, появляющиеся при наведении курсора на кнопки
        shadow(list_of_clients);
        shadow(new_request);
        shadow(list_of_requests);
        shadow(charts);

        // создаём отслеживание событий,для каждого пункта меню

        // создаём stage для новых Scene
        Stage stage = new Stage();
        // выделяем память и в качестве параметра ничего не передаём
        FXMLLoader loader = new FXMLLoader();

        // кнопка "список клиентов"
        list_of_clients.setOnAction((ActionEvent event) -> {
            // закроем окно
            list_of_clients.getScene().getWindow().hide();
            // теперь отображаем нужное нам окно
            // указываем место расположения нужного нам файла для дальнейшей его загрузки
            loader.setLocation(getClass().getResource("listOfAllClients_2.fxml"));
            // загружаем файл для дальнейшего отображения
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // путь к файлу который необхоодимо загрузить
            Parent root = loader.getRoot();
            // указываем то окошко которое нам необходимо загрузить
            stage.setScene(new Scene(root));
            // показать и подождать того момента когда само наше окошко отобразиться
            stage.show();
        });

        // аналогично с остальными кнопками

        // кнопка "новая заявка"
        new_request.setOnAction((ActionEvent event) -> {
            new_request.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("newRequest_4.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();
        });

        // кнопка "список заявок"
        list_of_requests.setOnAction((ActionEvent event) -> {
            list_of_requests.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("listOfAllRequests_5.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();
        });

        // кнопка "статистика"
        charts.setOnAction((ActionEvent event) -> {
            // закроем окно
            charts.getScene().getWindow().hide();
            // теперь отображаем нужное нам окно
            // указываем место расположения нужного нам файла для дальнейшей его загрузки
            loader.setLocation(getClass().getResource("chartsForAllRequests_10.fxml"));
            // загружаем файл для дальнейшего отображения
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // путь к файлу который необхоодимо загрузить
            Parent root = loader.getRoot();
            // указываем то окошко которое нам необходимо загрузить
            stage.setScene(new Scene(root));
            // показать и подождать того момента когда само наше окошко отобразиться
            stage.show();
        });
    }
    //метод добавляет тени кнопкам
    public static void shadow(Button shadoww) {
        // Adding the shadow when the mouse cursor is on
        DropShadow shadow = new DropShadow();
        shadoww.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> shadoww.setEffect(shadow));
        // Removing the shadow when the mouse cursor is off
        shadoww.addEventHandler(MouseEvent.MOUSE_EXITED, e -> shadoww.setEffect(null));
    }
}
