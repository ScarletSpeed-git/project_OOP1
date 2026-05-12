package bg.tu_varna.sit.f24621689.data;

import bg.tu_varna.sit.f24621689.models.Planet;
import java.util.ArrayList;
import java.util.List;

/**
 * The main data repository for the application.
 * Stores all planets and tracks the currently opened file path.
 */
public class Universe {

    /**
     * The list of all planets currently loaded in memory.
     */
    private List<Planet> planets;

    /**
     * The file path of the currently opened XML file.
     */
    private String currentFilePath;

    /**
     * Initializes an empty universe with no active file.
     */
    public Universe() {
        this.planets = new ArrayList<>();
        this.currentFilePath = null;
    }

    /**
     * Gets the list of planets.
     *
     * @return The list of {@link Planet} objects.
     */
    public List<Planet> getPlanets() {
        return planets;
    }

    /**
     * Finds a planet by its name (case-insensitive).
     *
     * @param name The name of the planet to search for.
     * @return The {@link Planet} object if found, or null if it does not exist.
     */
    public Planet getPlanetByName(String name) {
        for (Planet planet : planets) {
            if (planet.getName().equalsIgnoreCase(name)) {
                return planet;
            }
        }
        return null;
    }

    /**
     * Gets the active file path.
     *
     * @return The current file path, or null if no file is open.
     */
    public String getCurrentFilePath() {
        return currentFilePath;
    }

    /**
     * Sets the active file path.
     *
     * @param currentFilePath The new file path to track.
     */
    public void setCurrentFilePath(String currentFilePath) {
        this.currentFilePath = currentFilePath;
    }

    /**
     * Clears all planet data from memory and resets the file path.
     */
    public void clearUniverse() {
        this.planets.clear();
        this.currentFilePath = null;
    }
}