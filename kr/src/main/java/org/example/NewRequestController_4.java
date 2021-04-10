package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewRequestController_4 {

    // создание двусвязного списка
    public static LinkListR list = new LinkListR();
    // объект-итератор, ассоциированный с этим списком
    public static IteratorR iterator = new IteratorR(list);


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fio;
    public String fio1;

    @FXML
    private TextArea problem;
    public String problem1;

    @FXML
    private Button addNewRequest;

    @FXML
    private Rectangle rectangle;

    @FXML
    private Label label;

    @FXML
    private Button back;

    @FXML
    void initialize() {
        StarterWindowController_1.shadow(addNewRequest);
        StarterWindowController_1.shadow(back);

        // создаём stage для новых Scene
        Stage stage = new Stage();
        // создаём лбЪект класса FXMLLoader
        // выделяем память и в качестве параметра ничего не передаём
        FXMLLoader loader = new FXMLLoader();


        // кнопка "назад"
        back.setOnAction((ActionEvent event) -> {
            back.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("starterWindow_1.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();
        });

        // очищаем поле лейбла который выводит ошибки ввода
        label.setText("");
        // задаём рамке для ошибок "прозрачный" цвет
        rectangle.setStroke(Color.TRANSPARENT);
        // задаём прямоугольнику для ошибок "прозрачный" цвет
        rectangle.setFill(Color.TRANSPARENT);


        // обрабатываем нажатие кнопки "добавить заявку"
        addNewRequest.setOnAction((ActionEvent event) -> {
            // вводим переменую correct, для выявления ввода неверных символов
            boolean correctFio = true;
            boolean correctRequest = true;
            // создаём переммную notEmpty, для выявления наличия незаполненных полей
            boolean empty = false;
            // вводим переменую existingFio, для выявления ввода неверных символов
            boolean existingFio = false;




            // создаём список в который добавим нового клиента
            //LinkedList<NewRequest> newRequest = new LinkedList<>();




            // выполняем проверку на корректность вводимых данных
            // считываем данные с каждого поля
            fio1 = fio.getText().trim().toUpperCase();
            problem1 = problem.getText().trim();

            // проверяем пустое ли какое-нибудь поле
            if (fio1.isEmpty() || problem1.isEmpty()) {
                //  если да, то изменяем значение переменной Empty
                empty = true;
            }

            // делаем проверку на корректность фио
            for (int i = 0; i < fio1.length(); i++) {
                // кириллица-диапазон : 1040-1103
                // пробел с кодом 32
                if (!((int) fio1.charAt(i) >= 1040 && (int) fio1.charAt(i) <= 1103) && ((int) fio1.charAt(i) != 32) ) {
                    //  изменяем значение флаговой переменной если введён подходящий символ
                    correctFio = false;
                    break;
                }
            }

            // делаем проверку на отсутствие символа "|"
            for (int i = 0; i < problem1.length(); i++) {
                // "|" с кодом 124
                if ((int) problem1.charAt(i) == 124) {
                    //  изменяем значение флаговой переменной если введён неподходящий символ
                    correctRequest = false;
                    break;
                }
            }

            // автоматически вводится дата в поле date при добавлении заявки
            Date now = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat tf = new SimpleDateFormat("HH:mm");
            String dateString = df.format(now);
            String timeString = tf.format(now);

            // делаем проверку имеется ли фио клиента в списке клиентов
            // считываем каждую новую строку в массив, а потом проверяем эл-ты
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
                if (fioOfTheClient1.equals(fio1)) {
                    existingFio = true;
                    break;
                }
            }


            // записываем нового клиента только если все поля заполнены и притом правильно
            if (!empty && correctFio && existingFio && correctRequest) {
                // задаём рамке для ошибок  цвет
                rectangle.setStroke(Color.MEDIUMSEAGREEN);
                // задаём прямоугольнику для ошибок цвет
                rectangle.setFill(Color.MEDIUMAQUAMARINE);

                // записываем новый элемент в список
                list.addLast(new NewRequest( fio1, problem1,  dateString, timeString, "0%"));
                // записываем введённые значения в файл (добавляем новый эл-т)
                saveToFile(list);
                // очищаем поля
                fio.clear();
                problem.clear();

                // если поля верно были заполнены, очищаем поле лейбла
                label.setText("");
                label.setTextFill(Color.DARKGREEN);
                // выводим в пустой лейбел текст
                label.setText("Заявка была принята, она будет рассмотрена в ближайшее время  ");
            }else {
                // задаём рамке для ошибок "красный" цвет
                rectangle.setStroke(Color.RED);
                // задаём прямоугольнику для ошибок "красный" цвет
                rectangle.setFill(Color.LIGHTPINK);

                // иначе, если поля неверно были заполнены, очищаем поле лейбла
                label.setText("");
                // выводим в пустой лейбел текст об ошибке
                label.setText("Пожалуйста проверьте корректность введённых данных, также проверьте все ли поля заполнены ");
            if (!existingFio){
                // иначе, если поля fio нет в списке, очищаем поле лейбла
                label.setText("");
                // выводим в пустой лейбел текст об ошибке
                label.setText( fio1 + " не является клиентом данной сотовой сети ");
            }
            }

            // теперь очистим список чтобы при записи в файл нового клиента,не записывались сразу все имеющиеся в списке
            list.remove(0);
        });


    }

    // записываем введённые значения в файл2
    public static void saveToFile(LinkListR list) {
        FileWriter fw;
        try {
            fw = new FileWriter("RequestsList.txt", true);
            StringBuilder sb = new StringBuilder();
            int N = list.size();
            for (int i = -1; i < N - 1; i++) {
                String Fio = list.get(i + 1).getFio();
                String problem = list.get(i + 1).getProblem();
                String date = list.get(i + 1).getDate();
                String time = list.get(i + 1).getTime();
                sb.append(Fio);sb.append("|");
                sb.append(problem);sb.append("|");
                sb.append(date);sb.append("|");
                sb.append(time);sb.append("|");
                sb.append("0%");sb.append("|");
                sb.append("\n");
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }

}
