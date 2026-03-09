package bg.tu_varna.sit.f24621689.models;

import java.util.ArrayList;
import java.util.List;

public class Planet {
    private String name;
    private List<Jedi> population;

    public Planet(String name) {
        this.name = name;
        this.population = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public List<Jedi> getPopulation() {
        return population;
    }

    public void addJedi(Jedi jedi){
        this.population.add(jedi);
    }

}
