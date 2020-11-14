package Lesson2;

class MyArrayDataException extends Exception {
    public static int rows;
    public static int colums;

    public MyArrayDataException(int rows, int columns) {
        System.out.println("Слушай, у тебя есть ошибка в строке " + rows + " столбце " + columns + ". Нельзя ее преобразовать в int. Я ее назначил равной 0");
        this.rows = rows;
        this.colums = columns;
    }

}
