package client;


import client.controllers.AuthController;
import client.controllers.ChatController;
import client.models.Network;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;


public class NetworkClient extends Application {

    public static final List<String> USERS_TEST_DATA = List.of("Борис_Николаевич", "Мартин_Некотов", "Гендальф_Серый");
    private Stage primaryStage;
    private Stage authStage;
    private Network network;

    private ChatController chatController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage; // primaryStage используется во всем классе, поэтому вывели его в классовую переменную

        network = new Network(); //Создаем новый объект с логином и паролем и помещаем его в переменную
        if (!network.connect()) {
            showErrorMessage("Проблемы с соединением", "", "Ошибка подключения к серверу");
            return;
        }

        openAuthWindow(); //открой окно авторизации
        createMainChatWindow(); //Открытие основного окна
    }

    private void openAuthWindow() throws IOException { //Окно авторизации
        FXMLLoader loader = new FXMLLoader(); //Создаем новый лоадер
        loader.setLocation(NetworkClient.class.getResource("views/auth-view.fxml")); //Лоадер должен выглядеть, как созданная вьюха
        Parent root = loader.load(); //Создаем из лоудера окно
        authStage = new Stage(); //Создаем сцену

        authStage.setTitle("Авторизация");
        authStage.initModality(Modality.WINDOW_MODAL); //Авторизация - модальное окно
        authStage.initOwner(primaryStage); //К чему прикреплять это окно
        Scene scene = new Scene(root); // Создаем сцену для отображения в окну root
        authStage.setScene(scene); //Наш сцена = новой созданной сцене
        authStage.show(); //Показать это сцену
        //TODO
        PauseTransition pause = new PauseTransition(Duration.millis(120000)); //Подожди 120 секунд (основной поток при этом продолжается)
        pause.setOnFinished(event -> authStage.close()); //После 120 секунд окно закрой окно аутентификации
        pause.play(); //Начни выполнение паузы


        AuthController authController = loader.getController(); //получи объект AuthController из лоудера
        authController.setNetwork(network); // отправить в authController network созданный
        authController.setNetworkClient(this); // отправить NetworkClient


    }
    public void createMainChatWindow() throws java.io.IOException { //Основное окно (чат)
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(NetworkClient.class.getResource("views/chat-view.fxml"));

        Parent root = loader.load();

        primaryStage.setTitle("Messenger");
        primaryStage.setScene(new Scene(root, 600, 400));

        chatController = loader.getController();
        chatController.setNetwork(network);

        primaryStage.setOnCloseRequest(windowEvent -> network.close());
    }
    public static void showErrorMessage(String title, String message, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void openMainChatWindow() { //Создай метод, закрывающий окно авторизации и открывающий окно чата (главное окно)
        authStage.close();
        primaryStage.show();

        primaryStage.setTitle(network.getUsername()); //При открытии задает титл = юзернейму юзера
        primaryStage.setAlwaysOnTop(true); // При передаче значения true окно будет всегда располагаться поверх других окон
        chatController.setLabel(network.getUsername());//Поменять текст в Label usernameTitle
        network.waitMessage(chatController); //Ожидание сообщений
    }

    public void end_AutWindow() {
        authStage.close();
    }
}