package sample.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network { //Отвечает за связь с сервером и клиентом

    private static final int SERVER_PORT = 8189; // Серверный порт
    private static final String SERVER_HOST = "localhost"; //Имя сервера

    private final int port;
    private final String host;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;

    public Network() { //Конструкток, который отдает серверный хост и порт
        this(SERVER_HOST, SERVER_PORT);
    }

    public Network(String serverHost, int serverPort) { //Конструктор, который отдает хост и порт в классовые переменные
        this.host = serverHost;
        this.port = serverPort;
    }

    public boolean connect() {
        try {
            socket = new Socket(host, port); //Создание сокета для клиента
            in = new DataInputStream(socket.getInputStream()); //Поток для принятия данных от сервера, объект создается в EchoClient
            out = new DataOutputStream(socket.getOutputStream()); //Поток для отдачи данных серверу
            return true;
        } catch (IOException e) {
            System.out.println("Соединение не было установлено");
            e.printStackTrace();
            return false;
        }


    }

    public void close() { //Закрытие сокета
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public DataInputStream getIn() { //поток нельзя менять, его можно читать, чтобы ссылаться на него при отправке или принятии
        return in;
    }

    public DataOutputStream getOut() { //поток нельзя менять, его можно читать, чтобы ссылаться на него при отправке или принятии
        return out;
    }

    public void waitMessage(ViewController viewController) {
        Thread thread = new Thread(() -> { //добавление в новый поток(процесс) цикл с бесконечным чтением по пототоку in текста от EchoServer, если он появится то в appendMessage, класса viewController отправить сообщение
            try {
                while (true) {
                    String message = in.readUTF();
                    viewController.appendMessage(message); //Добавление во входящий viewController из EchoClient сообщения
                }

            }
            catch (IOException e) {
                e.printStackTrace();
                EchoClient.showErrorMessage("Ошибка подключения", "", e.getMessage());
            }
        });
        thread.setDaemon(true); //Поток (процесс) будет фоновым и при закрытии приложения автоматически останавливается (благодаря setDaemon)
        thread.start(); //Запуск потока(процесса) thread
    }
}
