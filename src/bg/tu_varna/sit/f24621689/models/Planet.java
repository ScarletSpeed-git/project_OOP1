package bg.tu_varna.sit.f24621689.models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores the planet's name and maintains a dynamic list of the {@link Jedi}
 * population currently residing on it.
 */
public class Planet {

    /**
     * The unique, designated name of the planet.
     */
    private String name;

    /**
     * The collection of Jedi currently inhabiting this planet.
     */
    private List<Jedi> population;

    /**
     * Constructs a new Planet with the specified name.
     * Automatically initializes an empty population list.
     *
     * @param name The name to assign to the newly discovered planet.
     */
    public Planet(String name) {
        this.name = name;
        this.population = new ArrayList<>();
    }

    /**
     * Default empty constructor.
     * Initializes an empty population list to prevent null references.
     * Required for XML serialization (using XMLEncoder) and JavaBean compliance.
     */
    public Planet() {
        this.population = new ArrayList<>();
    }

    /**
     * Retrieves the name of the planet.
     *
     * @return A String representing the planet's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the current list of Jedi residing on this planet.
     *
     * @return A List containing {@link Jedi} objects representing the population.
     */
    public List<Jedi> getPopulation() {
        return population;
    }

    /**
     * Sets or updates the name of the planet.
     *
     * @param name The new name to assign to the planet.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Overwrites the current Jedi population with a completely new list.
     *
     * @param population The new List of {@link Jedi} objects to assign.
     */
    public void setPopulation(List<Jedi> population) {
        this.population = population;
    }

    /**
     * Adds a single Jedi to the planet's current population list.
     *
     * @param jedi The {@link Jedi} object to append to the population.
     */
    public void addJedi(Jedi jedi) {
        this.population.add(jedi);
    }
}