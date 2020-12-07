package client.controllers;

import client.NetworkClient;
import client.models.Network;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;


public class AuthController {

    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;

    private Network network;
    private NetworkClient networkCliet;


    @FXML
    public void checkAuth() { //метод, который запускается при нажатии кнопки в окне с заданным loginField и passwordField
        String login = loginField.getText();
        String password = passwordField.getText();

        if (login.isBlank() || password.isBlank()) { //Если поле логина или поле пароля пустые, то выкидывай окно с ошибкой
            NetworkClient.showErrorMessage("Ошибка авторизации", "Ошибка ввода", "Поля не должны быть пустыми");
            return;
        }

        String authErrorMessage = network.sendAuthCommand(login, password); //Метод нетворк, который отправляет на сервер аутентификацию и возвращает определенное значение стринг
        if (authErrorMessage != null) { //Если он отправил не null, то открывается модальная вьюха - ошибка (алерт окно)
            NetworkClient.showErrorMessage("Ошибка авторизации", "Некорректный логин или пароль", authErrorMessage);
        } else {
            networkCliet.openMainChatWindow(); //Если нет - открой чат
        }

    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void setNetworkClient(NetworkClient networkCliet) {
        this.networkCliet = networkCliet;
    }
}
