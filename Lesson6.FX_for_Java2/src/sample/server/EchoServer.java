package sample.server;

import sample.client.EchoClient;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

    private static final int SERVER_PORT = 8189; // Используемый порт

    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) { //Сокет ( точка соединения со стороны сервера)
            System.out.println("Ожидание подключения...");
            Socket clientSocket = serverSocket.accept(); //Ожидает какого-то подключения к сокету сервера. Пока не выполнится - дальше не идет
            System.out.println("Соединение установлено!");

            DataInputStream in = new DataInputStream(clientSocket.getInputStream()); //Импортирование сокета в in
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream()); //Отдача сокета в clientSocket

            Thread threadInOut = new Thread(() -> {while (true) {
                try {
                    String message = in.readUTF(); //Бесконечно читает сообщение пользователя (ждет)
                System.out.println("Сообщение пользователя: " + message);

                if (message.equals("/exit")) { //В случаи, если он читая найдет /exit
                    break; //То закроет цикл
                }

                    out.writeUTF("Я: " + message); //Отправить сообщение обратно, в скобках ранее стояло message.toUpperCase(), что переобразует текст в большие буквы

                } catch (IOException e) {
                    e.printStackTrace();
                    EchoClient.showErrorMessage("Ошибка сервера","", "Ошибка при отправке на(с) сервер(а)");
                }
            }
            });

            Thread threadAdmMessage = new Thread(() -> {

                while (true) {
                    System.out.println("Введите текст:");
                    Scanner scanner = new Scanner(System.in);
                    String admmessage = scanner.nextLine();
                    try {
                        out.writeUTF("Администратор: " + admmessage.toUpperCase());
                    } catch (IOException e) {
                        e.printStackTrace();
                        EchoClient.showErrorMessage("Ошибка сервера", "", "Ошибка при отправке на(с) сервер(а)");
                    }
                }
            });

            threadInOut.start();
            threadAdmMessage.start();
            threadInOut.join();
            threadAdmMessage.join();


            System.out.println("Сервер остановлен");
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Порт уже занят");
        }
        catch (InterruptedException e){
            e.printStackTrace();
            System.out.println("Невозможно ждать, пока потоки потоки остановятся");
        }


    }
}
