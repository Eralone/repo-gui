package Lesson2;

class MyArraySizeException extends Exception {
     public MyArraySizeException(String text) {
        super(text);
        System.out.println("Проблема в том, что нельзя портить объект Малевича, добавлением в массив объекта.");
    }
}
