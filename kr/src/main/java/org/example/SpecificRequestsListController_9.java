package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpecificRequestsListController_9 {

    private static int counter2 = 0;

    private static ObservableList<NewRequest> specificTableData = FXCollections.observableArrayList();

    public static ObservableList<NewRequest> getTableData2() {
        return specificTableData;
    }

    public static void setTableData2(ObservableList<NewRequest> tableData) {
        SpecificRequestsListController_9.specificTableData = tableData;
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
    private Label lb1;

    @FXML
    private Label lb2;

    @FXML
    void initialize() throws ParseException {
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

            // "перезаписываем" файл
            saveToSpecificFile(specificTableData);
        });

        //таблица 2 (выводим список заявок, выполненных за текущие сутки)

        // если не первый с запуска программы раз заходим в окно
        if (counter2 != 0) {
            // то очищаем ObservableList
            specificTableData.clear();
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

        // устанавливаем в label диапазон
        lb1.setText(ListOfAllRequestsController_5.ourPeriod1.toString());
        lb2.setText(ListOfAllRequestsController_5.ourPeriod2.toString());
        // затем преобразуем в String крайние даты диапазона, взятые из datePicker-ов
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ZoneId defaultZoneId = ZoneId.systemDefault();
        // преобразуем LocalDate в Date
        Date sDate= Date.from(ListOfAllRequestsController_5.ourPeriod1.atStartOfDay(defaultZoneId).toInstant());
        Date eDate= Date.from(ListOfAllRequestsController_5.ourPeriod2.atStartOfDay(defaultZoneId).toInstant());



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
            // преобразуем в Date
            Date date = sdf.parse(dateString);
            String timeString = separated[3].trim();
            String ready1 = separated[4].trim();
            // проверяем подходит ли дата в промежуток времени сравниваем тип Date
            if (date.compareTo(sDate) >= 0 && date.compareTo(eDate) <= 0) {
                // то добавляем эту заявку в список
                specificTableData.add(new NewRequest(fio1, problem1, dateString, timeString, ready1));
            }
        }
        // загружаем данные из ObservableList в таблицу
        table2.setItems(specificTableData);

        // (обрабатываем изменение полей таблички)
        time2.setOnEditCommit(event ->
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setTime(event.getNewValue()));


        // "перезаписываем" файл SpecificRequests.txt
        saveToSpecificFile(specificTableData);
    }


    // записываем введённые значения в файл SpecificRequests.txt
    public static void saveToSpecificFile(ObservableList<NewRequest> list) {
        FileWriter fw;
        try {
            fw = new FileWriter("SpecificRequests.txt");
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
