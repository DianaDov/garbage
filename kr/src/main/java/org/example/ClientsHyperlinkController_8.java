package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ClientsHyperlinkController_8 {

    private static int counter = 0;

    private static ObservableList<NewClient> tableData = FXCollections.observableArrayList();

    public static ObservableList<NewClient> getTableData() {
        return tableData;
    }

    public static void setTableData(ObservableList<NewClient> tableData) {
        ClientsHyperlinkController_8.tableData = tableData;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<NewClient> table;

    @FXML
    private TableColumn<NewClient, String> fioOfTheClient;

    @FXML
    private TableColumn<NewClient, String> agreementNumber;

    @FXML
    private TableColumn<NewClient, String> address;

    @FXML
    private TableColumn<NewClient, String> ipAddress;


    @FXML
    void initialize() {

        //таблица
        // если не первый с запуска программы раз заходим в окно
        if (counter != 0) {
            // то очищаем ObservableList
            tableData.clear();
        }
        counter++;
        // делаем нашу табличку редактируемой
        table.setEditable(true);
        /* специфицируем, так сказть, колонки таблицы
         Use the setCellFactory method to reimplement the table cell as a text field with
         the help of the TextFieldTableCell class.
         */
        fioOfTheClient.setCellFactory(TextFieldTableCell.forTableColumn());//как текстовое поле
        fioOfTheClient.setCellValueFactory(cellData -> cellData.getValue().fioProperty());

        agreementNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        agreementNumber.setCellValueFactory(cellData -> cellData.getValue().agreementNumberProperty());

        address.setCellFactory(TextFieldTableCell.forTableColumn());
        address.setCellValueFactory(cellData -> cellData.getValue().homeAddressProperty());

        ipAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        ipAddress.setCellValueFactory(cellData -> cellData.getValue().ipAddressProperty());


        // считываем каждую новую строку в массив, а потом добавляем эл-ты в список
        List<String> clientList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get("ClientsList.txt"))) {
            // convert it into a List
            clientList = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < clientList.size(); i++) {
            String[] separated = clientList.get(i).split("\\|");
            String fioOfTheClient1 = separated[0].trim();
            String agreementNumber1 = separated[1].trim();
            String address1 = separated[2].trim();
            String ipAddress1 = separated[3].trim();
            tableData.add(new NewClient(fioOfTheClient1, agreementNumber1, address1, ipAddress1));
        }


        // загружаем данные из ObservableList в таблицу
        table.setItems(tableData);


        // записываем ObservableList в LinkedList, а затем сохраняем наши значения в файл
        LinkedList<NewClient> clientsFirstPart = new LinkedList<>(tableData);
        // "перезаписываем" файл
        saveToFile(clientsFirstPart);


    }

    // записываем введённые значения в файл1
    public static void saveToFile(LinkedList<NewClient> list) {
        FileWriter fw;
        try {
            fw = new FileWriter("ClientsList.txt");
            StringBuilder sb = new StringBuilder();
            sb.append("ФИО|номер договора|домашний адрес|ip адрес|\n");
            int N = list.size();
            for (int i = -1; i < N - 1; i++) {
                String Fio = list.get(i + 1).getFio();
                String number0fTheAgreement = list.get(i + 1).getAgreementNumber();
                String homeAddress = list.get(i + 1).getHomeAddress();
                String IPAddress = list.get(i + 1).getIpAddress();
                sb.append(Fio);
                sb.append("|");
                sb.append(number0fTheAgreement);
                sb.append("|");
                sb.append(homeAddress);
                sb.append("|");
                sb.append(IPAddress);
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
