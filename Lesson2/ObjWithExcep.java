package Lesson2;

import java.util.Random;


public class ObjWithExcep {

    static int col;

    ObjWithExcep(String[][] obj, int[][] objint) throws MyArrayDataException, MyArraySizeException{ //Создаем конструктор

//        stringrandom(obj);
        TheExceptCondit(obj);
        FromTextToNumber(obj,objint);
    }


    void TheExceptCondit(String[][] anyobj) throws MyArraySizeException { //Пишем условия для исключения, проверяем объект
        for (int i = 0; i < anyobj.length; i++) {
            if (anyobj.length != 4 || anyobj[i].length != 4) {
                throw new MyArraySizeException("Много строк (или столбцов) не осилил, проверяй, почему вышел obj ["
                        + anyobj.length + "] [" + anyobj[i].length + "]");
            }
        }
    }

    static void stringrandom(String[][] objadd) { // Вносим рандомные значения (метод инициализированный отдельно в main
        Random random = new Random();
        for (int i = 0; i < objadd.length; i++) {
            for (int j = 0; j < objadd[i].length; j++) {
                if (i == 2 && j == 3)
                    objadd[i][j] = "Hello, Exception";
                else
                objadd[i][j] = String.valueOf(random.nextInt(201));
            }
        }
    }

    void FromTextToNumber(String[][] objstr, int[][] objint) throws MyArrayDataException { // Переводим данные из объекта String в объект int и отсылаем сумму массива

        int col = 0;
        for (int i = 0; i < objstr.length; i++) {
            for (int j = 0; j < objstr[i].length; j++) {
                try {
                    objint[i][j] = Integer.parseInt(objstr[i][j]);
                    col += objint[i][j];
                }catch (NumberFormatException e){
                    throw new MyArrayDataException(i,j);
                }
            }
        }
        ObjWithExcep.col = col;
    }


}



