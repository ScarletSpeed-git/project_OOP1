package bg.tu_varna.sit.f24621689.data;

import bg.tu_varna.sit.f24621689.models.Planet;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * Handles the deserialization of the application data from an XML file.
 */
public class UniverseXmlReader {

    /**
     * Reads planet and Jedi data from the specified XML file and loads it into the given Universe.
     * It utilizes Java's built-in {@link XMLDecoder} to parse the XML format back into
     * compliant JavaBean objects. If the target file exists but is completely empty,
     * the method safely exits.
     *
     * @param universe The central {@link Universe} instance to be populated with the loaded data.
     * @param filePath The exact system file path of the XML document to read.
     * @throws Exception if the file cannot be found, accessed, or if a critical error occurs
     * during the decoding process .
     */
    @SuppressWarnings("unchecked")
    public void read(Universe universe, String filePath) throws Exception {
        File file = new File(filePath);

        /**
         *  Prevent End of File exceptions by gracefully skipping completely empty files
         */
        if (file.length() == 0) return;

        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)))) {
            /**
             *  Suppressed unchecked cast warning because the application strictly controls the XML schema
             */
            List<Planet> loadedPlanets = (List<Planet>) decoder.readObject();
            universe.getPlanets().addAll(loadedPlanets);
        }
    }
}