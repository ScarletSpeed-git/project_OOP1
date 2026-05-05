package bg.tu_varna.sit.f24621689.models;

public class Jedi {
    private String name;
    private Rank rank;
    private int age;
    private String saberColor;
    private double strength;

    public Jedi(String name, Rank rank, int age, String saberColor, double strength) {
        this.name = name;
        this.rank = rank;
        this.age = age;
        this.saberColor = saberColor;
        this.strength = strength;
    }

    public Jedi() {
    }

    public String getName() {
        return name;
    }

    public double getStrength() {
        return strength;
    }

    public String getSaberColor() {
        return saberColor;
    }

    public int getAge() {
        return age;
    }

    public Rank getRank() {
        return rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSaberColor(String saberColor) {
        this.saberColor = saberColor;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }
}
