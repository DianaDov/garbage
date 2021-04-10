package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListOfAllRequestsController_5 {

    private static int counter = 0;

    // поля для обработки заявки
    public static String ourFio = "";
    public static String ourComplain = "";

    // поля для выбора даты
    public static LocalDate ourPeriod1 ;
    public static LocalDate ourPeriod2 ;

    private static ObservableList<NewRequest> dataFromTheTable = FXCollections.observableArrayList();

    public static ObservableList<NewRequest> getTableData() {
        return dataFromTheTable;
    }

    public static void setTableData(ObservableList<NewRequest> tableData) {
        ListOfAllRequestsController_5.dataFromTheTable = tableData;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteTheRequest;

    @FXML
    private Button handleTheRequest;

    @FXML
    private Button back;

    @FXML
    private TableView<NewRequest> table1;

    @FXML
    private TableColumn<NewRequest, String> fioOfTheClient1;

    @FXML
    private TableColumn<NewRequest, String> problem1;

    @FXML
    private TableColumn<NewRequest, String> date1;

    @FXML
    private TableColumn<NewRequest, String> time1;

    @FXML
    private TableColumn<NewRequest, String> ready1;

    @FXML
    public DatePicker period1;

    @FXML
    public DatePicker period2;

    @FXML
    private Button readyTodayRequests;

    @FXML
    private Button apply;


    @FXML
    void initialize() {
        // тени, появляющиеся при наведении курсора на кнопки
        StarterWindowController_1.shadow(deleteTheRequest);
        StarterWindowController_1.shadow(handleTheRequest);
        StarterWindowController_1.shadow(apply);
        StarterWindowController_1.shadow(readyTodayRequests);
        StarterWindowController_1.shadow(back);


        // кнопка"назад"
        back.setOnAction((ActionEvent event) -> {
            back.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("starterWindow_1.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // "перезаписываем" файл
            saveToFile(dataFromTheTable);
        });




        // кнопка "readyTodayRequests"
        readyTodayRequests.setOnAction((ActionEvent event) -> {
            // создаём stage для новых Scene
            Stage stage = new Stage();
            // выделяем память и в качестве параметра ничего не передаём
            FXMLLoader loader = new FXMLLoader();
            // закроем окно
            readyTodayRequests.getScene().getWindow().hide();
            // теперь отображаем нужное нам окно
            // указываем место расположения нужного нам файла для дальнейшей его загрузки
            loader.setLocation(getClass().getResource("readyTodayRequests_7.fxml"));
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

            // "перезаписываем" файл
            saveToFile(dataFromTheTable);
        });


        //таблица 1 (изначально выводим список заявок за всё время)

        // если не первый с запуска программы раз заходим в окно
        if (counter != 0) {
            // то очищаем ObservableList
            dataFromTheTable.clear();
        }
        counter++;
        // делаем нашу табличку редактируемой
        table1.setEditable(true);
        //специфицируем колонки таблицы
        fioOfTheClient1.setCellFactory(TextFieldTableCell.forTableColumn());//как текстовое поле
        fioOfTheClient1.setCellValueFactory(cellData -> cellData.getValue().fioProperty());

        problem1.setCellFactory(TextFieldTableCell.forTableColumn());
        problem1.setCellValueFactory(cellData -> cellData.getValue().problemProperty());

        date1.setCellFactory(TextFieldTableCell.forTableColumn());
        date1.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        time1.setCellFactory(TextFieldTableCell.forTableColumn());
        time1.setCellValueFactory(cellData -> cellData.getValue().timeProperty());

        ready1.setCellFactory(TextFieldTableCell.forTableColumn());
        ready1.setCellValueFactory(cellData -> cellData.getValue().readyProperty());

        // считываем каждую новую строку в массив, а потом добавляем эл-ты в список
        List<String> clientList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get("RequestsList.txt"))) {
            // convert it into a List
            clientList = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < clientList.size(); i++) {
            String[] separated = clientList.get(i).split("\\|");
            String fio1 = separated[0].trim();
            String problem1 = separated[1].trim();
            String dateString = separated[2].trim();
            String timeString = separated[3].trim();
            String ready1 = separated[4].trim();
            dataFromTheTable.add(new NewRequest(fio1, problem1, dateString, timeString, ready1));
        }
        // загружаем данные из ObservableList в таблицу
        table1.setItems(dataFromTheTable);

        //(обрабатываем изменение полей таблички, можем редактировать не все поля в таблице)
        date1.setOnEditCommit(event ->
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setDate(event.getNewValue()));
        time1.setOnEditCommit(event ->
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setTime(event.getNewValue()));

        // кнопка"удалить заявку"
        deleteTheRequest.setOnAction((ActionEvent) -> {
            // удаляем выбранную заявку из таблицы 1
            NewRequest t = table1.getSelectionModel().getSelectedItem();
            dataFromTheTable.remove(t);
        });


        // кнопка "обработать заявку"
        handleTheRequest.setOnAction((ActionEvent event) -> {
            NewRequest p = table1.getSelectionModel().getSelectedItem();
            // если заявка уже выполнена,то изменить её не можем
            if (!p.getReady().equals("Выполнено")) {
                // охраняем фио и проблему в отдельную переменную, чтобы знать атрибуты какой заявки нужно будет обрабатываать
                ourFio = p.getFio();
                ourComplain = p.getProblem();
                // и удаляем выбранный элемент из ObservableList и соответственно из таблицы
                dataFromTheTable.remove(p);
                handleTheRequest.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("handleTheRequest_6.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();


                // "перезаписываем" файл
                saveToFile(dataFromTheTable);
            }
        });


        period1.setOnAction((ActionEvent) -> {
            ourPeriod1 = period1.getValue();
                });
        period2.setOnAction((ActionEvent) -> {
            ourPeriod2 = period2.getValue();
        });


        // обрабатываем нажатие клавиши "apply" (применить)
        // используя выбранный пользователем промежуток времени, выводим только соответствующие ему заявки
        apply.setOnAction((ActionEvent) -> {
            // если поля диапазона дат заполнены,
            if (period1.getValue() != null && period2.getValue() != null) {
                // то открываем таблицу с соответствующими диапазону заявками
                handleTheRequest.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("specificRequestsList_9.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();


                // "перезаписываем" файл
                saveToFile(dataFromTheTable);
            }
        });



    }


    // записываем введённые значения в файл RequestsList.txt
    public static void saveToFile(ObservableList<NewRequest> list) {
        FileWriter fw;
        try {
            fw = new FileWriter("RequestsList.txt");
            StringBuilder sb = new StringBuilder();
            sb.append("ФИО|проблема|дата поступления заявки|время поступления заявки|состояние готовности|\n");
            int N = list.size();
            for (int i = -1; i < N - 1; i++) {
                String Fio = list.get(i + 1).getFio();
                String problem = list.get(i + 1).getProblem();
                String date = list.get(i + 1).getDate();
                String time = list.get(i + 1).getTime();
                String ready = list.get(i + 1).getReady();
                sb.append(Fio);
                sb.append("|");
                sb.append(problem);
                sb.append("|");
                sb.append(date);
                sb.append("|");
                sb.append(time);
                sb.append("|");
                sb.append(ready);
                sb.append("|");
                sb.append("\n");
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }

}
