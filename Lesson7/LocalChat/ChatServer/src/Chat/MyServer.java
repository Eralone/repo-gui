package Chat;

import Chat.Auth.AuthService;
import Chat.Auth.BaseAuthService;
import Chat.Handler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

    private final ServerSocket serverSocket;
    private final AuthService authService;

    private final List<ClientHandler> client = new ArrayList<>(); //создание ArrayList из объектов ClientHandler с данными юзеров в сети


    public MyServer(int port) throws IOException { //Ошибку перекидываем на уровень выше
        this.serverSocket = new ServerSocket(port); //Конструктор для создания сокета с определенным портом и введением его в переменную класса
        this.authService = new BaseAuthService(); //Подключение метода аутификации, проверяющий по базе аутификации
    }


    public void start() throws IOException {

        System.out.println("Сервер запущен!");
        authService.start();

        try {
            while (true) {
                waitAndProcessNewClient(); //подключение юзера (сокета юзера) и создание его in и out потоков
            }
            }catch (IOException e){
            System.out.println("Ошибка создание нового подключения");
            e.printStackTrace();
            } finally {
            serverSocket.close(); // В последствии -все ражно закрываем сокет
        }
        }

    private void waitAndProcessNewClient() throws IOException {
        System.out.println("Ожидание пользователя...");
        Socket clientSocket = serverSocket.accept(); //Ожидание подключений от serverSocket ("ловец подключений" в serverSocket) с передачей его в clientSocket
        System.out.println("Клиент подключился!");
        processClientConnection(clientSocket); //Получение сокета, которого только что получили и помещаем в метод processClientConnektion
    }

    private void processClientConnection(Socket clientSocket) throws IOException {
        ClientHandler clientHandler = new ClientHandler(this, clientSocket); // Создаем нового хэндлера (с сокетом, аутификацией и прочего) по конструктору, он передает MyServer (с данным созданным сокетом) и clientSocket
        clientHandler.handle();//Организует логику подключения пользователя и его потоки
    }


    public AuthService getAuthService() {
        return authService;
    }

    public void subscribe(ClientHandler clientHandler) { //добавление ClientHandler (передающийся из его класса) - зарегистрирован и регистрация его для отправки писем
        client.add(clientHandler); // добавление в ArrayList значение clientHandler
    }

    public void unsubscribe(ClientHandler clientHandler) { //удаление ClientHandler (передающийся из его класса) - из зарегистрированых для отправки писем
        client.remove(clientHandler); // убрать из ArrayList значение данного clientHandler
    }
    public boolean isUserNameBusy(String username) { //При повторном подключении юзера (если он уже в сети), то он не подключится повторно
        for (ClientHandler clientHandler : client) {
            if(clientHandler.getUsername().equals(username)){ //Отдаем каждый Username ClientHandler через Getter и сверяем его с пришедшим username
                return true;
            }
        }
        return false;
    }

    public void broadCastMessage(String message, ClientHandler clientHandler, boolean isServerInfoMsg, boolean PrivMsg) throws IOException { //Сообщение о подключении в чат юзера (всех, кроме самого этого юзера)
        for (ClientHandler handler : client) {
            if (handler == clientHandler) {
                continue;
            }
            //TODO
            else {
                if (PrivMsg) {
                    String[] parts = message.split("\\s+", 3);
                    String foruser = parts[1];
                    String privatmsg = parts[2];
                    if (handler.getUsername().equals(foruser)) {
                        handler.sendMessage(clientHandler.getUsername(), privatmsg); //handler.sendPrivMessage(clientHandler.getUsername(), privatmsg);
                    }

                } else
                    handler.sendMessage(isServerInfoMsg ? null : clientHandler.getUsername(), message); //отправить сообщение от сервера. Если isServerInfoMsg = true то отправляем null, если false, то clientHandler.getUsername()
            }
        }
    }
    //TODO
    public List<ClientHandler> getClient() {
        return client;
    }
}

