package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RowWord {

    private SimpleStringProperty word; //Оболочка Стринг
    private SimpleIntegerProperty count; //Оболочка Интеджер

    public RowWord(String word, int count) {
        this.word = new SimpleStringProperty(word);
        this.count = new SimpleIntegerProperty(count);
    }

    public String getWord() {
        return word.get();
    }

    public SimpleStringProperty wordProperty() {
        return word;
    }

    public void setWord(String word) {
        this.word.set(word);
    }

    public int getCount() {
        return count.get();
    }

    public SimpleIntegerProperty countProperty() {
        return count;
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public void incCount() {
        count.set(count.get() + 1); // Увеличение значения на 1 (через count +=1 не получится)
    }
}
