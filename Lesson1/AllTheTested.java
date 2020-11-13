package Lesson1;

public interface AllTheTested {

    public String getType();
    public String getName();
    public int getMaxrun();
    public float getMaxjump();
    public int getSumrun();
    public void setSumrun(int sumrun);
    public int getSumjump();
    public void setSumjump(int sumjump);

    static void isayrun() {
        System.out.print(". Я бегу дорогу ");
    }

    static void isayjump() {
        System.out.print( ". Я прыгаю через ");
    }
}
