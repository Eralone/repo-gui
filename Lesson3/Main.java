package Lesson3;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> text = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            text.add("строка один");
            text.add("строка два");
            text.add("строка три");
            text.add("строка четыре");
            text.add("строка пять");
            text.add("строка шесть");
            text.add("строка семь");
            text.add("строка восемь");
            text.add("строка девять");
            text.add("строка ноль");
        }

        System.out.println(text);

        HashSet nodub = new HashSet(text);

        ArrayList<String> nodub1 = new ArrayList<>(nodub);

        System.out.println(nodub1);


        int [] sum = new int[text.size()];

        for (int i = 0; i < text.size(); i++) {
            for (int j = 0; j < nodub1.size(); j++) {
                if (text.get(i) == nodub1.get(j)){
                    sum[j] = sum[j] +1;
                }
            }
        }
        for (int i = 0; i < nodub1.size(); i++) {
            System.out.println(nodub1.get(i) + " - количество дубликатов = " + sum[i]);
        }

        // Либо ДЗ №1 можно решить через ключ:

//        HashMap<Integer, String> allkey = new HashMap<>();
//
//        for (int i = 0; i < nodub.size(); i++) {
//            allkey.put(i,nodub1.get(i));
//        }
//
//        for (int i = 0; i < text.size(); i++) {
//            for (int j = 0; j < allkey.size(); j++) {
//                if (text.get(i) == allkey.get(j)){
//                    sum[j] = sum[j] +1;
//                }
//            }
//        }
//        for (int i = 0; i < allkey.size(); i++) {
//            System.out.println(allkey.get(i) + " - колличество дубликатов = " + sum[i]);
//        }
//
//

        ArrayList<PhoneBook> list = new ArrayList<>();
        list.add(new PhoneBook("Ivanov", "8960092312"));
        list.add(new PhoneBook("Rezvanov", "89794526223"));
        list.add(new PhoneBook("Smirnov", "841245346643"));
        list.add(new PhoneBook("Sidorow", "+79245324326"));
        list.add(new PhoneBook("Ivanov", "7959842523562"));
        list.add(new PhoneBook("Ivanov", "987423875743"));

        ArrayList<String> str = new ArrayList<>();
        int ind = 0;
        
        for (PhoneBook test : list){
            str.add(test.number);
            if (test.name.equals("Ivanov")) {
                System.out.println(str.get(ind));
            }
            ind = ind + 1;
        }
    }
}
