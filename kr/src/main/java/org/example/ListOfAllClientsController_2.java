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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// отобразим в нашей таблице некоторые данные.
// Для этого необходимо создать класс-контроллер для представления listOfAllClients_2.fxml
public class ListOfAllClientsController_2 {

    private static int counter = 0;

    /*
     для записи данных из списка в таблицу используем ObservableList
     Мы работаем с классами-представлениями JavaFX, которые необходимо информировать при любых изменениях
     в списке адресатов. Это важно, потому что, не будь этого, мы бы не смогли синхронизировать
     представление данных с самими данными
     */
    private static ObservableList<NewClient> tableData = FXCollections.observableArrayList();

    public static ObservableList<NewClient> getTableData() {
        return tableData;
    }

    public static void setTableData(ObservableList<NewClient> tableData) {
        ListOfAllClientsController_2.tableData = tableData;
    }

    /*
    Для того, чтобы получить доступ к таблице и меткам представления, мы определим некоторые переменные.
    Эти переменные и некоторые методы имеют специальную аннотацию @FXML.
    Она необходима для того, чтобы fxml-файл имел доступ к приватным полям и методам.
     */
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
    private Button deleteClient;

    @FXML
    private Button addClient;

    @FXML
    private Button back;

    @FXML
    void initialize() {
        // тени, появляющиеся при наведении курсора на кнопки
        StarterWindowController_1.shadow(addClient);
        StarterWindowController_1.shadow(deleteClient);
        StarterWindowController_1.shadow(back);

        // кнопка"назад"
        back.setOnAction((ActionEvent event) -> {
            back.getScene().getWindow().hide();
            // создаём обЪект класса FXMLLoader
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

            // сохраняем наши значения в файл ("перезаписываем" файл)
            saveToFile(tableData);
        });


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


        /* The setOnEditCommit method processes editing and assigns the updated value to the corresponding
        table cell.(обрабатываем изменение полей таблички)
         */
        fioOfTheClient.setOnEditCommit(event ->
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setFio(event.getNewValue()));
        agreementNumber.setOnEditCommit(event ->
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setAgreementNumber(event.getNewValue()));
        address.setOnEditCommit(event ->
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setHomeAddress(event.getNewValue()));
        ipAddress.setOnEditCommit(event ->
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setIpAddress(event.getNewValue()));


        // кнопка"удалить клиента"
        deleteClient.setOnAction((ActionEvent) -> {
            // удаляем выбранного клиента
            NewClient p = table.getSelectionModel().getSelectedItem();
            tableData.remove(p);
        });

        // загружаем данные из ObservableList в таблицу
        table.setItems(tableData);


        // кнопка "добавить клиента"
        addClient.setOnAction((ActionEvent event) -> {
            // создаём stage для новых Scene
            Stage stage = new Stage();
            // выделяем память и в качестве параметра ничего не передаём
            FXMLLoader loader = new FXMLLoader();
            // закроем окно
            addClient.getScene().getWindow().hide();
            // теперь отображаем нужное нам окно
            // указываем место расположения нужного нам файла для дальнейшей его загрузки
            loader.setLocation(getClass().getResource("newClient_3.fxml"));
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

            // сохраняем наши значения в файл ("перезаписываем" файл)
            saveToFile(tableData);
        });

    }


    // записываем введённые значения в файл ClientsList.txt
    public static void saveToFile(ObservableList<NewClient> list) {
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
