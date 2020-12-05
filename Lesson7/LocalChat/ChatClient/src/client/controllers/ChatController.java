package client.controllers;

import Chat.Auth.BaseAuthService;
import Chat.Handler.ClientHandler;
import Chat.MyServer;
import Chat.User;
import client.NetworkClient;
import client.models.Network;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class ChatController {

    @FXML
    public ListView<String> usersList;

    @FXML
    private Button sendButton;
    @FXML
    private TextArea chatHistory;
    @FXML
    private TextField textField;
    @FXML
    private Label usernameTitle;

    private Network network;

    public void setLabel(String usernameTitle) {
        this.usernameTitle.setText(usernameTitle);
    } //Поменять текст в Label usernameTitle

    public void setNetwork(Network network) {
        this.network = network;
    }

    @FXML
    public void initialize() {
        usersList.setItems(FXCollections.observableArrayList(NetworkClient.USERS_TEST_DATA));
        sendButton.setOnAction(event -> ChatController.this.sendMessage());
        textField.setOnAction(event -> ChatController.this.sendMessage());
    }

    private void sendMessage() { //Отправка сообщения юзером
        String message = textField.getText(); //Чтение сообщения с поля
        appendMessage("Я: " + message); //Передача его методу appendMessage
        textField.clear(); //Очистить поле для надписи у клиента

        try {
            network.sendMessage(message); //отправим сообщение методу network для отправки сообщения на сервер

        } catch (IOException e) {
            e.printStackTrace();
            NetworkClient.showErrorMessage("Ошибка подключения", "Ошибка при отправке сообщения", e.getMessage());
        }

    }

    public void appendMessage(String message) {
        String timestamp = DateFormat.getInstance().format(new Date());
        chatHistory.appendText(timestamp); // добавление в окно чтения сообщений - Время отправки
        chatHistory.appendText(System.lineSeparator()); //Пробел
        chatHistory.appendText(message); //добавление в окно чтения сообщений - Сообщение
        chatHistory.appendText(System.lineSeparator()); //Пробел
        chatHistory.appendText(System.lineSeparator()); //Пробел
    }

    public void setUsernameTitle(String username) {

    }
}