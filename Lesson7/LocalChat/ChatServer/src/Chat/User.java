package Chat;

import java.util.Objects;

public class User {

    private final String login;
    private final String password;
    private final String username;

    public User(String login, String password, String username) { //Создание нового хранилища (объекта) для логина, пароля и никнейма
        this.login = login;
        this.password = password;
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) { // Поисковик, принимающий объект
        if (this == o) return true; // Если данный юзер с его логином, паролем и юзернеймом = объекту о, то верни правда
        if (o == null || getClass() != o.getClass()) return false; //Если объект о = null или его тип не равен типу объекта класса (User)
        User user = (User) o; // То переведи принудительно объект о к типу User и помести ее в переменную User
        return Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(username, user.username); //верни булево выражения с проверкой на логин, пароль и никнейм
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, username);
    } //Добавление к объекту хэш-кода с логином, паролеи и никнеймом (чтобы избежать "столкновений" хэш-кода)
}
