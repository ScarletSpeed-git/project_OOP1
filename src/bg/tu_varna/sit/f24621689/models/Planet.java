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

    public Planet() {
        this.population = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Jedi> getPopulation() {
        return population;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(List<Jedi> population) {
        this.population = population;
    }

    public void addJedi(Jedi jedi){
        this.population.add(jedi);
    }

}
