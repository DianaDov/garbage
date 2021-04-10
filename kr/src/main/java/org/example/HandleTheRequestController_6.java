package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
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

public class HandleTheRequestController_6 extends ListOfAllRequestsController_5 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink hyperlink;

    @FXML
    private TextArea problem;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button back;

    @FXML
    void initialize() {
        StarterWindowController_1.shadow(back);

        // работаем с полем готовности (choiceBox)
        ObservableList<String> args = FXCollections.observableArrayList( "0%" ,"Выполнено",
                "Ожидает рассмотрения", " на 10%", " на 30%", " на 50%", " на 80%");
        choiceBox.setItems(args);

        // ссылка на список слиентов
        hyperlink.setText("");
        hyperlink.setOnAction((ActionEvent event) -> {
            back.getScene().getWindow();
            // создаём обЪект класса FXMLLoader
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("clientsHyperlink_8.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });


        // список, чтобы после записать этот элемент в файл с готовыми элементами
        LinkedList<NewRequest> newRequest = new LinkedList<>();
        // список для пеерзаписи элементов
        LinkedList<NewRequest> allRequests = new LinkedList<>();

        // считываем каждую новую строку в массив, а потом добавляем эл-ты в список
        List<String> StudentList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get("RequestsList.txt"))) {
            // convert it into a List
            StudentList = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < StudentList.size(); i++) {
            String[] separated = StudentList.get(i).split("\\|");
            String fio1 = separated[0].trim();
            String problem1 = separated[1].trim();
            String date = separated[2].trim();
            String time = separated[3].trim();
            String ready = separated[4].trim();

            // находим в текстовом файле нужного нам клиента и его заявку и соответсвенно заполняем поля на экране
            if (ourFio.equals(fio1) && ourComplain.equals(problem1)) {
                choiceBox.setValue(ready);
                // работаем с остальными полями
                problem.setText(problem1);
                hyperlink.setText(ourFio);

                // добавляем элемент в список, чтобы после записать этот элемент в файл с готовыми элементами
                newRequest.add(new NewRequest(fio1,  problem1,  date, time,  ready));
                // все остальные заявки, которые мы не обрабатываем в данный момент
            }else allRequests.add(new NewRequest(fio1,  problem1,  date, time,  ready));
        }

        //  перезаписали список2 без наших элементов
        saveToFile1(allRequests);
        //  а затем очистили
        allRequests.clear();

        // проверка, имеются ли повторяющиеся уже выполненные заявки
        // переменная для проверки повторяющихся заявок
        boolean repeat = false;
        // считываем каждую новую строку в массив, а потом добавляем эл-ты в список
        List<String> list = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get("ReadyRequests.txt"))) {
            // convert it into a List
            list = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < list.size(); i++) {
            String[] separated = list.get(i).split("\\|");
            String fio1 = separated[0].trim();
            String problem1 = separated[1].trim();
            String date = separated[2].trim();
            String time = separated[3].trim();
            String ready = separated[4].trim();

            // находим в текстовом файле нужного нам клиента и его заявку и соответсвенно заполняем поля на экране
            if (ourFio.equals(fio1) && ourComplain.equals(problem1)) {
                // если такая завка уже есть то мы не добавялем эту заявку в список готовых
                // а просто обновляем
                repeat = true;
                break;
            }
        }


        // кнопка"назад"
        boolean finalRepeat = repeat;
        back.setOnAction((ActionEvent event) -> {

            // .getSelectionModel().getSelectedItem() - возвращает текущий выбранный элемент
            if(choiceBox.getSelectionModel().getSelectedItem().equals("Выполнено") && !finalRepeat){
                // если заявка выполнена, то добавляем её в файл к другим готовым
                saveInTheReadyFile(newRequest);
            }
            // и добавляем изменённую заявку тоже и в список к невыполненным пока заявкам
            FileWriter fw;
            try {
                fw = new FileWriter("RequestsList.txt", true);
                String sb = newRequest.get(0).getFio() + "|" +
                        newRequest.get(0).getProblem() + "|" +
                        newRequest.get(0).getDate() + "|" +
                        newRequest.get(0).getTime() + "|" +
                        //choiceBox.getValue() + "|" + choiceBox.getSelectionModel().getSelectedItem()
                        choiceBox.getSelectionModel().getSelectedItem() + "|" +
                        "\n";
                fw.write(sb);
                fw.close();
            } catch (IOException e) {
                System.out.println("Файл не найден");
            }


            back.getScene().getWindow().hide();
            // создаём обЪект класса FXMLLoader
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
        });


    }

    // записываем введённые значения в файл2
    public static void saveInTheReadyFile(LinkedList<NewRequest> list) {
        FileWriter fw;
        try {
            fw = new FileWriter("ReadyRequests.txt", true);
            StringBuilder sb = new StringBuilder();
            int N = list.size();
            for (int i = -1; i < N - 1; i++) {
                String Fio = list.get(i + 1).getFio();
                String problem = list.get(i + 1).getProblem();
                String date = list.get(i + 1).getDate();
                String time = list.get(i + 1).getTime();
                String ready = list.get(i + 1).getTime();
                sb.append(Fio);sb.append("|");
                sb.append(problem);sb.append("|");
                sb.append(date);sb.append("|");
                sb.append(time);sb.append("|");
                sb.append("Выполнено");sb.append("|");
                // автоматически вводится текущая дата
                Date now = new Date();
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = df.format(now);
                sb.append(dateString);sb.append("|");
                sb.append("\n");
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }

    // записываем введённые значения в файл2
    public static void saveToFile1(LinkedList<NewRequest> list) {
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
