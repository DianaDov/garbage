package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChartsForAllRequestsController_10 {

    private static int counter = 0;

    // поля для выбора даты
    public static LocalDate ourPeriod1;
    public static LocalDate ourPeriod2;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AreaChart<?, ?> areaChart;
    // "ось категорий"
    @FXML
    private CategoryAxis x;
    // ось NumberAxis вдоль которой каждое значение представляет числовое значение
    @FXML
    private NumberAxis y;

    @FXML
    private Button back;

    @FXML
    private DatePicker dp1;

    @FXML
    private DatePicker dp2;

    @FXML
    private Button apply;


    @FXML
    void initialize() {
        StarterWindowController_1.shadow(back);
        StarterWindowController_1.shadow(apply);
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
        });

        // обрабатываем выбор даты
        dp1.setOnAction((ActionEvent) -> {
            ourPeriod1 = dp1.getValue();
        });
        dp2.setOnAction((ActionEvent) -> {
            ourPeriod2 = dp2.getValue();
        });




        XYChart.Series series = new XYChart.Series<>();
        // кнопка "применить"
        apply.setOnAction((ActionEvent event) -> {
            // переменная для проверки, что первая дата раньше последней
            boolean correctDates = false;
            // каждый раз когда применяем(нажимаем клавишу"применить") увеличиваем счётчик
            // чтобы потом очистить график для нвого диапазона
            counter++;
            if(counter>=2){
                areaChart.getData().remove(series);
                series.getData().clear();
            }
            // преобразуем в String крайние даты диапазона, взятые из datePicker-ов
            ZoneId defaultZoneId = ZoneId.systemDefault();
            // преобразуем в Date
            Date sDate = Date.from(ChartsForAllRequestsController_10.ourPeriod1.atStartOfDay(defaultZoneId).toInstant());
            Date eDate = Date.from(ChartsForAllRequestsController_10.ourPeriod2.atStartOfDay(defaultZoneId).toInstant());


            // проверяем, что первая дата раньше последней
            if(sDate.compareTo(eDate) <= 0){
                correctDates = true;
            }

            // если поля диапазона дат заполнены, только тогда можем отображать данные
            // и если первая дата раньше последней
            if (dp1.getValue() != null && dp2.getValue() != null && correctDates ) {

                // проходим по списку дат в заданном промежутке,
                // запоминаем каждую дату промежутка и приравниваем её к каждой дате списка заявок
                // и если в данный день есть заявки добавляем их на график, если нет, то дань добавляем с пустым значением

                ObservableList<String> getDatesBetween = getDatesBetweenString(sDate, eDate);

                // цикл для добавления дат
                for (int i = 0; i < getDatesBetween.size(); i++) {
                    // берём дату из промежутка и ищем такую же дату из списка
                    String current = getDatesBetween.get(i);
                    // для счётчика кол-ва заявок в определённый день
                    int counter = 0;
                    boolean found = false;

                    // цикл для добавления элементов

                    // считываем каждую новую строку в массив, а потом добавляем эл-ты в список
                    List<String> clientList = new ArrayList<>();
                    try (Stream<String> stream = Files.lines(Paths.get("RequestsList.txt"))) {
                        // convert it into a List
                        clientList = stream.collect(Collectors.toList());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // цикл для добавления элементов
                    for (int j = 1; j < clientList.size(); j++) {

                        String[] separated = clientList.get(j).split("\\|");

                        String dateString = separated[2].trim();

                        if (current.equals(dateString)) {
                            counter++;
                            // если дата находится в нужном нам промежутке то добавляем эл-т
                            // и соответственно значение к этой дате уже добавлено
                            found = true;
                        }
                    }

                    // если значение текущей дате current не присвоено,то просто доавляе её со значением "0"
                    if (!found) {
                        series.getData().add(new XYChart.Data<>(current, 0));
                    }else series.getData().add(new XYChart.Data<>(current, counter));
                }

                series.setName("поступившие заявки");

            }
            // проверяем, что первая дата раньше последней
            if(!correctDates){
                areaChart.getData().remove(series);
                series.getData().clear();
            }else {
                // добавляем на график
                areaChart.getData().addAll(series);
            }
        });

    }

    /*
    Получение всех дат между двумя датами
    Одним из способов его вычисления является использование экземпляра Calendar ,
    выполнение цикла и добавление 1 дня в каждой итерации с использованием метода add и единицы
    поля Calendar.Date, когда достигается конечная дата.
     */
    public static ObservableList<String> getDatesBetweenString(
            Date startDate, Date endDate) {
        ObservableList<String> datesInRange = FXCollections.observableArrayList();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(sdf.format(result));
            calendar.add(Calendar.DATE, 1);
        }
        // и ещё одну конечную добавим
        Date result = calendar.getTime();
        datesInRange.add(sdf.format(result));
        calendar.add(Calendar.DATE, 1);
        return datesInRange;
    }


}
