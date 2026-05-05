package bg.tu_varna.sit.f24621689.data;

import bg.tu_varna.sit.f24621689.models.Planet;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class UniverseXmlReader {

    @SuppressWarnings("unchecked")
    public void read(Universe universe, String filePath) throws Exception {
        File file = new File(filePath);
        if (file.length() == 0) return;

        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)))) {
            List<Planet> loadedPlanets = (List<Planet>) decoder.readObject();
            universe.getPlanets().addAll(loadedPlanets);
        }
    }
}