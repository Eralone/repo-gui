package sample;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class Controller {

    @FXML
    private TextField inputField; //Получаем текст

    @FXML
    private ListView<String> listView; //Отображаем текст здесь

    @FXML
    private TableView<RowWord> tableView;
    @FXML
    private TableColumn<RowWord, String> wordTableColumn; // Слова в таблице tableView
    @FXML
    private TableColumn<RowWord, Integer> countTableColumn; // Сумма слов в таблице tableView


    private final ObservableList<RowWord> frequencyByWord = FXCollections.observableArrayList( //то, что будет в tableView (несколько строк)
            new RowWord("a", 1),
            new RowWord("b", 2),
            new RowWord("c", 3)
    );

    private final ObservableList<String> wordList = FXCollections.observableArrayList("Привет", "Часы", "Новый год!"); //Начальный текст в listView


    @FXML
    public void initialize() { //Первый метод для выполнения, инициализация

        wordTableColumn.setCellValueFactory(new PropertyValueFactory<>("word")); //Вытаскивать из колонки объекта RowWord слова и вставлять в wordTableColumn
        countTableColumn.setCellValueFactory(new PropertyValueFactory<>("count")); //Вытаскивать из колонки объекта RowWord суммы и вставлять в countTableColumn

        listView.setItems(wordList); //Вставить текст ObservableList в listView
        tableView.setItems(frequencyByWord); //Вставить содержимое объекта RowWord в tableView

    }

    @FXML
    public void addWord() { // Добавить слово в список
        String word = inputField.getText(); // переменная word с текстом от TextField
        if (word.isBlank()) { //Если "бланк" пустой, то

            Alert alert = new Alert(Alert.AlertType.ERROR); //Вызови объект типа - Ошибка
            alert.setTitle("Input Error"); //С шапкой
            alert.setHeaderText("Ошибка ввода сообщения"); //Заголовком
            alert.setContentText("Нельзя вводить пустое сообщение"); //Текстом
            alert.showAndWait(); //Покажи это окно и заблокиру1 внутреннее
            return; //Конец метода

        } else {
            addWordToList(word); //Добавь в listView текст (слово)
            addWordToTable(word); //Добавь в tableView текст (слово)
        }

        inputField.clear(); //Очисти поле записи текста
    }

    private void addWordToTable(String word) {

        for (RowWord rowWord : frequencyByWord) { // Пройтись по каждому значению frequencyByWord (типа RowWord) и вставить содержимое в объекты RowWord
            if (word.equals(rowWord.getWord())) { //При просмотре объектов смотри в строку текст, и если она повторяется, то
                rowWord.incCount(); //увеличь сумму данного слова на 1 (incCount() - метод из RowWord)
                return; //Завершить метод
            }
        }

        frequencyByWord.add(new RowWord(word, 1)); //Если слова нет, то создай новый объект RowWord с заданным текстом и суммой 1
    }

    @FXML
    public void exit() {  //Если выходишь, выходи полностью из процесса Idea
        System.exit(1);
    }

    private void addWordToList(String word) {
        listView.getItems().add(word);
    } //Добавление текста из TextField в listview

    @FXML
    public void showAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Lesson 4");
        alert.setContentText("Приложение, созданное в рамках четвертого занятия!");
        alert.showAndWait();
    }

}
