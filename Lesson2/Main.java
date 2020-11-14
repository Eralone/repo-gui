package Lesson2;

class Main {
    public static void main(String[] args) {
        String[][] objforexc = new String[4][4];
        String[][] objforexc1 = new String[4][4];
        String[][] objforexc2 = new String[5][4];

        int[][] objintforexc = new int[4][4];
        int[][] objintforexc1 = new int[4][4];
        int[][] objintforexc2 = new int[5][4];

        boolean except = false;

        ObjWithExcep.stringrandom(objforexc);
        ObjWithExcep.stringrandom(objforexc1);
        ObjWithExcep.stringrandom(objforexc2);

        do {
            try {
                new ObjWithExcep(objforexc, objintforexc);
                System.out.println("Общая сумма массива 1 равна " + ObjWithExcep.col);
                new ObjWithExcep(objforexc1, objintforexc1);
                System.out.println("Общая сумма массива 2 равна " + ObjWithExcep.col);
                new ObjWithExcep(objforexc2, objintforexc2);
                System.out.println("Общая сумма массива 2 равна " + ObjWithExcep.col);
                except = true;
            } catch (MyArrayDataException e) {
                objforexc[MyArrayDataException.rows][MyArrayDataException.colums] = "0";
                objforexc1[MyArrayDataException.rows][MyArrayDataException.colums] = "0";
                objforexc2[MyArrayDataException.rows][MyArrayDataException.colums] = "0";
                e.printStackTrace();
                except = false;
            } catch (MyArraySizeException e) {
                e.printStackTrace();
                except = true;
            }
        } while (except == false);
    }
}
