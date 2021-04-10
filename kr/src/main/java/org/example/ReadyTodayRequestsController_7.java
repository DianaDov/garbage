package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadyTodayRequestsController_7 {

    private static int counter2 = 0;

    private static ObservableList<NewRequest> dataFromTheTable2 = FXCollections.observableArrayList();

    public static ObservableList<NewRequest> getTableData2() {
        return dataFromTheTable2;
    }

    public static void setTableData2(ObservableList<NewRequest> tableData) {
        ReadyTodayRequestsController_7.dataFromTheTable2 = tableData;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private TableView<NewRequest> table2;

    @FXML
    private TableColumn<NewRequest, String> fioOfTheClient2;

    @FXML
    private TableColumn<NewRequest, String> problem2;

    @FXML
    private TableColumn<NewRequest, String> date2;

    @FXML
    private TableColumn<NewRequest, String> time2;

    @FXML
    private TableColumn<NewRequest, String> ready2;

    @FXML
    void initialize() {
        StarterWindowController_1.shadow(back);

        // кнопка"назад"
        back.setOnAction((ActionEvent event) -> {
            back.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("listOfAllRequests_5.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();


            // записываем в текстовый файл все заявки, выполненные сегодня
            // записываем ObservableList в LinkedList, а затем сохраняем наши значения в файл
            LinkedList<NewRequest> ordersFirstPart = new LinkedList<>(dataFromTheTable2);
            // "перезаписываем" файл
            saveToReadyTodayFile(ordersFirstPart);
            //  а затем очищаем
            ordersFirstPart.clear();
        });


        //таблица 2 (выводим список заявок, выполненных за текущие сутки)

        // если не первый с запуска программы раз заходим в окно
        if (counter2 != 0) {
            // то очищаем ObservableList
            dataFromTheTable2.clear();
        }
        counter2++;

        // делаем нашу табличку редактируемой
        table2.setEditable(true);
        // специфицируем, так сказть, колонки таблицы

        fioOfTheClient2.setCellFactory(TextFieldTableCell.forTableColumn());//как текстовое поле
        fioOfTheClient2.setCellValueFactory(cellData -> cellData.getValue().fioProperty());

        problem2.setCellFactory(TextFieldTableCell.forTableColumn());
        problem2.setCellValueFactory(cellData -> cellData.getValue().problemProperty());

        date2.setCellFactory(TextFieldTableCell.forTableColumn());
        date2.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        time2.setCellFactory(TextFieldTableCell.forTableColumn());
        time2.setCellValueFactory(cellData -> cellData.getValue().timeProperty());

        ready2.setCellFactory(TextFieldTableCell.forTableColumn());
        ready2.setCellValueFactory(cellData -> cellData.getValue().readyProperty());


        // считываем каждую новую строку в массив, а потом добавляем эл-ты в список
        List<String> clientList2 = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get("ReadyRequests.txt"))) {
            // convert it into a List
            clientList2 = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < clientList2.size(); i++) {
            String[] separated = clientList2.get(i).split("\\|");
            String fio1 = separated[0].trim();
            String problem1 = separated[1].trim();
            String date1 = separated[2].trim();
            String time1 = separated[3].trim();
            String ready1 = separated[4].trim();
            //LocalDate readyTime = LocalDate.parse(separated[5].trim());
            String readyTime = separated[5].trim();

            // проверяем время, когда заявку выполнили
            // и если сегодня, то выводим на экран и добавляем в файл

            // текущая дата
            Date now = new Date();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            //LocalDate dateString = LocalDate.parse(df.format(now));
            String dateString = df.format(now);

            if (readyTime.equals(dateString)) {
                // добавляем элемент в список
                dataFromTheTable2.add(new NewRequest( fio1, problem1,  date1, time1, ready1));
            }
        }

        // (обрабатываем изменение полей таблички)
        date2.setOnEditCommit(event ->
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setDate(event.getNewValue()));
        time2.setOnEditCommit(event ->
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setTime(event.getNewValue()));


        // загружаем данные из ObservableList в таблицу 2
        table2.setItems(dataFromTheTable2);



    }

    // записываем введённые значения в файл2
    public static void saveToReadyTodayFile(LinkedList<NewRequest> list) {
        FileWriter fw;
        try {
            fw = new FileWriter("ReadyTodayRequests.txt");
            StringBuilder sb = new StringBuilder();
            sb.append("ФИО|проблема|дата поступления заявки|время поступления заявки|состояние готовности|\n");
            int N = list.size();
            for (int i = -1; i < N - 1; i++) {
                String Fio = list.get(i + 1).getFio();
                String problem = list.get(i + 1).getProblem();
                String date = list.get(i + 1).getDate();
                String time = list.get(i + 1).getTime();
                String ready = list.get(i + 1).getReady();
                sb.append(Fio);sb.append("|");
                sb.append(problem);sb.append("|");
                sb.append(date);sb.append("|");
                sb.append(time);sb.append("|");
                sb.append(ready);sb.append("|");
                sb.append("\n");
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }

}
