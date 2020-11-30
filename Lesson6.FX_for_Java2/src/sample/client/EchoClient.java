package sample.client;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.List;


public class EchoClient extends Application {

    public static final List<String> USERS_TEST_DATA = List.of("Пользователь первый", "Пользователь второй", "Пользователь третий"); //Включить в List USERS_TEST_DATA данный Стринговый текст

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EchoClient.class.getResource("view.fxml"));

        Parent root = loader.load();

        primaryStage.setTitle("Messenger");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        Network network = new Network(); //Создание объекта по контруктору, передающему Сервер_хост и Сервер_порт
        if(!network.connect()) {
           showErrorMessage("Проблемы с соединением", "", "Ошибка подключения к серверу");
        }

        ViewController viewController = loader.getController(); //Контролер, который работает с окном лоудер
        viewController.setNetwork(network); // Передача в метод setNetwork значения созданного на 29 строке

        network.waitMessage(viewController); //метод в классе Network, который читает текст от сервера и добавляет его в окно

        primaryStage.setOnCloseRequest(windowEvent -> network.close()); //primaryStage -наша "сцена"; setOnCloseRequest - в случаи, если окно закроется; windowEvent -функциональный интерфейс; network.close() - закрытие сокета данного клиента из метода класса Network


    }

    public static void showErrorMessage(String title, String message, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR); //Создание окна Ошибки
        alert.setTitle(title); //Шапка окна
        alert.setHeaderText(message); //Заголовок окна
        alert.setContentText(errorMessage); //Текст окна
        alert.showAndWait(); //Показывай его и блокирую дальнейшие использование остальных окон
    }

    public static void main(String[] args) {
        launch(args);
    }
}