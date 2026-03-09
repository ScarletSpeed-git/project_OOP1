package bg.tu_varna.sit.f24621689.data;

import bg.tu_varna.sit.f24621689.models.Planet;

import java.util.ArrayList;
import java.util.List;

    public class Universe {
    private List<Planet> planets;

        public Universe(){
            this.planets = new ArrayList<>();
        }

        public List<Planet> getPlanets() {
            return planets;
        }

        public Planet getPlanetByName(String name) {
            for (Planet planet : planets) {
                if (planet.getName().equalsIgnoreCase(name)) {
                    return planet;
                }
            }
            return null;
        }
    }
