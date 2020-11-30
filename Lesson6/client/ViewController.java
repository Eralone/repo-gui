package sample.client;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ViewController {

    @FXML
    public ListView<String> usersList;

    @FXML
    private Button sendButton;


    @FXML
    private TextArea chatHistory;
    @FXML
    private TextField textField;

    private Network network;

    public void setNetwork(Network network) { //передача из EchoClient (35 строки) в классовый объект Network объект Network
        this.network = network;
    }

    @FXML
    public void initialize() {
        usersList.setItems(FXCollections.observableArrayList(sample.client.EchoClient.USERS_TEST_DATA));
        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ViewController.this.sendMessage();
            }
        });
        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ViewController.this.sendMessage();
            }
        });
    }


    private void sendMessage() { //Запускается нажатием на кнопку или Enter
        String message = textField.getText(); //Принятие текста из поля
//        appendMessage(message); //Добавление текста в метод appendMessage, не нужен, т.к. мы отправляем сообщение из EchoServer
        textField.clear(); //Очищение окна

        try {
            network.getOut().writeUTF(message); //отправка по Getter (метод, отдающий информацию по потоку out из Network) сообщения в EchoServer

        } catch (IOException e) {
            e.printStackTrace();
            EchoClient.showErrorMessage("Ошибка подключения", "Ошибка при отправке сообщения", e.getMessage()); // Выводит в EchoClient метод, где вызывается окно с ошибкой
        }

    }

    public void appendMessage(String message) { //Принимается из Network
        chatHistory.appendText(message); //Добавление текста в окно TextArea с id окна - chatHistory
        chatHistory.appendText(System.lineSeparator()); //Добавление строки-переноса в окно TextArea  с id окна - chatHistory
    }

}