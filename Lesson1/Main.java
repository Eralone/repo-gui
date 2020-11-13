package Lesson1;

public class Main {


    public static void main(String[] args) {

        AllTheTested [] theTested = new AllTheTested[7];
        theTested[0] = new Human("Человек", "Игорь", 600, 1.8f);
        theTested[1] = new Human("Человек", "Вася", 250, 1.2f);
        theTested[2] = new Cat("Кот", "Васькин Вася", 250, 1.2f);
        theTested[3] = new Cat("Кот", "Мурзик", 10, 1.4f);
        theTested[4] = new Cat("Кот", "Тапок", 800, 5.4f);
        theTested[5] = new Robot("Робот", "Бендер", 1000, 0.1f);
        theTested[6] = new Robot("Робот", "Бендер с крыльями", 1200, 15);

        Track [] track = new Track[5];
        track [0] = new Track("Детская", 120);
        track [1] = new Track("Прогулочная", 220);
        track [2] = new Track("Беговая", 500);
        track [3] = new Track("Горная", 750);
        track [4] = new Track("Нет дороги (заблудился)", 1100);

        Wall [] wall = new Wall[5];
        wall [0] = new Wall("Бордюр", 0.05f);
        wall [1] = new Wall("Перила", 1);
        wall [2] = new Wall("Стенка почтампа", 2.5f);
        wall [3] = new Wall("Стенка военкомата", 4.5f);
        wall [4] = new Wall("Многоэтажка", 12);

        for (int i = 0; i < theTested.length; i++) {
            for (int j = 0; j < track.length; j++) {
                System.out.print(theTested[i].getType() + " . " + theTested[i].getName());
                AllTheTested.isayrun();
                System.out.println(track[j].name);
                if (theTested[i].getMaxrun()>=track[j].runlenght){
                    System.out.println("Он успешно пробежал по дорожке" + track[j].name);
                    theTested[i].setSumrun(theTested[i].getSumrun() + 1);

                        System.out.print(theTested[i].getType() + " . " + theTested[i].getName());
                        AllTheTested.isayjump();
                        System.out.println(wall[j].name);
                        System.out.println(theTested[i].getName() + " лезет по стене " + wall[j].name);
                        if (theTested[i].getMaxjump()>=wall[j].height) {
                            System.out.println("Он успешно перелез стену " + wall[j].name);
                            theTested[i].setSumjump(theTested[i].getSumjump() + 1);
                        }
                        else {
                            System.out.println("Он посмотрел вниз и упал в обморок у стены " + wall[j].name);
                            j = 100;
                            break;
                        }

                }
                else {
                    System.out.println("Он устал и споткнулся на дорожке" + track[j].name);
                    break;
                }
            }
        }
        System.out.println("__________________");
        System.out.println("В конечном итоге: ");
        for (int i = 0; i < theTested.length; i++) {
            System.out.println(theTested[i].getType() + " " + theTested[i].getName() + " пробежал " + theTested[i].getSumrun() + " дорожку(ек) и " + theTested[i].getSumjump() + " стенку(ок)");
        }




    }
}
