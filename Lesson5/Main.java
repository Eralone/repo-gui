package Lesson5;

public class Main {

    static final int value = 10000000;
    static final int lim = value/2;
    static float[] arr = new float[value];


    public static void main(String[] args) {

        met2(arr);

    }

    static void met1 (float[] arr){
        iterarr(arr);
        long curtime = System.currentTimeMillis();
        timewait();
        newiterarr(arr);
        timewait();
        System.out.println(System.currentTimeMillis() - curtime);
    }

    static void met2 (float[] arr){
        Thread thread1 = new Thread(() ->{
            float[] arr2 = new float[lim];
            System.arraycopy(arr,0,arr2,0, lim);
            met1(arr2);
            System.arraycopy(arr2,0,arr,0,lim);
            });

        Thread thread2 = new Thread(() ->{
            float[] arr3 = new float[lim];
            System.arraycopy(arr,lim,arr3,0, lim);
            System.out.println(System.currentTimeMillis());
            met1(arr3);
            System.arraycopy(arr3,0,arr,lim,lim);
            });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() + " - Конец выполнения 2х потоков");
    }

    static void iterarr(float[] arr){
        for (float v : arr) {
            v = 1;
        }
    }

    static void timewait(){
        System.out.println(System.currentTimeMillis());
    }

    static void newiterarr (float[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i]*Math.sin(0.2f + i/5)*Math.cos(0.2f + i/5)*Math.cos(0.4f+i/2));
        }
    }
}
