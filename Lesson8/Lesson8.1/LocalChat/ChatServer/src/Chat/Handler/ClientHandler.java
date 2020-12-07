package Chat.Handler;

import Chat.Auth.AuthService;
import Chat.MyServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private static final String auth_cmd_prefix = "/auth"; // Префикс аутентификации
    private static final String authOk_cmd_prefix = "/authok"; // Префикс успешной аутентификации
    private static final String authErr_cmd_prefix = "/autherr"; //Префикс ошибки в аутентификации
    private static final String private_msg_prefix = "/w"; //Префикс приватного сообщения
    private static final String end_cmd = "/end"; // Префикс для остановки сервера
    private static final String client_msg_prefix = "/clientMsg"; //Префикс клиентского сообщения
    private static final String server_msg_prefix = "/serverMsg"; //Префикс серверного сообщения

    private final MyServer myServer;
    private final Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;

    private String username;

    public ClientHandler(MyServer myServer, Socket clientSocket) {
        this.myServer = myServer;
        this.clientSocket = clientSocket;
    }

    public void handle() throws IOException {
        // создаем инпут/аутпут стрим поток для clientSocket
        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());

        new Thread(() -> {
            try {

                authentication(); //делаем аутентификацию по методу, а потом позволяем ему уже писать и читать сообщения

                readMessage(); // читаем сообщения

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ошибка принятия аутетификации или ожидания и чтения сообщений юзером");
            }



        }).start(); //Сразу же его начинаем!!!

    }

    private void authentication() throws IOException {
        String message = in.readUTF(); //Ждет, пока пользователь запишет данные (логин и пароль) в определенное окно, с этого окна ловим данные в message

        while (true){ //Безконечное колличество попыток авторизации
            if (message.startsWith(auth_cmd_prefix)){   //если данные начинаются с /auth, то
                String[] parts = message.split("\\s+", 3);// Распарсиваем (разделяем) присланное сообщение на 3 части через пробел(ы), (если один пробел, то поставили бы \\s)
                // parts[0] = префикс
                // parts[1] = логину
                // parts[2] = паролю
                String login = parts[1];
                String password = parts[2];

                AuthService authService = myServer.getAuthService(); //Обращение к сервису AuthService со значением его, после изменений класса MyServer
                //обращание к конструктору String в authService с найденным логином и паролем и возвратом юзернэйма в стринге
                username = authService.getUserNameAndPasswForLogin(login, password);
                if (username != null){
                    if(myServer.isUserNameBusy(username)){ // никнейм не занят
                        out.writeUTF(String.format("%s %s", authErr_cmd_prefix, "Логин уже авторизирован"));
                    }

                    out.writeUTF(String.format("%s %s", authOk_cmd_prefix, username));// отправить на клиент никнейм с успешной авторизацией (отправляется: "/authok" + username), а "%s %s" показывает расположение принимаемых переменных, относительно друг к друга

                    myServer.broadCastMessage(String.format(">>> %s подключился к чату",username), this, true, false);// оповезение пользователей о подключении юзера, оповещение кроме самошо юзера

                    myServer.subscribe(this);// Зарегистрировать для отправки/принятия сообщений клиента(у) из метода в myServer отдавая ему действующий ClientHandler
                    break;
                }
                else {
                    out.writeUTF(String.format("%s %s", authErr_cmd_prefix, "Логин или пароль не соответствуют действительности"));
                }
            }
            else {
                out.writeUTF(String.format("%s %s", authErr_cmd_prefix, "Ошибка авторизации"));
            }
        }
    }

    private void readMessage() throws IOException {
        while (true){
            String message = in.readUTF();
            System.out.println("message | " + username + ": " + message);
            if(message.startsWith(end_cmd))
                return;
            else if (message.startsWith(private_msg_prefix)){
                //TODO
                myServer.broadCastMessage(message, this, false, true);


            }
            else
                myServer.broadCastMessage(message, this, false, false);
        }
    }

    public String getUsername() {
        return username;
    }

    public void sendMessage(String sender, String message) throws IOException { //метод для отправки сообщения с именем - кто отправил сообщение (username) и какое сообщение
        if (sender == null) {
            out.writeUTF(String.format("%s %s",server_msg_prefix, message)); // если никнейм пустой, то отправить сообщение от сервера

        }
        else {
            out.writeUTF(String.format("%s %s %s",client_msg_prefix, sender, message));
        }
    }
//
//    public void sendPrivMessage(String sender, String privatmsg) throws IOException {
//
//        out.writeUTF(String.format("%s %s %s", private_msg_prefix, sender, privatmsg));
//    }
}
