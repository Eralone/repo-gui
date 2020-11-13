package Lesson1;

public class Cat implements AllTheTested {

    private String type;
    private String name;
    private int maxrun;
    private float maxjump;
    private int sumrun;
    private int sumjump;

    public Cat(String type,String name, int maxrun, float maxjump) {
        this.type = type;
        this.name = name;
        this.maxrun = maxrun;
        this.maxjump = maxjump;
        sumrun = 0;
        sumjump = 0;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getMaxrun() {
        return maxrun;
    }

    public float getMaxjump() {
        return maxjump;
    }

    public int getSumrun() {
        return sumrun;
    }

    public void setSumrun(int sumrun) {
        this.sumrun = sumrun;
    }

    public int getSumjump() {
        return sumjump;
    }

    public void setSumjump(int sumjump) {
        this.sumjump = sumjump;
    }
}
