package Chat.Auth;

public interface AuthService {

    void start();

    String getUserNameAndPasswForLogin (String login, String password); //Метод принимающий логин и пароль и сверяющий их со своей базой или базой наследника BaseAuthService

    void close();
}
