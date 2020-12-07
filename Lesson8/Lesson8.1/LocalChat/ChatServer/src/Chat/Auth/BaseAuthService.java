package Chat.Auth;

import Chat.User;

import java.util.List;

public class BaseAuthService implements AuthService {

    private static final List<User> client = List.of( //Новый лист, отдающий ArrayList и принимает объекты User с его конструктором
            new User("user1", "1111", "Инокентий_Петров"),
            new User("user2", "2222", "Владимир_Иванов"),
            new User("user3", "3333", "Иван_Смирнов")
    );

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public String getUserNameAndPasswForLogin(String login, String password) {
        for (User user : client) { //пробегаем по каждому объекту в листе List<User> client

            if(user.getLogin().equals(login) && user.getPassword().equals(password)){ //Если логин и пароль одного из объектов юзер соответствует внесенному логину и паролю (.equals() -настроен в самом классе User, как и геттеры)
                return user.getUsername(); //Вернем клиентский юзернейм
            }
        }
        return null;
    }

    @Override
    public void close() {
        System.out.println("Сервис аутентификации завершен");
    }

//    //TODO
//    public static List<User> getClient() {
//        return client;
//    }
}
