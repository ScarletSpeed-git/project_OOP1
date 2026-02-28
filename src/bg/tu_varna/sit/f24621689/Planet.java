package bg.tu_varna.sit.f24621689;

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

    public boolean removeJediByName(String jediName){
        for (int i = 0; i < population.size(); i++){
            if (population.get(i).getName().equals(jediName)){
                population.remove(i);
                return true;
            }
        }
        return false;
    }

}
