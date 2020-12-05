import Chat.MyServer;

import java.io.IOException;

public class ServerApp {

    private static final int default_port = 8189; //порт для сревера

    public static void main(String[] args) {

        int port = default_port;

        if (args.length != 0){
            port = Integer.parseInt(args[0]); //Парсить 1ое число, которое принимает метод майн и задаем этим значение port
        }

        try {
            new MyServer(port).start(); //Создание нового юзера и сокета для юзера с указанным портом из main
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка");
            System.exit(1); //Выход из процесса
        }

    }
}
