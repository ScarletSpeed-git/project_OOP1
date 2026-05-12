package bg.tu_varna.sit.f24621689.models;

/**
 * Represents a Jedi entity within the Star Wars universe.
 * This class acts as a standard JavaBean, storing data about a Jedi's personal details,
 * combat statistics, and hierarchical rank.
 */
public class Jedi {

    /**
     * The unique name of the Jedi.
     */
    private String name;

    /**
     * The hierarchical rank of the Jedi (e.g., YOUNGLING, PADAWAN, KNIGHT, MASTER, GRAND_MASTER).
     */
    private Rank rank;

    /**
     * The age of the Jedi in years.
     */
    private int age;

    /**
     * The color of the Jedi's lightsaber.
     */
    private String saberColor;

    /**
     * The combat strength multiplier of the Jedi.
     */
    private double strength;

    /**
     * Constructs a new Jedi with the specified attributes.
     *
     * @param name       The name of the Jedi.
     * @param rank       The starting {@link Rank} of the Jedi.
     * @param age        The age of the Jedi in years.
     * @param saberColor The color of the Jedi's lightsaber.
     * @param strength   The combat strength multiplier of the Jedi.
     */
    public Jedi(String name, Rank rank, int age, String saberColor, double strength) {
        this.name = name;
        this.rank = rank;
        this.age = age;
        this.saberColor = saberColor;
        this.strength = strength;
    }

    /**
     * Default empty constructor.
     * Required for XML serialization
     */
    public Jedi() {
    }

    /**
     * Retrieves the name of the Jedi.
     *
     * @return A String representing the Jedi's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the combat strength multiplier of the Jedi.
     *
     * @return A double representing the Jedi's strength.
     */
    public double getStrength() {
        return strength;
    }

    /**
     * Retrieves the color of the Jedi's lightsaber.
     *
     * @return A String representing the lightsaber color.
     */
    public String getSaberColor() {
        return saberColor;
    }

    /**
     * Retrieves the age of the Jedi.
     *
     * @return An integer representing the Jedi's age in years.
     */
    public int getAge() {
        return age;
    }

    /**
     * Retrieves the current rank of the Jedi.
     *
     * @return The {@link Rank} enum value representing the Jedi's status.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Sets or updates the name of the Jedi.
     *
     * @param name The new name to assign to the Jedi.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets or updates the age of the Jedi.
     *
     * @param age The new age to assign.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets or updates the color of the Jedi's lightsaber.
     *
     * @param saberColor The new lightsaber color to assign.
     */
    public void setSaberColor(String saberColor) {
        this.saberColor = saberColor;
    }

    /**
     * Sets or updates the rank of the Jedi.
     *
     * @param rank The new {@link Rank} to assign.
     */
    public void setRank(Rank rank) {
        this.rank = rank;
    }

    /**
     * Sets or updates the combat strength multiplier of the Jedi.
     *
     * @param strength The new strength value to assign.
     */
    public void setStrength(double strength) {
        this.strength = strength;
    }
}