package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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

public class NewClientController_3 {

    // создание двусвязного списка (объекта класса LinkListC)
    // однако поле list содержит не объект,а только ссылку на него.
    // Объект находится где-то в другом месте памяти
    public static LinkListC list = new LinkListC();

    // объект-итератор, ассоциированный с этим списком
    public static IteratorC iterator = new IteratorC(list);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fio;
    public String fio1;

    @FXML
    private TextField IPAddress;
    public String IPAddress1;

    @FXML
    private TextField homeAddress;
    public String homeAddress1;

    @FXML
    private TextField number0fTheAgreement;
    public String number0fTheAgreement1;

    @FXML
    private Button addNewClient;

    @FXML
    private Rectangle rectangle;

    @FXML
    private Label label;

    @FXML
    private Button back;

    @FXML
    void initialize() {
        StarterWindowController_1.shadow(addNewClient);
        StarterWindowController_1.shadow(back);


        // создаём stage для новых Scene
        Stage stage = new Stage();
        // создаём лбЪект класса FXMLLoader
        // выделяем память и в качестве параметра ничего не передаём
        FXMLLoader loader = new FXMLLoader();


        // кнопка "назад"
        back.setOnAction((ActionEvent event) -> {
            back.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("listOfAllClients_2.fxml"));
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


        // обрабатываем нажатие кнопки "добавить клиента"
        addNewClient.setOnAction((ActionEvent event) -> {


            // вводим переммные correct, для выявления ввода неверных символов
            boolean correctIP = true;
            boolean correctNumber0fTheAgreement = true;
            boolean correctFio = true;
            boolean correctAddress = true;
            // создаём переммную notEmpty, для выявления наличия незаполненных полей
            boolean empty = false;
            // переменная для проверки уже существующего клиента
            boolean repeat = false;


            // создаём список в который добавим нового клиента
            //LinkedList<NewClient> newClient = new LinkedList<>();


            // выполняем проверку на корректность вводимых данных
            // считываем данные с каждого поля
            number0fTheAgreement1 = number0fTheAgreement.getText().trim();
            IPAddress1 = IPAddress.getText().trim();
            fio1 = fio.getText().trim().toUpperCase();
            homeAddress1 = homeAddress.getText().trim();

            // проверяем пустое ли какое-нибудь поле
            if (fio1.isEmpty() || IPAddress1.isEmpty()
                    || homeAddress1.isEmpty() || number0fTheAgreement1.isEmpty()) {
                //  если да, то изменяем значение переменной Empty
                empty = true;
            }

            // IPAddress должен быть только целыми неотрицательными числами, допускается точка в записи
            for (int i = 0; i < IPAddress1.length(); i++) {
                // числа в диапазоне 48-57, код точки 46
                if (!((int) IPAddress1.charAt(i) >= 48 && (int) IPAddress1.charAt(i) <= 57)
                        && ((int) IPAddress1.charAt(i) != 46)) {
                    //  изменяем значение флаговой переменной если в поле кол-ва товара были ввведены символы кроме цифр
                    correctIP = false;
                    break;
                }
            }

            // number0fTheAgreement должен быть только целыми неотрицательными числами
            for (int i = 0; i < number0fTheAgreement1.length(); i++) {
                // числа в диапазоне 48-57
                if (!((int) number0fTheAgreement1.charAt(i) >= 48 && (int) number0fTheAgreement1.charAt(i) <= 57)) {
                    //  изменяем значение флаговой переменной если в поле кол-ва товара были ввведены символы кроме цифр
                    correctNumber0fTheAgreement = false;
                    break;
                }
            }


            // делаем проверку на корректность фио
            for (int i = 0; i < fio1.length(); i++) {
                // кириллица-диапазон : 1040-1103
                // пробел с кодом 32
                // "-" с кодом 45
                if (!((int) fio1.charAt(i) >= 1040 && (int) fio1.charAt(i) <= 1103) && ((int) fio1.charAt(i) != 32)
                        && ((int) fio1.charAt(i) != 45)) {
                    //  изменяем значение флаговой переменной если введён подходящий символ
                    correctFio = false;
                    break;
                }
            }


            // делаем проверку на корректность адреса
            for (int i = 0; i < homeAddress1.length(); i++) {
                // кириллица-диапазон : 1040-1103
                // пробел с кодом 32
                // точка с кодом 46
                // запятая с кодом 44
                // "-" с кодом 45
                // числа в диапазоне 48-57
                if (!((int) homeAddress1.charAt(i) >= 1040 && (int) homeAddress1.charAt(i) <= 1103) && ((int) homeAddress1.charAt(i) != 32)
                        && ((int) homeAddress1.charAt(i) != 46) && (int) homeAddress1.charAt(i) != 44 && ((int) homeAddress1.charAt(i) != 45)
                        && !((int) number0fTheAgreement1.charAt(i) >= 48 && (int) number0fTheAgreement1.charAt(i) <= 57)) {
                    //  изменяем значение флаговой переменной если введён подходящий символ
                    correctAddress = false;
                    break;
                }
            }

            // проверяем есть ли уже такой клиент в списке сот сети
            // считываем каждую новую строку в массив, а потом добавляем эл-ты в список
            List<String> listString = new ArrayList<>();
            try (Stream<String> stream = Files.lines(Paths.get("ClientsList.txt"))) {
                // convert it into a List
                listString = stream.collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 1; i < listString.size(); i++) {
                String[] separated = listString.get(i).split("\\|");
                String fioOfTheClient1 = separated[0].trim();
                String agreementNumber1 = separated[1].trim();
                String address1 = separated[2].trim();
                String ipAddress1 = separated[3].trim();
                if (number0fTheAgreement1.equals(agreementNumber1)) {
                    // если такая завка уже есть то мы не добавялем эту заявку в список готовых
                    // а просто обновляем
                    repeat = true;
                    break;
                }
            }


            // проверяем правильность введённого ip адреса (ipv4)
            // записывается в виде 4-ёх десятичных чисел (от 0 до 255), разделённых точками
            validIP(IPAddress1);


            if (repeat) {
                // задаём рамке для ошибок "красный" цвет
                rectangle.setStroke(Color.RED);
                // задаём прямоугольнику для ошибок "красный" цвет
                rectangle.setFill(Color.LIGHTPINK);

                // иначе, если поля неверно были заполнены, очищаем поле лейбла
                label.setText("");
                // выводим в пустой лейбел текст об ошибке
                label.setText("Договор с данным номером уже был заключён ");

            } else {

                // записываем нового клиента только если все поля заполнены и притом правильно
                // а также, если такого клиента ещё нет в списке
                if (!empty && correctIP && correctFio && correctAddress && correctNumber0fTheAgreement && validIP(IPAddress1)) {
                    // задаём рамке для ошибок "прозрачный" цвет
                    rectangle.setStroke(Color.MEDIUMSEAGREEN);
                    // задаём прямоугольнику для ошибок "прозрачный" цвет
                    rectangle.setFill(Color.MEDIUMAQUAMARINE);

                    // записываем новый элемент в список для записи в файл
                    list.addLast(new NewClient(fio1, number0fTheAgreement1, homeAddress1, IPAddress1));
                    // записываем введённые значения в файл (добавляем новый эл-т)
                    saveToFile(list);

                    // очищаем поля
                    number0fTheAgreement.clear();
                    fio.clear();
                    homeAddress.clear();
                    IPAddress.clear();

                    // если поля верно были заполнены, очищаем поле лейбла
                    label.setText("");
                    label.setTextFill(Color.DARKGREEN);
                    // выводим в пустой лейбел текст
                    label.setText("Клиент был успешно добавлен ");
                } else {
                    // задаём рамке для ошибок "красный" цвет
                    rectangle.setStroke(Color.RED);
                    // задаём прямоугольнику для ошибок "красный" цвет
                    rectangle.setFill(Color.LIGHTPINK);

                    // иначе, если поля неверно были заполнены, очищаем поле лейбла
                    label.setText("");
                    label.setTextFill(Color.FIREBRICK);
                    // выводим в пустой лейбел текст об ошибке
                    label.setText("Пожалуйста проверьте корректность введённых данных, также проверьте все ли поля заполнены ");
                }
            }

            // теперь очистим список чтобы при записи в файл нового клиента,не записывались сразу все имеющиеся в списке
            list.remove(0);
        });


    }

    // записываем введённые значения в файл ClientsList.txt
    public static void saveToFile(LinkListC list) {
        FileWriter fw;
        try {
            fw = new FileWriter("ClientsList.txt", true);
            StringBuilder sb = new StringBuilder();
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


    // проверка валидности введённого ip
    public static boolean validIP(String ip) {
        try {
            if (ip == null || ip.isEmpty()) {
                return false;
            }

            String[] parts = ip.split("\\.");
            if (parts.length != 4) {
                return false;
            }

            for (String s : parts) {
                int i = Integer.parseInt(s);
                if ((i < 0) || (i > 255)) {
                    return false;
                }
            }
            if (ip.endsWith(".")) {
                return false;
            }

            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
